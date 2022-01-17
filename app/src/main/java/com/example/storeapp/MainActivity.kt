package com.example.storeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.service.controls.ControlsProviderService
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)


        val newExecutor: Executor = Executors.newSingleThreadExecutor()

        val activity: FragmentActivity = this

        val myBiometricPrompt = androidx.biometric.BiometricPrompt(activity,
            newExecutor,
            object : androidx.biometric.BiometricPrompt.AuthenticationCallback() {


                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode == androidx.biometric.BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    } else {


                        Log.d(ControlsProviderService.TAG, "An unrecoverable error occurred")
                    }
                }


                override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)


                    Log.d(ControlsProviderService.TAG, "Fingerprint recognised successfully")
                }


                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    finish()
                    Log.d(ControlsProviderService.TAG, "Fingerprint not recognised")
                }
            })


        val promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Safe Login")
            .setSubtitle("Please Authenticate With Fingerprint")
            .setDescription("Authenticate Your Finger Print To Access Our App")
            .setNegativeButtonText("Cancel")
            .build()

        myBiometricPrompt.authenticate(promptInfo)


        val SignUpBtn = findViewById<Button>(R.id.sign_up)
        val LogInBtn = findViewById<Button>(R.id.log_in)
        val db = DBHelper(this, null)

        LogInBtn.setOnClickListener {

            val username = findViewById<EditText>(R.id.User_name).text.toString()
            val pass = findViewById<EditText>(R.id.User_password).text.toString()
            val cursor = db.getUserData(username)

            if (cursor!!.moveToFirst()) {
                var uses: String =
                    cursor.getString(cursor.getColumnIndex(DBHelper.User_Name.toString()))
                var pas: String =
                    cursor.getString(cursor.getColumnIndex(DBHelper.User_password.toString()))

                if (username == uses && pass == pas) {

                    Toast.makeText(this, uses + " : " + pas, Toast.LENGTH_LONG).show()
                    cursor.close()
                    intent = Intent(this, homePage::class.java)
                    intent.putExtra("UserName", username)
                    startActivity(intent)
                } else {
                    while (cursor.moveToNext()) {
                        uses =
                            cursor.getString(cursor.getColumnIndex(DBHelper.User_Name.toString()))
                                .toString()
                        pas =
                            cursor.getString(cursor.getColumnIndex(DBHelper.User_password.toString()))
                                .toString()

                        if (username == uses && pass == pas) {

                            Toast.makeText(this, uses + " : " + pas, Toast.LENGTH_LONG).show()
                            cursor.close()

                            intent = Intent(this, homePage::class.java)
                            intent.putExtra("UserName", username)
                            startActivity(intent)
                            break
                            Toast.makeText(
                                this,
                                "Wrong password/username or not found",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                }

            } else Toast.makeText(this, "NO DATA", Toast.LENGTH_LONG)
        }

        SignUpBtn.setOnClickListener {

            intent = Intent(this, sign_up::class.java)
            startActivity(intent)
        }

    }
}