package com.example.kingd.hello_world;


import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityUnitTestCase;

import com.example.kingd.hello_world.MainActivity;
import com.example.kingd.hello_world.PopupWindowActivity;
import com.example.kingd.hello_world.R;
import com.example.kingd.hello_world.showMorePayments;

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
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
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
public class ShowMorePaymentsTests extends ActivityUnitTestCase<showMorePayments>{

    public ShowMorePaymentsTests(){
        super(showMorePayments.class);
    }

    /**
     * Launches {@link MainActivity} for every test
     */

    // Given the user has been in the showmorepayments page
    @Rule
    public IntentsTestRule<showMorePayments> activityRule = new IntentsTestRule<>(showMorePayments.class);

    /**
     * Test if more payments is expandable
     */

    // When the user clicks the backtohomepage button
    @Test
    public void testExpandedListViewClickable() {
        // Then no errors shall be thrown because the button is clickable
        onView(withId(R.id.button4)).check(matches(isClickable()));
        // Then the used shall be navigated back to homepage
        onView(withId(R.id.button4)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }

}