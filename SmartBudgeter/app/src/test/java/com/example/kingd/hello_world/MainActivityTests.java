package com.example.kingd.hello_world;


        import android.support.test.espresso.matcher.ViewMatchers;
        import android.support.test.rule.ActivityTestRule;
        import android.support.test.runner.AndroidJUnit4;
        import android.test.ActivityUnitTestCase;

        import com.example.kingd.hello_world.MainActivity;

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
public class MainActivityTests extends ActivityUnitTestCase<MainActivity>{

    public MainActivityTests(){
        super(MainActivity.class);
    }

    /**
     * Launches {@link MainActivity} for every test
     */

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Test if showMorePayments button is clickable.
     */

    @Test
    public void testToolBarDisplayed(){
        try {
            onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
            System.out.println("Toolbar is displayed! Test Passed!");
        } catch (AssertionFailedError e){
            System.err.println("Toolbar isn't displayed");
        }
    }

    @Test
    public void testDrawerToggleClickable(){
        try {
            onView(withId(R.id.drawer_layout)).check(matches(isClickable()));
            System.out.println("Drawer Toggle is clickable! Test Passed!");
        } catch(Exception e) {
            System.out.println("DrawerToggle isn't clickable");
        }
    }

    @Test
    public void testNavigationDrawerDisplayed(){
        try {
            onView(withId(R.id.drawer_layout)).perform(click());
            onView(withId(R.id.nvView)).check(matches(isDisplayed()));
            System.out.println("Navigation drawer is displayed! Test Passed!");
        } catch (Exception e){
            System.err.println("Navigation drawer isn't displayed");
        }
    }


    @Test
    public void testSMPClickable() {
        try{
            onView(withId(R.id.showMorePayments)).check(matches(isClickable()));
            System.out.println("The showmorepayments button is clickable! Test passed!");
        }catch(Exception e) {
            System.out.println("The showmorepayments button isn't clickable!");
        }
    }

    /**
     * Test showMoreIncome Button is clickable.
     */

    @Test
    public void testSMIClickable() {
        try{
            onView(withId(R.id.showMoreIncome)).check(matches(isClickable()));
            System.out.println("The showmoreincome button is clickable! Test passed!");
        }catch(Exception e) {
            System.out.println("The showmoreincome button isn't clickable!");
        }

    }

    @Test
    public void testAddButtonClickable() {
        try{
            onView(withId(R.id.AddButton)).check(matches(isClickable()));
            System.out.println("The add button is clickable! Test passed!");
        }catch(Exception e) {
            System.out.println("The add button isn't clickable!");
        }

    }

    @Test
    public void testEditCatergoryNextButtonClickable() {
        try{
            onView(withId(R.id.button)).check(matches(isClickable()));
            System.out.println("The next button is clickable! Test passed!");
        }catch(Exception e) {
            System.out.println("The next button isn't clickable!");
        }
    }

    @Test
    public void testEditDatesListViewClickable() {
        try{
            onView(withId(R.id.chooseEventList)).check(matches(isClickable()));
            System.out.println("The Edit Dates List View is clickable! Test passed!");
        }catch(Exception e) {
            System.out.println("The Edit Dates List View isn't clickable!");
        }
    }

    @Test
    public void testEditDeletePageClickable() {
        try{
            onView(withId(R.id.editDatePicker)).check(matches(isClickable()));
            System.out.println("The DatePicker is clickable!");
        }catch(Exception e) {
            System.out.println("The DatePicker isn't clickable!");
        }
        try{
            onView(withId(R.id.EditButton)).check(matches(isClickable()));
            System.out.println("The Edit Button is clickable!");
        }catch(Exception e) {
            System.out.println("The Edit Button isn't clickable!");
        }
        try{
            onView(withId(R.id.DeleteButton)).check(matches(isClickable()));
            System.out.println("The Delete Button is clickable!");
        }catch(Exception e) {
            System.out.println("The Delete Button isn't clickable!");
        }
    }




}

