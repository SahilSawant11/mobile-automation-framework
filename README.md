# Mobile Automation Framework (Java + Appium)

Enterprise-grade Java-based Appium UI automation framework for Kotlin Android apps.

**Drop-in usage**
1. Place your APK under `src/test/resources/apps/`.
2. Update `src/test/resources/config/config.properties` (device capabilities, app path, environment).
3. Run: `./scripts/run-tests.sh -s regression -e android-local`

This repository includes:
- Maven + TestNG + Appium
- Page Object Model (POM)
- Allure reporting & GitHub Actions pipelines
- Parallel execution with thread-safe driver management
- Dockerized Selenium Grid for webview tests
- Device farm integrations & extensible utilities
