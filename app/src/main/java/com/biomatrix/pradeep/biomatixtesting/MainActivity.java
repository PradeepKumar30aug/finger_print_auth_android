package com.biomatrix.pradeep.biomatixtesting;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import android.os.Bundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.fragment.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "Tap On Button to Match the Fingerprint",Toast.LENGTH_SHORT).show();
        Executor newExecutor = Executors.newSingleThreadExecutor();
        FragmentActivity activity = this;
        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                } else {
                    // if device not find any fingerprint sensors ..
                    Log.d(TAG, "An unrecoverable error occurred");
                }
            }
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.d(TAG, "Fingerprint recognised successfully");
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d(TAG, "Fingerprint not recognised");
            }
        });
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .build();
        findViewById(R.id.launchAuthentication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBiometricPrompt.authenticate(promptInfo);
            }
        });
    }
}
