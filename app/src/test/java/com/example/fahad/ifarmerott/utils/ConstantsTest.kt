package com.example.fahad.ifarmerott.utils

import org.junit.Assert.*
import org.junit.Test

class ConstantsTest {
    @Test
    fun testConstantsValues() {
        assertEquals("https://www.omdbapi.com/", Constants.BASE_URL)
        assertEquals("f2a5c29", Constants.API_KEY)

        assertEquals("query", Constants.LISTING_FIELD_QUERY)
        assertEquals("year", Constants.LISTING_FIELD_YEAR)
        assertEquals(10, Constants.PAGE_ITEM_LIMIT)

        assertEquals("Marvel", Constants.CAROUSEL_MOVIE_VALUE)
        assertEquals("Batman", Constants.BATMAN_MOVIE_VALUE)
        assertEquals("movie", Constants.LATEST_MOVIE_VALUE)
        assertEquals("2022", Constants.LATEST_MOVIE_YEAR)

        assertEquals("imdbID", Constants.DETAILS_FIELD_IMDBID)
    }
}