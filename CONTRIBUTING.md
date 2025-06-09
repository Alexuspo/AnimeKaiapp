# Contributing to AnimeKai Android App

First off, thank you for considering contributing to AnimeKai Android App! It's people like you that make this project better.

## Code of Conduct

This project and everyone participating in it is governed by our commitment to creating a welcoming and inclusive environment for all contributors.

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check existing issues as you might find out that you don't need to create one. When you are creating a bug report, please include as many details as possible:

- **Use a clear and descriptive title**
- **Describe the exact steps which reproduce the problem**
- **Provide specific examples to demonstrate the steps**
- **Describe the behavior you observed after following the steps**
- **Explain which behavior you expected to see instead and why**
- **Include screenshots and animated GIFs** if possible
- **Include device information** (Android version, device model, app version)

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, please include:

- **Use a clear and descriptive title**
- **Provide a step-by-step description of the suggested enhancement**
- **Provide specific examples to demonstrate the steps**
- **Describe the current behavior** and **explain which behavior you expected to see instead**
- **Explain why this enhancement would be useful**

### Pull Requests

1. Fork the repository
2. Create your feature branch from `main`
3. Make your changes
4. Add or update tests as appropriate
5. Ensure your code follows the project's coding standards
6. Write clear, concise commit messages
7. Push to your fork and submit a pull request

#### Pull Request Guidelines

- **Update the README.md** with details of changes if applicable
- **Include screenshots** for UI changes
- **Write clear commit messages** following the format: `type: description`
  - `feat:` for new features
  - `fix:` for bug fixes
  - `docs:` for documentation changes
  - `style:` for code style changes
  - `refactor:` for code refactoring
  - `test:` for adding tests
  - `chore:` for maintenance tasks

## Development Setup

### Prerequisites

- Android Studio Hedgehog | 2023.1.1 or newer
- Android SDK API Level 35
- Git

### Setting up the development environment

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/AnimeKaiapp2.git
   cd AnimeKaiapp2
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned repository folder

3. **Build the project**
   ```bash
   ./gradlew build
   ```

### Coding Standards

- Follow [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Add comments for complex logic
- Keep functions small and focused
- Follow Android best practices for WebView implementation

### Testing

- Test your changes on multiple Android versions if possible
- Verify WebView functionality works correctly
- Ensure navigation and back button behavior is preserved
- Test on different screen sizes and orientations

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/alexus/studio/animekaiapp/
│   │   │   └── MainActivity.kt          # Main WebView activity
│   │   ├── res/                         # Android resources
│   │   └── AndroidManifest.xml          # App manifest
│   └── test/                            # Unit tests
├── build.gradle.kts                     # App-level build configuration
└── proguard-rules.pro                   # ProGuard configuration
```

## Questions?

Don't hesitate to ask questions by creating an issue with the "question" label.

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

Thank you for your contribution! 🎉 