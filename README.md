# GitHub Events App 

An Android app built with **Jetpack Compose**, **Hilt**, and **Retrofit** that displays the latest GitHub events.  
It supports **integration testing with MockWebServer** to validate UI flows like loading event lists and navigating to details.

---

## 📽️ Demo Video
Watch the demo here:  
[Screen Recording 2025-09-03 at 3.20.39 PM.mov](../../Desktop/Screen%20Recording%202025-09-03%20at%203.20.39%E2%80%AFPM.mov)

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
  ![Unit Test Report](../../Desktop/Screenshot%202025-09-03%20at%203.35.38%E2%80%AFPM.png)  
  ![Integration Test Report](../../Desktop/Screenshot%202025-09-03%20at%203.36.13%E2%80%AFPM.png)

---

## 📝 Notes & Future Improvements
- UI can be enhanced with more **Material 3** components.
- Extract common test utilities into a `core-test` module for reusability.
- Adding offline caching (Room) would improve UX.

---

