# loginApp
Source code for Simple login app - Here We can login through Facebook and Google by proving our suitable password.And there is also logout option is there using architecture component.  

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Dagger 2](https://dagger.dev/) - Dependency Injection Framework
- [Room data base](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-4-saving-user-data/lesson-10-storing-data-with-room/10-1-c-room-livedata-viewmodel/10-1-c-room-livedata-viewmodel.html) - For Database related activity.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Google login for Android](https://developers.google.com/identity/sign-in/android/start-integrating) - login using google account for Android.
- [Facebook login for Android](https://developers.facebook.com/docs/facebook-login/android/) - login using facebook account for Android.

# Package Structure
    
    com.android.microblogapp    # Root Package
    .
    ├── data                # For data handling.
    │   ├── local           # Local Data Handlers     
    |   │   ├── db          # databse related activity.
            ├── prefs       # local file system for saving data. 
        └── model           # POJO classes  
    │   └── repository      # Single source of data.
    |
    ├── model               # Model classes
    |
    ├── di                  # Dependency Injection             
    │   ├── builder         # Activity Builder
    │   ├── component       # DI Components       
    │   └── module          # DI Modules
    |
    ├── ui                  # Activity/View layer
    │   ├── base            # Base View
    |
    │   ├── home            # User login Activity & ViewModel
    |   │ 
    │   └── main            # User main Activity & ViewModel
    |
    └── utils               # Utility Classes / Common classes / Rx / display

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
