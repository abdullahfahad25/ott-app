This is an ott app. It shows information about movies. Movie can also be played.

Features:
1. It has 3 screens.
     i. Home screen
     ii. Listing screen
     iii. Details screen
2. Home Screen has
    i. Carousel about 5 Marvel movies
    ii. Batman movies rail
    iii. Latest movies(2022) rail
3. Listing Screen has
    i. list of movies
4. Details Screen
    i. Video player
    ii. Detailed movie information

How it works:
1. Home screen fetches and shows information from backend for Marvel carousel, Batman rail and Latest rail.
2. Clicking on Batman Movies rail's 'See All >' option will launch listing screen with Batman movies.
      Click Batman movies' 'See All >' -> Listing Screen with Batman movies
3.  Clicking on Latest Movies rail's 'See All >' option will launch listing screen with Latest movies(2022).
      Click Latest movies' 'See All >' -> Listing Screen with Latest movies released in year 2022 and have keyword 'movie' in the title
4. Clicking on any movie will launch Details screen about that particular movie. Also,
  http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4
    video will be played.

Tech Stack used:
1. Language: Kotlin version: 1.9.24
2. Retrofit2 version: 2.11.0
3. GSON Converter version: 2.11.0
4. Glide version: 4.16.0
5. Gradle version: 8.9
6. AGP version: 8.7.3
7. JDK version: 21

How to Build and Run:
Prerequisites:
1. Android Studio (Recommended version: Lady Bug or higher)
2. JDK 11 or higher
3. Android device running Android 9.0 Pie or above. App is optimized for Android 13/ Api level 35
4. Android SDK 35 need to be installed to compile the project

Build and Run:
1. Clone the repository
2. Open the project in Android Studio
    i. Launch Android Studio
    ii. Click Open an existing project
    iii. Select the project folder that is just cloned
3. Sync Gradle
    i. Click File > Sync Project with Gradle Files
4. Configure Kotlin and Android Gradle Plugin versions
    i. These are set in the build.gradle files
5. Run the app
    i. Connect an Android device via USB or start an emulator
    ii. Click the Run button
