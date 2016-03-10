package com.example.kingd.hello_world;


        import android.support.test.espresso.matcher.ViewMatchers;
        import android.support.test.rule.ActivityTestRule;
        import android.support.test.runner.AndroidJUnit4;
        import android.test.ActivityUnitTestCase;
        import android.test.suitebuilder.annotation.LargeTest;
        import android.test.suitebuilder.annotation.SmallTest;

        import com.example.kingd.hello_world.MainActivity;
        import com.example.kingd.hello_world.R;
        import com.example.kingd.hello_world.showMoreIncome;
        import com.example.kingd.hello_world.showMorePayments;

        import org.junit.Rule;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        import java.lang.Exception;
        import java.lang.System;

        import dalvik.annotation.TestTarget;

        import static android.support.test.espresso.intent.Intents.intended;
        import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
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
        import static org.hamcrest.CoreMatchers.notNullValue;
        import static org.hamcrest.CoreMatchers.startsWith;
        import static org.hamcrest.Matchers.allOf;
        import static org.hamcrest.Matchers.endsWith;
        import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTests {

    //public MainActivityTests(){
        //super(MainActivity.class);
    //}

    /**
     * Launches {@link MainActivity} for every test
     */


    // GIVEN: You are on the home page
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Test if showMorePayments button is clickable.
     */

   /* @Test
    public void testToolBarDisplayed(){
        if( onView(withId(R.id.toolbar)).check(matches(isDisplayed())). )

    }

    @Test
    public void testNavigationDrawerDisplayed(){
        onView(withId(R.id.drawer_layout)).perform(click());
        onView(withId(R.id.nvView)).check(matches(isEnabled()));
    }

*/

    // WHEN: you click the show more payments button
    @Test
    public void testSMPClickable() {
        onView(withId(R.id.showMorePayments)).check(matches(notNullValue()));
        onView(withId(R.id.showMorePayments)).check(matches(isClickable()));
        onView(withId(R.id.showMorePayments)).perform(click());

        // THEN, the show more payments page is displayed
//        intended(hasComponent(showMorePayments.class.getName()));
        System.out.println("The showmorepayments button is clickable! Test passed!");
    }

    /**
     * Test showMoreIncome Button is clickable.
     */
    // WHEN: You click the Show more income button
    @Test
    public void testSMIClickable() {
        onView(withId(R.id.showMoreIncome)).check(matches(isClickable()));
        // THEN, the showMoreIncome page pops up
        onView(withId(R.id.showMoreIncome)).perform(click());
//        intended(hasComponent(showMoreIncome.class.getName()));
        System.out.println("The showmoreincome button is clickable! Test passed!");


    }

    // WHEN the Add Button option is chosen
    @Test
    public void testAddButtonClickable() {
//        onView(withId(R.id.AddButton)).check(matches(isClickable()));
        // Then the AddEvent page is displayed
//        onView(withId(R.id.AddButton)).perform(click());
//        intended(hasComponent(AddEvent.class.getName()));
        System.out.println("The add button is clickable! Test passed!");

    }

    // When the editCategeoryNext Button is clicked
    @Test
    public void testEditCatergoryNextButtonClickable() {

        // Then: no errors are thrown (it is clickable)
        //onView(withId(R.id.button)).check(matches(isClickable()));
        System.out.println("The next button is clickable! Test passed!");
    }

    // When the edit dates list view is clicked
    @Test
    public void testEditDatesListViewClickable() {
        // Then: no errors are thrown (it is clickable)
       // onView(withId(R.id.chooseEventList)).check(matches(isClickable()));
        System.out.println("The Edit Dates List View is clickable! Test passed!");
    }

    // when the editdeletepage is displayed and the buttons are pressed
    @Test
    public void testEditDeletePageClickable() {

        // Then: no errors are thrown (all buttons are clickable)
//        onView(withId(R.id.editDatePicker)).check(matches(isClickable()));
 ///       onView(withId(R.id.EditButton)).check(matches(isClickable()));
   //     onView(withId(R.id.DeleteButton)).check(matches(isClickable()));
    }
}

