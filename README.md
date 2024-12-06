## Snap! Mobile Reverse Polish Notation Calculator Project by Sam Christiansen

### Project Review
- I spent ~3 hours on this project: ~2 hours getting the UI and ViewModel hooked up, and ~1 hour getting the Reverse Polish Notation Calculator logic working and tested. I've never heard of a Reverse Polish Notation Calculator before, so it was fun learning the logic to it.
- I used Kotlin and JetPack Compose, as those are the most comfortable for me.
- I used MVVM for my architecture, to keep the code logic clean and separated.
  - UI held in CalculatorView
  - ViewModel in CalculatorViewModel
  - ViewModel's state in CalculatorState
  - Logic specific for Reverse Polish Notation Calculator in RpnCalculationsImpl
- I show specific errors for when there are too many numbers, too many operators, and when dividing by zero. There is also an error code for invalid operation, but this shouldn't be seen by users if they use the buttons provided.
- I kept the UI fairly simple. I can follow design files from figma to make more complex views, but since there was no design requirement, I kept it simple.

### Uncompleted Work
- I realized that I didn't handle the use case for negative numbers. Since I added code to automatically add spaces between numbers and operators, this logic would have to be updated to handle negative numbers.
- I didn't get around to adding unit tests
- I didn't add DI, which I would for unit testing

### Libraries Added
- No extra libraries were needed for this project

### How to Run the Project
- Clone the project locally
- Open the project in Android Studios
- Run the project with the "app" run configuration selected
  - Instructions for using an android emulator can be found [here](https://developer.android.com/studio/run/managing-avds).
  - If you want to use a physical device, instructions for setting up your android device to debug found [here](https://developer.android.com/studio/debug/dev-options)

### Example Reverse Polish Notation Expressions:
- `5 8 +`
  - `13.0`
- `5 5 5 8 + + - 13 +`
  - `0.0`
- `5 9 1 - /`
  - `0.625`
- `2 3 + 4 *`
  - `20.0`
- `3 4 + 5 2 - *`
  - `21.0`