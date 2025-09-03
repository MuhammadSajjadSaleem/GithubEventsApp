package com.sajjad.feature_events.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjad.core.common.Result
import com.sajjad.feature_events.domain.model.Event
import com.sajjad.feature_events.domain.usecase.GetEventsPageUseCase
import com.sajjad.feature_events.domain.usecase.PollEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val getEventsPage: GetEventsPageUseCase,
    private val pollEvents: PollEventsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Result<List<Event>>>(Result.Loading)
    val state: StateFlow<Result<List<Event>>> = _state.asStateFlow()

    private val _selectedEvent = MutableStateFlow<Event?>(null)
    val selectedEvent: StateFlow<Event?> = _selectedEvent.asStateFlow()

    private val seenIds = LinkedHashSet<String>()
    private var currentPage = 1

    private var pollingJob: Job? = null

    init {
        refresh()
    }

    private fun refresh() {
        currentPage = 1
        seenIds.clear()
        loadPage(reset = true)
    }

    fun loadMore() {
        currentPage += 1
        loadPage(reset = false)
    }

    private fun loadPage(reset: Boolean) {
        viewModelScope.launch {
            when (val res = getEventsPage(currentPage)) {
                is Result.Error -> _state.value = res
                is Result.Success -> {
                    val mapped = res.data.filter { seenIds.add(it.id) }
                    val current = if (reset || _state.value !is Result.Success) {
                        emptyList()
                    } else {
                        (_state.value as Result.Success<List<Event>>).data
                    }
                    _state.value = Result.Success(current + mapped)
                }
                Result.Loading -> _state.value = Result.Loading
            }
        }
    }

    fun startPolling() {
        if (pollingJob?.isActive == true) return
        pollingJob = viewModelScope.launch {
            pollEvents().collect { res ->
                if (res is Result.Success) {
                    val fresh = res.data.filter { it.id !in seenIds }
                    if (fresh.isNotEmpty()) {
                        fresh.forEach { seenIds.add(it.id) }
                        val current = (_state.value as? Result.Success)?.data ?: emptyList()
                        _state.value = Result.Success(fresh + current)
                    }
                }
            }
        }
    }

    fun stopPolling() {
        pollingJob?.cancel()
        pollingJob = null
    }

    fun selectEventById(id: String) {
        val current = (_state.value as? Result.Success)?.data
        _selectedEvent.value = current?.firstOrNull { it.id == id }
    }
}
