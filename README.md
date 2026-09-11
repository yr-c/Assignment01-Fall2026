# Assignment01-Fall2026 : Tying tutor

### By Yoonchan Rhie

This is a submission for an assignment at Vanier college.

## Features

- **Real-time Accuracy Tracking:** Instantly tracks correct and incorrect keystrokes as you type.
- **Visual Error Feedback:** The text field automatically turns red if a typo is made, helping you correct mistakes
  immediately.
- **Interactive On-Screen Keyboard:** Highlights the keys currently being pressed (including Space and Shift).
- **Multiple Prompts:** Cycle through various built-in text prompts using the "Next" and "Reset" buttons.
- **Progress Tracking:** Displays your current prompt progress (e.g., 1/6) alongside a live tally of correct/incorrect
  characters.

## Technologies Used

- **Java:** Core application logic.
- **CSS:** Node styling.
- **JavaFX:** User interface and keyboard event handling.
- **Lombok:** Boilerplate reduction (using annotations like `@Getter`).

## Getting Started

### Prerequisites

- JDK 11 or higher (configured with JavaFX, or handled via Maven/Gradle).
- [Lombok](https://projectlombok.org/) plugin installed and enabled in your IDE.

## Key Classes

- `App.java`: The main application class containing the UI layout, data binding, and event listeners.
- `TextManager.java`: Manages the typing prompts and calculates all typing accuracy statistics.
- `KeyboardManager.java`: Handles the generation, mapping, and visual styling of the on-screen virtual keyboard.
