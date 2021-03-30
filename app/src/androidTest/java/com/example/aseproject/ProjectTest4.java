package com.example.aseproject;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ProjectTest4 {

    private static final String VALID_MESSAGE = "Hello World!";
    private static final String INVALID_NUMBER = "155135";
    private static final String INVALID_EMAIL = "A@B";
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void clickOnBackButton(){
        onView(withId(R.id.et1))
                .perform(typeText(VALID_MESSAGE), closeSoftKeyboard());
        onView(withId(R.id.sendbutton1)).perform(click());

        onView(withId(R.id.et2))
                .perform(typeText(INVALID_NUMBER), closeSoftKeyboard());

        onView(withId(R.id.sendbutton2)).perform(click());

        onView(withId(R.id.et2))
                .check(matches(hasErrorText(
                        MainActivity2.ErrorMessage)));


        onView(withId(R.id.et2))
                .perform(clearText())
                .perform(click())
                .perform(typeText(INVALID_EMAIL), closeSoftKeyboard());

        onView(withId(R.id.sendbutton2)).perform(click());

        onView(withId(R.id.et2))
                .check(matches(hasErrorText(
                        MainActivity2.ErrorMessage)));


    }

}
