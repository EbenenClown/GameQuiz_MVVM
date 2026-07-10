# GameQuiz (MVVM Showcase)

A native Android Quiz Application centered around video games, built to explore, implement, and benchmark modern Android development best practices and architecture patterns. The project was build 4 years ago, so there was no AI involvement.

[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](https://developer.android.com)
[![Architecture](https://img.shields.io/badge/Architecture-MVVM-blue.svg)](https://developer.android.com/topic/libraries/architecture)
[![Language](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org)

---

## 🚀 Purpose & Motivation
This project was initiated as a comprehensive playground to master the **MVVM (Model-View-ViewModel)** architecture pattern and integrate industry-standard Android libraries. The core focus was to achieve a clean separation of concerns, testability, and a highly scalable codebase while utilizing cloud-backed services. The Project was build 4 years ago, so there was no involvement of AI.

---

## 🛠️ Tech Stack & Architecture

### Architecture & UI
*   **MVVM Architecture:** Strict separation of UI logic, business logic, and data.
*   **Jetpack Compose:** Fully declarative UI implementation for modern, smooth, and reactive user interfaces.
*   **State Management:** Leveraging Kotlin flows / LiveData to seamlessly propagate state changes from ViewModels to the Compose UI.

### Backend & Cloud Integration (Firebase)
*   **Firebase Authentication:** Secure user sign-up, sign-in, and session handling.
*   **Firebase Realtime Database / Firestore:** Used for dynamic, live updates of the quiz question database and real-time score syncing.
*   **Cloud Storage / Performance:** Efficiently handling user data and high-score leaderboards.

### Dependency Injection & Core Libraries
*   **Dagger (Hilt):** Compile-time dependency injection to ensure modularity, loose coupling, and optimal testability across ViewModels and repositories.
*   **Coroutines & Flow:** Asynchronous programming made simple, handling network requests and database queries off the main thread.

---

## 📦 Project Structure (High-Level)
The project follows a feature-by-feature or layer-based clean architecture approach:
*   `data/`: Data sources, Firebase repositories, and data models.
*   `di/`: Dagger/Hilt modules providing singletons and repository instances.
*   `ui/`: Jetpack Compose screens, components, themes, and corresponding ViewModels.
*   `model/`: Pure domain/business logic models.
