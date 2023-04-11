package com.ez.dotarate.profile

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ez.data.repository.StubUserRepository
import com.ez.domain.model.Profile
import com.ez.domain.model.User
import com.ez.domain.model.UserResponse
import com.ez.domain.model.WinsAndLosses
import com.ez.dotarate.view.TestFragmentUtils
import com.ez.dotarate.view.profile.ProfileFragment
import com.ez.dotarate.view.profile.ProfileViewModel
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import com.ez.dotarate.R
import io.mockk.every
import io.mockk.spyk
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest {

    private val user = User(
        id = 1,
        leaderboard_rank = null,
        name = "Name",
        avatarUrl = "url",
        wins = 100,
        losses = 1,
        rankId = 15
    )

    private var navController: NavController = mockk()
    private var application: Application = mockk()
    private var repository: StubUserRepository = spyk {
        every { getUser() } returns MutableLiveData(user)
    }
    private val profileViewModel: ProfileViewModel = ProfileViewModel(application, repository)
    private var profileFragment: ProfileFragment = ProfileFragment(profileViewModel)

    @Test
    fun testProfileDataIsOkay() {
        val fragmentArgs = bundleOf("test" to 0)
        val scenario = launchMyFragmentScenario(fragmentArgs)

        onView(withId(R.id.winsCount)).check(matches(withText("100")))
        onView(withId(R.id.lossesCount)).check(matches(withText("1")))
        onView(withId(R.id.winRateCount)).check(matches(withText("99.01%")))
        onView(withId(R.id.ivRank)).check(matches(withDrawable(R.drawable.herald_5)))
        onView(withId(R.id.tvLeaderBoardRank)).check(matches(withText("")))
    }

    private fun launchMyFragmentScenario(bundle: Bundle?): FragmentScenario<ProfileFragment> =
        TestFragmentUtils.launchFragmentScenario(
            bundle,
            profileFragment,
            navController,
            Lifecycle.State.RESUMED
        )

    private fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("ImageView with drawable same as drawable with id $id")
        }

        override fun matchesSafely(view: View): Boolean {
            val context = view.context
            val expectedBitmap = context.getDrawable(id)?.toBitmap()

            return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
        }
    }
}