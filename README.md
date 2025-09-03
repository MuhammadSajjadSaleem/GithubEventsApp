# GitHub Events App 

An Android app built with **Jetpack Compose**, **Hilt**, and **Retrofit** that displays the latest GitHub events.  
It supports **integration testing with MockWebServer** to validate UI flows like loading event lists and navigating to details.

---

## 📽️ Demo Video

https://github.com/user-attachments/assets/36a13a56-a5a2-4a0c-83f1-0798922dd46a



Watch the demo here:  

---

## ✨ Features
- Show list of recent GitHub events
- Display event details on item click
- Integration tests with MockWebServer
- Hilt dependency injection
- Jetpack Compose UI
- Polling for new events every 10 seconds (currently polls the **first page only**)

---

## 🚀 Tech Stack
- **UI**: Jetpack Compose
- **DI**: Hilt
- **Networking**: Retrofit + OkHttp
- **Testing**: JUnit4, MockWebServer, Compose UI Testing
- **Build**: Gradle (Kotlin DSL)

---

## 🧪 Testing
- **Unit Tests**: cover core business logic (use cases, repositories)
- **Integration Tests**: validate UI flow (list → detail) using **MockWebServer**
- Example reports:
  <img width="929" height="442" alt="Screenshot 2025-09-03 at 3 35 38 PM" src="https://github.com/user-attachments/assets/c49520bb-1f00-40b4-94fe-89c8b4386dc8" />

<img width="880" height="400" alt="Screenshot 2025-09-03 at 3 36 13 PM" src="https://github.com/user-attachments/assets/03fcdbfa-5aff-49df-af66-88b41707054a" />

---

## 📝 Notes & Future Improvements
- UI can be enhanced with more **Material 3** components.
- Extract common test utilities into a `core-test` module for reusability.
- Adding offline caching (Room) would improve UX.

---

