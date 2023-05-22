package com.shubham.groovy

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PlaylistFeatureInstrumentationTest {

    // Writing first espresso test

    // The first thing that we need to do is to tell our test to target our "MainActivity".

    val mActivityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get // Now we have told the espresso that it needs to initialise and kill the activity
    // after the test has finished.



    @Test // Test is going to check if we're displaying "Playlist" as title on the actionbar.
    fun displayScreenTitle() {

        assertDisplayed("Playlists")
    }
    
    
    // Now we want to display a list of playlist in our HomeScreen
    // so let's write a failing acceptance test first
    @Test
    fun displaysListOfPlaylist() {

        // This system is not going to pass this test even if we have whole system in place, that's because
        // we're doing these assertion at first millisecond as we open up our application
        // so for now we're making thread sleep until our recyclerview loads up
        Thread.sleep(4000) // This is actually an anti-pattern, cause this might make the test fail without
        // the code being wrong, for ex: if our http call for fetching the data and loading into revyvlerview
        // takes 5000 ms then this test will fail without reason.
        // later on we'll refactor this implementation and we'll use espresso idling resource, this will make it
        // wait as long as http request needs the time.

        // Now we need to asset that our list is displaying 10 playlist (as per our requirement)
        // means we need to check if the recyclerview is having 10 items.
        assertRecyclerViewItemCount(R.id.playlist_list, 10)

        // Now we also need to make sure that each item of recyclerview is displaying an image, title & category
        // correctly.
        // Asserting the ListViews with espresso library is one of the most difficult things to do.
        // you can google for some utility code to make the Asserting the ListViews with espresso library a bit
        // easy, but for now we're going to copy some utility functions for this.

        // This might not be the most accurate as in case of scrollable items some items might not be available
        // right away until we scroll the layout. So in this case we're going to check list entries.
        // So we'll test we have the right list items in the right order  with the right data.

        onView(allOf(withId(R.id.playlist_name), isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_category), isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 0))))
            .check(matches(withDrawable(R.mipmap.playlist)))
            .check(matches(isDisplayed()))
    }


    // So here we have to pass the "Parent View", so parent view can be a root layout, so in our case it's going
    // to be a list(Recyclerview) & then from that parent view we're accessing the end child.
    // for ex: if we pass second parameter of this function as 0 then we're going to retrieve the first item of
    // our list.
    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }


}