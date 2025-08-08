package com.example.fahad.ifarmerott.utils

object Constants {
    //Data fetch related constants
    const val BASE_URL = "https://www.omdbapi.com/"
    //Note: Api key must not be put in any Kotlin file in production due to security issue
    const val API_KEY = "f2a5c29"

    //Listing screen related constants
    const val LISTING_FIELD_QUERY = "query"
    const val LISTING_FIELD_YEAR = "year"
    const val PAGE_ITEM_LIMIT = 10

    //Home screen related constants
    const val CAROUSEL_MOVIE_VALUE = "Marvel"
    const val BATMAN_MOVIE_VALUE = "Batman"
    const val LATEST_MOVIE_VALUE = "movie"
    const val LATEST_MOVIE_YEAR = "2022"

    //Details screen related constants
    const val DETAILS_FIELD_IMDBID = "imdbID"
}