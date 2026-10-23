
package com.example.firstform;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find all input fields
        EditText username = findEditTextByHint("Username");
        EditText fullname = findEditTextByHint("Fullname");
        EditText country = findEditTextByHint("Country");
        EditText email = findEditTextByHint("Email");
        EditText phone = findEditTextByHint("Phone number");
        EditText password = findEditTextByHint("Password");

        RadioButton radio1 = findViewById(R.id.radioButton);
        RadioButton radio2 = findViewById(R.id.radioButton2);

        CheckBox checkBox = findViewById(R.id.checkBox);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {

            String usernameText =
                    username.getText().toString().trim();

            String fullnameText =
                    fullname.getText().toString().trim();

            String countryText =
                    country.getText().toString().trim();

            String emailText =
                    email.getText().toString().trim();

            String phoneText =
                    phone.getText().toString().trim();

            String passwordText =
                    password.getText().toString();

            String gender = "Not selected";

            if (radio1.isChecked()) {
                gender = radio1.getText().toString();
            } else if (radio2.isChecked()) {
                gender = radio2.getText().toString();
            }

            String terms = checkBox.isChecked()
                    ? "Accepted"
                    : "Not accepted";

            String message =
                    "Username: " + usernameText +
                            "\nFullname: " + fullnameText +
                            "\nCountry: " + countryText +
                            "\nEmail: " + emailText +
                            "\nPhone number: " + phoneText +
                            "\nGender: " + gender +
                            "\nPassword: " + passwordText +
                            "\nTerms and Conditions: " + terms;

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Registration Details")
                    .setMessage(message)
                    .setPositiveButton("OK", (dialog, which) ->
                            dialog.dismiss())
                    .show();
        });
    }

    // Find an EditText using its hint
    private EditText findEditTextByHint(String hint) {
        android.view.ViewGroup root =
                findViewById(R.id.main);

        return findEditTextRecursive(root, hint);
    }

    private EditText findEditTextRecursive(
            android.view.ViewGroup parent, String hint) {

        for (int i = 0; i < parent.getChildCount(); i++) {
            android.view.View child = parent.getChildAt(i);

            if (child instanceof EditText) {
                EditText editText = (EditText) child;

                if (hint.contentEquals(
                        editText.getHint() == null
                                ? ""
                                : editText.getHint().toString())) {
                    return editText;
                }
            }

            if (child instanceof android.view.ViewGroup) {
                EditText result = findEditTextRecursive(
                        (android.view.ViewGroup) child, hint);

                if (result != null) {
                    return result;
                }
            }
        }

        throw new IllegalStateException(
                "EditText not found: " + hint);
    }
}
