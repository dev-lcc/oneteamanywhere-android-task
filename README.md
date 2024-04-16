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

## Remaining Features that are yet to be addressed
- [ ] `ActivityRepository` actual use of Store5 framework to handle data persistence and caching.
  - for some reason, my local database implementation had some issues where it infinitely emits different sorts of Level list data which causes a never ending recomposition(Loading<->Render Levels List<->Loading...)
  - Last resort would be to replace SQLDelight with [RoomDB](https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase) implementation
  - For the meantime, I just did the easy way by using StateFlow to persist the data(fetched from REST API) in-memory.
- [ ] Comprehensive test cases for `HomeScreenViewModel`
  - I ran out of time to complete the test cases. It is also due to the above issue(ActivityRepository -> Store5 -> Local Database issue) which took me a lot of time debugging but to no avail.
- [ ] Under Home Screen, Level -> Activities Icons: I deliberately made the each Icon fixed because I no longer had time to implement a PDF renderer to render each activity icon.
  - from the response, Activity.Icon content type is a `application/pdf` content(i.e. [Chapter_05__Lesson_02__State_Active.pdf](https://assets.ctfassets.net/37k4ti9zbz4t/7qfuLW6KOLr5wARa6y1iiJ/d9fe08d9680ebe8fa1d02b056e9d9f61/Chapter_05__Lesson_02__State_Active.pdf))
  - I could use something like this PDF rendering framework [Bouquet](https://github.com/GRizzi91/bouquet) to render PDF file, extract bitmap, then use is as an Activity Item icon.
