package com.example.kingd.hello_world;


import android.content.ComponentName;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityUnitTestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class HomePageFragmentTest extends ActivityUnitTestCase<MainActivity>{

    public HomePageFragmentTest(){
        super(MainActivity.class);
    }

    // Given the user has used the app before
    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);


    // When the user is viewing the home page
    // Then the user can see the Balance, Next Payment, and Next Income
    @Test
    public void BalanceIsShown() {
        onView(withId(R.id.balanceText)).check(matches(isDisplayed()));
        onView(withId(R.id.balanceField)).check(matches(isDisplayed()));
    }
    @Test
    public void NextPaymentIsShown() {
        onView(withId(R.id.NextPayment)).check(matches(isDisplayed()));
        onView(withId(R.id.dateText)).check(matches(isDisplayed()));
        onView(withId(R.id.dateField)).check(matches(isDisplayed()));
        onView(withId(R.id.categoryText)).check(matches(isDisplayed()));
        onView(withId(R.id.categoryField)).check(matches(isDisplayed()));
        onView(withId(R.id.amountText)).check(matches(isDisplayed()));
        onView(withId(R.id.amountField)).check(matches(isDisplayed()));
        onView(withId(R.id.notesText)).check(matches(isDisplayed()));
        onView(withId(R.id.notesField)).check(matches(isDisplayed()));
    }
    @Test
    public void NextIncomeIsShown() {
        onView(withId(R.id.nextIncome)).check(matches(isDisplayed()));
        onView(withId(R.id.dateText2)).check(matches(isDisplayed()));
        onView(withId(R.id.dateField2)).check(matches(isDisplayed()));
        onView(withId(R.id.categoryText2)).check(matches(isDisplayed()));
        onView(withId(R.id.categoryField2)).check(matches(isDisplayed()));
        onView(withId(R.id.amountText2)).check(matches(isDisplayed()));
        onView(withId(R.id.amountField2)).check(matches(isDisplayed()));
        onView(withId(R.id.notesText2)).check(matches(isDisplayed()));
        onView(withId(R.id.notesField2)).check(matches(isDisplayed()));
    }

    // When the user clicks on the show more payments button
    // Then the show more payments page opens
    @Test
    public void ShowMorePaymentsButton() {
        onView(withId(R.id.showMorePayments)).check(matches(isDisplayed()));    // Find button
        onView(withId(R.id.showMorePayments)).check(matches(isClickable()));    // Check if clickable
        onView(withId(R.id.showMorePayments)).perform(click()); // Perform click
        intended(hasComponent(showMorePayments.class.getName()));   // Opens more payments page
    }

    // When the user clicks on the show more income button
    // Then the show more income page opens
    @Test
    public void ShowMoreIncomeButton() {
        onView(withId(R.id.showMoreIncome)).check(matches(isDisplayed()));  // Find button
        onView(withId(R.id.showMoreIncome)).check(matches(isClickable()));  // Check if clickable
        onView(withId(R.id.showMoreIncome)).perform(click()); // Perform click
        intended(hasComponent(showMoreIncome.class.getName()));   // Opens more income page
    }

}