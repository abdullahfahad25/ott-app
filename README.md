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
1. Language: Kotlin
2. Retrofit2
3. GSON
4. Glide
5. Gradle version: 8.9
6. AGP version: 8.7.3
