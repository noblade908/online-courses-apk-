package com.example.ourapp;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = 28)
public class MainActivityTest {

    FirebaseAuth auth;

    @Before
    public void init() {
        Context appContext = RuntimeEnvironment.application;
        FirebaseApp.initializeApp(appContext);
    }

    @Test
    public void logi() {

        boolean failure = FirebaseAuth.getInstance().signInWithEmailAndPassword("coolio@coolio908.com","pop098765").isCanceled();
        assertEquals("Login successfull", false, failure);
    }

    @Test
    public void shouldShowEmailErrorWhenEmailInputIsEmpty() {
        ActivityController<MainActivity> activityController = Robolectric.buildActivity(MainActivity.class);
        activityController.setup();

        // for passing into the logi funciton
        View mockView = Mockito.mock(View.class);

        // get the reference to main activity
        MainActivity activity = activityController.get();

        // trigger login
        activity.Logi(mockView);

        // get input
        EditText emailText = activity.findViewById(R.id.etEmail);
        // emailText.setText("emaild@gmail.com");

        String expectedError = "PLease enter an email";
        // validate input error is shown
        String actualError = emailText.getError().toString();

        // asser equality
        assertEquals("Correct Email error is not shown", expectedError, actualError);
    }

    @Test
    public void shouldShowPasswordErrorWhenPasswordInputIsEmpty() {
        ActivityController<MainActivity> activityController = Robolectric.buildActivity(MainActivity.class);
        activityController.setup();

        // for passing into the logi funciton
        View mockView = Mockito.mock(View.class);

        // get the reference to main activity
        MainActivity activity = activityController.get();

        EditText emailText = activity.findViewById(R.id.etEmail);
        emailText.setText("emaild@gmail.com");

        EditText psswrdText = activity.findViewById(R.id.etPass);

        // trigger login
        activity.Logi(mockView);

        String expectedError = "please en a password";
        // validate input error is shown
        String actualError = psswrdText.getText().toString();

        // assert equality
        assertNotEquals("Correct Password error is not shown", expectedError, actualError);

    }
    @Test
    public void createAccount() {
    }

    @Test
    public void forgotpass() {
    }
}