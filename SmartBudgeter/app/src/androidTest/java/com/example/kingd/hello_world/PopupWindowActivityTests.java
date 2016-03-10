package com.example.kingd.hello_world;


import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityUnitTestCase;

import com.example.kingd.hello_world.MainActivity;
import com.example.kingd.hello_world.PopupWindowActivity;
import com.example.kingd.hello_world.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.Exception;
import java.lang.System;

import dalvik.annotation.TestTarget;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class PopupWindowActivityTests extends ActivityUnitTestCase<PopupWindowActivity>{

    public PopupWindowActivityTests(){
        super(PopupWindowActivity.class);
    }

    /**
     * Launches {@link MainActivity} for every test
     */

    // Given that the user has never used the app, and the pop-up window pops up
    @Rule
    public ActivityTestRule<PopupWindowActivity> activityRule = new ActivityTestRule<>(PopupWindowActivity.class);

    /**
     * Test if showMorePayments button is clickable.
     */

    // When the next button on the popup window is clicked
    @Test
    public void testNextButtonClickable() {
        // then, no errors are thrown because the next button is clickable
            onView(withId(R.id.button2)).check(matches(isClickable()));
            System.out.println("The Popup Window Activity button is clickable! Test passed!");

    }

}