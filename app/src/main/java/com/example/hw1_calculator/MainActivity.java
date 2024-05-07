package com.example.hw1_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    private EditText editTextNum1, editTextNum2;
    private TextView textViewResult;
    private Spinner spinner_signs;
    private Button calculateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinner_signs);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource
                        (this,
                                R.array.signs,
                                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        textViewResult = findViewById(R.id.textViewResult);
        spinner_signs = findViewById(R.id.spinner_signs);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int num1 = Integer.parseInt(editTextNum1.getText().toString());
                    int num2 = Integer.parseInt(editTextNum2.getText().toString());


                    String operation = spinner_signs.getSelectedItem().toString();


                    int result = 0;
                    switch (operation) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {

                                Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();

                            }
                            break;
                        case "^":
                            result = (int) Math.pow(num1, num2);
                            break;
                    }


                    textViewResult.setText(String.valueOf(result));
                } catch (NumberFormatException e) {

                    textViewResult.setText("Please enter valid numbers.");
                }
            }
        });
    }
}