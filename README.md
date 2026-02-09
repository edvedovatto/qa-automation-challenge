# QA Automation Challenge

This repository contains a complete automation framework covering API and E2E web testing.

## Project Structure

```
├── 📁 .github
│   └── 📁 workflows
│       └── ⚙️ pipeline.yml # CI/CD Configuration
├── 📁 api-tests # RESTAssured API suite
│   ├── 📁 .mvn
│   │   ├── ⚙️ jvm.config
│   │   └── ⚙️ maven.config
│   ├── 📁 src
│   │   ├── 📁 main
│   │   │   └── 📁 java
│   │   └── 📁 test
│   │       ├── 📁 java
│   │       │   ├── 📁 clients
│   │       │   │   └── ☕ UsersClient.java
│   │       │   ├── 📁 tests
│   │       │   │   ├── ☕ CreateUserTest.java
│   │       │   │   ├── ☕ DeleteUserTest.java
│   │       │   │   ├── ☕ GetUserTest.java
│   │       │   │   └── ☕ UpdateUserTest.java
│   │       │   └── 📁 utils
│   │       │       └── ☕ Config.java
│   │       └── 📁 resources
│   │           └── 📄 allure.properties
│   └── ⚙️ pom.xml
├── 📁 e2e-tests # Selenium + Cucumber UI suite
│   ├── 📁 .mvn
│   │   ├── ⚙️ jvm.config
│   │   └── ⚙️ maven.config
│   ├── 📁 src
│   │   └── 📁 test
│   │       ├── 📁 java
│   │       │   ├── 📁 pages
│   │       │   │   ├── ☕ CartPage.java
│   │       │   │   ├── ☕ CheckoutCompletePage.java
│   │       │   │   ├── ☕ CheckoutInformationPage.java
│   │       │   │   ├── ☕ CheckoutOverviewPage.java
│   │       │   │   ├── ☕ InventoryPage.java
│   │       │   │   └── ☕ LoginPage.java
│   │       │   ├── 📁 runners
│   │       │   │   └── ☕ RunCucumberTest.java
│   │       │   ├── 📁 steps
│   │       │   │   ├── ☕ CheckoutSteps.java
│   │       │   │   ├── ☕ Hooks.java
│   │       │   │   └── ☕ LoginSteps.java
│   │       │   └── 📁 support
│   │       │       └── ☕ BaseTest.java
│   │       └── 📁 resources
│   │           └── 📁 features
│   │               ├── 📄 checkout.feature
│   │               └── 📄 login.feature
│   └── ⚙️ pom.xml
├── ⚙️ .gitignore
└── 📝 README.md
```

## Test Targets

### API
The API tests are executed against the public testing API ReqRes:
https://reqres.in

This API was chosen for its stable endpoints and support for common CRUD validation scenarios.

### UI (E2E)
The E2E tests are executed on the SauceDemo e-commerce demo application:
https://www.saucedemo.com

Was selected for its stability and realistic user flows, including login, cart, and checkout.

## Run

### 1) API Tests
``` 
cd api-tests
mvn clean test 
```

### Report:
After running, use the following command to see the interactive dashboard:
```
mvn allure:serve
```

![allure](docs/allure.png)

### 2) UI Tests (E2E)
```
cd e2e-tests
mvn clean test
```

### Report:
Open the file generated at target/cucumber-report.html

![cucumber](docs/cucumber.png)

## Scenarios Covered
* API: Multiple endpoints validation including status codes, headers, and body (positive & negative).
* UI: Login and complete e-commerce checkout flow.

## Stack
- Java: 17 
- Rest Assured: 5.4.0 
- Selenium: 4.17.0 
- Cucumber: 7.14.0 
- JUnit Jupiter: 5.11.0
- Allure Report: 2.24.0
- Maven: 3.9.11

## CI/CD Pipeline
- Automated execution via GitHub Actions on every push.
- All reports are saved as artifacts in the Actions summary.

## Future Improvements
- Mobile: The structure is ready for an Appium module, that would be integrated as a parallel job in the existing CI pipeline. In a production scenario, the mobile suite would be triggered automatically after successful API/E2E runs (or in parallel), ensuring that the application remains stable across all platforms at every commit.