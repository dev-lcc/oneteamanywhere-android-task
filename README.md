## One Team Anywhere Android Task
- This Android app project is delivered for the [android task](https://getahead.notion.site/Android-Task-a3a6336b9274434fb323bc204ba4219c).

## Technology Stack Used
- Kotlinx Coroutines
- Jetpack Compose
  - for Android UI development
- Android Jetpack Components
  - such as ViewModel, Lifecycle, etc.
- Version Catalogue
  - `libs.versions.toml` to manage dependencies
- [CashApp Turbine](https://github.com/cashapp/turbine)
  - for Flow testing, particularly for ViewModel tests
- [SQLDelight](https://github.com/cashapp/sqldelight)
  - for Local Database persistence
- [Store](https://github.com/MobileNativeFoundation/Store)
  - Store implementation to consolidate multiple datasources, providing fetcher(i.e. using Network API services), source of truth(i.e. using Local Database), cache policy, and a lot more.

## Project Architecture
- Following MVVM(Model-View-ViewModel) Clear Code Architecture, with Repository, Network Datasource, and Local Datasource.
- Built on multi-module setup
  - `app` module
    - This is the android app which includes presentation layer, ViewModel classes, navigation, etc..
  - `core-model` module
    - This contains the domain models
  - `core-network` module
    - This contains Network and REST API datasource implementation
  - `core-database` module
    - This contains Local Database datasource implementation
  - `core-data` module
    - This contains the Repository class, consolidating multiple datasources(i.e. Network/Database) which provides data and acts as a bridge to ViewModels and presentation layer.

## How to Build Project
- Just run the app as-is.
