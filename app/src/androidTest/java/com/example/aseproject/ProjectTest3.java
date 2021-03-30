package com.example.aseproject;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.speech.RecognizerIntent;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest


public class ProjectTest3 {


    private static final String VALID_MESSAGE = "Hello World!";

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule =
            new IntentsTestRule<>(MainActivity.class, false,
                    true);

    @Before
    public void setUp() {
        Intent data = getResultIntent();
        final Instrumentation.ActivityResult result = new
                Instrumentation.ActivityResult(Activity.RESULT_OK, data);
        intending(hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)).respondWith(result);
    }

    private Intent getResultIntent() {
        final ArrayList<String> results = new ArrayList<>();
        results.add(VALID_MESSAGE);
        Intent intent = new Intent();
        intent.putStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS, results);
        return intent;
    }


    @Test
    public void checkIntent() {

        /*onView(withId(R.id.et1))
                .perform(typeText(VALID_MESSAGE), closeSoftKeyboard());*/
        //onView(withText(R.id.mic)).check(matches(isClickable()));
        onView(withId(R.id.mic)).perform(click());

        intended(allOf(
                hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                , hasExtraWithKey(RecognizerIntent.EXTRA_LANGUAGE_MODEL)
        ));

        onView(withId(R.id.et1)).check(matches(withText(VALID_MESSAGE)));



    }
}


