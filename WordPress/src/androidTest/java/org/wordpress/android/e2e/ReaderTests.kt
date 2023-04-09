package org.wordpress.android.e2e

import android.Manifest.permission
import androidx.test.rule.GrantPermissionRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.wordpress.android.e2e.pages.ReaderPage
import org.wordpress.android.support.BaseTest

@HiltAndroidTest
class ReaderTests : BaseTest() {
    @JvmField @Rule
    var mRuntimeImageAccessRule = GrantPermissionRule.grant(permission.WRITE_EXTERNAL_STORAGE)

    @Before
    fun setUp() {
        logoutIfNecessary()
        wpLogin()
        ReaderPage().go()
    }

    @Test
    fun e2eNavigateThroughPosts() {
        ReaderPage()
            .tapFollowingTab()
            .openPost(TITLE_COACHING_POST)
            .verifyPostDisplayed(TITLE_COACHING_POST)
            .slideToPreviousPost()
            .verifyPostDisplayed(TITLE_COMPETITION_POST)
            .slideToNextPost()
            .verifyPostDisplayed(TITLE_COACHING_POST)
            .goBackToReader()
    }

    @Test
    fun e2eLikePost() {
        ReaderPage()
            .tapFollowingTab()
            .openPost(TITLE_COACHING_POST)
            .likePost()
            .verifyPostLiked()
            .unlikePost()
            .verifyPostNotLiked()
            .goBackToReader()
    }

    @Test
    fun e2eBookmarkPost() {
        ReaderPage()
            .openBlog(TITLE_BLOG)
            .bookmarkPost()
            .verifyPostBookmarked()
            .goBackToReader()
    }

    companion object {
        private const val TITLE_BLOG = "Technical World"
        private const val TITLE_COACHING_POST = "Let's check out the coaching team!"
        private const val TITLE_COMPETITION_POST = "Let's focus on the competition."
    }
}
