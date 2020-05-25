package com.example.ourapp;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
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
public class forgetpasswordTest {

    FirebaseAuth auth;

    @Before
    public void init() {
        Context appContext = RuntimeEnvironment.application;
        FirebaseApp.initializeApp(appContext);
    }

    @Test
    public void onClick() {
    }
}



