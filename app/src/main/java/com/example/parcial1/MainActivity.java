package com.example.parcial1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin, buttonRegister;
    private CheckBox checkBoxTerms, checkBoxRemember;
    private TextView errorEmail, errorPassword;
    private boolean emailErrorShown = false;
    private boolean passwordErrorShown = false;
    private boolean termsErrorShown = false;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private boolean isUserRegistered =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        checkBoxRemember = findViewById(R.id.checkBoxRemember);
        errorEmail = findViewById(R.id.errorEmail);
        errorPassword = findViewById(R.id.errorPassword);

        
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateFields();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        editTextEmail.addTextChangedListener(textWatcher);
        editTextPassword.addTextChangedListener(textWatcher);


        checkBoxTerms.setOnCheckedChangeListener((buttonView, isChecked) -> {
            validateFields();
        });


        buttonLogin.setOnClickListener(v -> {
            if (validateFields()) {
                if (isUserRegistered) {
                    Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Debes registrarte primero", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonRegister.setOnClickListener(v -> {
            if (validateFields()) {
                isUserRegistered = true;
                Toast.makeText(MainActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateFields() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        boolean isEmailValid = Pattern.matches(EMAIL_PATTERN, email);
        boolean isPasswordValid = password.length() >= 8;

        boolean isValid = true;


        if (email.isEmpty()) {
            errorEmail.setText("Rellena correctamente el campo de correo");
            errorEmail.setVisibility(View.VISIBLE);
            emailErrorShown = true;
            isValid = false;
        } else if (!isEmailValid) {
            errorEmail.setText("Correo electrónico no válido");
            errorEmail.setVisibility(View.VISIBLE);
            emailErrorShown = true;
            isValid = false;
        } else {
            errorEmail.setVisibility(View.GONE);
            emailErrorShown = false;
        }


        if (password.isEmpty()) {
            errorPassword.setText("Rellena correctamente el campo de contraseña");
            errorPassword.setVisibility(View.VISIBLE);
            passwordErrorShown = true;
            isValid = false;
        } else if (!isPasswordValid){
            errorPassword.setText("La contraseña debe tener al menos 8 caracteres");
            errorPassword.setVisibility(View.VISIBLE);
            passwordErrorShown = true;
            isValid = false;
        } else{
            errorPassword.setVisibility(View.GONE);
            passwordErrorShown = false;
        }


        if (!checkBoxTerms.isChecked()) {
            if (!termsErrorShown) {
                errorPassword.setText("Debes aceptar los términos y condiciones");
                errorPassword.setVisibility(View.VISIBLE);
                termsErrorShown = true;
            }
            isValid = false;
        } else {
            termsErrorShown = false;
        }

        buttonLogin.setEnabled(isValid);
        return isValid;
    }
}