# AnimeKai Android App
![Verze](https://img.shields.io/badge/version-1.2.0-blue.svg)

A WebView wrapper application for [AnimeKai.to](https://animekai.to/home) - providing a native Android experience for streaming anime content.

## 📱 Features

- **Native Android Experience**: Full WebView integration with native Android navigation
- **Optimized Performance**: Lightweight wrapper with optimized WebView settings
- **Responsive Design**: Supports zoom and responsive layouts
- **Navigation Controls**: Back button support for web history navigation
- **Network Optimized**: Efficient loading with proper error handling
- **Modern UI**: Clean interface using AppCompat theme

## 🚀 Installation

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or newer
- Android SDK API Level 35
- Minimum Android version: 7.0 (API level 24)

### Build Instructions

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
   ./gradlew assembleDebug
   ```

4. **Install on device**
   ```bash
   ./gradlew installDebug
   ```

## 🛠️ Technical Details

### Architecture
- **Language**: Kotlin
- **UI Framework**: Android WebView + AppCompat
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 35 (Android 14)

### Dependencies
- `androidx.appcompat:appcompat:1.6.1`
- `androidx.webkit:webkit:1.8.0`
- `androidx.core:core-ktx`
- `androidx.lifecycle:lifecycle-runtime-ktx`

### WebView Configuration
- JavaScript enabled for full functionality
- DOM Storage enabled
- Zoom controls with enhanced UX
- Mixed content support for compatibility
- Custom User-Agent for optimal web experience

## 📋 Permissions

The app requires the following permissions:
- `INTERNET` - For accessing AnimeKai.to content
- `ACCESS_NETWORK_STATE` - For network connectivity checks

## 🔧 Configuration

### WebView Settings
```kotlin
webSettings.javaScriptEnabled = true
webSettings.domStorageEnabled = true
webSettings.loadWithOverviewMode = true
webSettings.useWideViewPort = true
webSettings.setSupportZoom(true)
webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
```

### URL Handling
The app is configured to handle only AnimeKai.to URLs to maintain focus and security.

## 📱 Screenshots

<!-- Add screenshots here when available -->

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## ⚠️ Disclaimer

This application is a WebView wrapper for AnimeKai.to. All content is provided by AnimeKai.to and this app does not host any content. Please respect copyright laws and the terms of service of AnimeKai.to.

## 🔗 Links

- [AnimeKai.to Official Website](https://animekai.to/home)
- [Android Developer Documentation](https://developer.android.com/)
- [WebView Best Practices](https://developer.android.com/guide/webapps/webview)

## 📞 Support

For support, please open an issue in this repository or contact the development team.

---

**Note**: This is an unofficial third-party application and is not affiliated with AnimeKai.to. 
