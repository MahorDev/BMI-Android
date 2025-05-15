package com.devmahor.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String getAgeGroup(int age) {
        if (age >= 1 && age <= 3) return "Toddler";
        else if (age >= 4 && age <= 12) return "Child";
        else if (age >= 13 && age <= 17) return "Teenager";
        else if (age >= 18 && age <= 25) return "Young Adult";
        else if (age >= 26 && age <= 40) return "Adult";
        else if (age >= 41 && age <= 60) return "Middle-aged Adult";
        else if (age >= 61 && age <= 120) return "Senior";
        else return "Unknown";
    }

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

        EditText edtAge = findViewById(R.id.edtAge);
        EditText edtHeightFe = findViewById(R.id.edtHeightFe);
        EditText edtHeightIn = findViewById(R.id.edtHeightIn);
        EditText edtWeight = findViewById(R.id.edtWeight);
        TextView edtResult = findViewById(R.id.edtResult);
        TextView txtSuggestions = findViewById(R.id.txtSuggestions);
        Button edtButton = findViewById(R.id.edtButton);
        Button btnClear = findViewById(R.id.edtClear);

        edtButton.setOnClickListener(v -> {
            String ageStr = edtAge.getText().toString();
            String heightFeStr = edtHeightFe.getText().toString();
            String heightInStr = edtHeightIn.getText().toString();
            String weightStr = edtWeight.getText().toString();

            if (ageStr.isEmpty() || heightFeStr.isEmpty() || heightInStr.isEmpty() || weightStr.isEmpty()) {
                edtResult.setText("Please fill all fields.");
                return;
            }

            try {
                int age = Integer.parseInt(ageStr);
                int heightFe = Integer.parseInt(heightFeStr);
                int heightIn = Integer.parseInt(heightInStr);
                int weight = Integer.parseInt(weightStr);

                int totalHeight = heightFe * 12 + heightIn;
                double totalM = totalHeight * 0.0254;
                double bmi = weight / (totalM * totalM);

                if (age >= 18 && age <= 120) {
                    String ageGroup = getAgeGroup(age);

                    if (bmi < 16) {
                        edtResult.setText("Very Underweight (" + ageGroup + ")");
                        txtSuggestions.setText("- Increase protein intake\n- Add smoothies/snacks\n- Seek medical advice");
                    } else if (bmi >= 16 && bmi < 17) {
                        edtResult.setText("Severely Underweight (" + ageGroup + ")");
                        txtSuggestions.setText("- Add high-calorie healthy foods (nuts, cheese)\n- Consult a doctor\n- Avoid skipping meals");
                    } else if (bmi >= 17 && bmi < 18.5) {
                        edtResult.setText("Underweight (" + ageGroup + ")");
                        txtSuggestions.setText("- Eat healthy carbs\n- Do strength training\n- Increase meal portions");
                    } else if (bmi >= 18.5 && bmi <= 24.9) {
                        edtResult.setText("Normal Weight (" + ageGroup + ")");
                        txtSuggestions.setText("- Maintain exercise\n- Eat balanced meals\n- Stay hydrated");
                    } else if (bmi >= 25 && bmi <= 29.9) {
                        edtResult.setText("Overweight (" + ageGroup + ")");
                        txtSuggestions.setText("- Reduce sugar & fried food\n- Exercise daily\n- Use a calorie tracker");
                    } else if (bmi >= 30 && bmi <= 34.9) {
                        edtResult.setText("Obesity Class I (" + ageGroup + ")");
                        txtSuggestions.setText("- Consult a dietician\n- Focus on portion control\n- Join a fitness group");
                    } else if (bmi >= 35 && bmi <= 39.9) {
                        edtResult.setText("Obesity Class II (" + ageGroup + ")");
                        txtSuggestions.setText("- Follow medical weight loss plan\n- Avoid crash diets");
                    } else if (bmi >= 40 && bmi <= 49.9) {
                        edtResult.setText("Obesity Class III (" + ageGroup + ")");
                        txtSuggestions.setText("- Immediate medical help\n- Consider structured programs");
                    } else if (bmi >= 50 && bmi <= 59.9) {
                        edtResult.setText("Super Obese (" + ageGroup + ")");
                        txtSuggestions.setText("- Work with medical team\n- Consider bariatric counseling");
                    } else {
                        edtResult.setText("Hyper Obese (" + ageGroup + ")");
                        txtSuggestions.setText("- Seek emergency hospital care\n- Surgery may be needed");
                    }
                } else if (age >= 1 && age < 18) {
                    String ageGroup = getAgeGroup(age);

                    if (age <= 3) {
                        if (bmi < 14) {
                            edtResult.setText("Underweight (" + ageGroup + ")");
                            txtSuggestions.setText("- Breastfeeding/full-fat milk\n- Mashed dal, khichdi\n- Pediatric guidance");
                        } else if (bmi <= 20) {
                            edtResult.setText("Healthy Weight (" + ageGroup + ")");
                            txtSuggestions.setText("- Nutritious meals\n- Active play\n- Pediatric checkups");
                        } else if (bmi <= 24) {
                            edtResult.setText("Slightly Overweight (" + ageGroup + ")");
                            txtSuggestions.setText("- Limit sugary milk\n- Encourage activity");
                        } else {
                            edtResult.setText("Overweight (" + ageGroup + ")");
                            txtSuggestions.setText("- Pediatrician consultation\n- Avoid force-feeding");
                        }
                    } else {
                        if (bmi < 14) {
                            edtResult.setText("Underweight (" + ageGroup + ")");
                            txtSuggestions.setText("- Regular meals with ghee, milk\n- Pediatric growth checkups");
                        } else if (bmi <= 20) {
                            edtResult.setText("Healthy Weight (" + ageGroup + ")");
                            txtSuggestions.setText("- Encourage play\n- Avoid junk food");
                        } else if (bmi <= 24) {
                            edtResult.setText("Slightly Overweight (" + ageGroup + ")");
                            txtSuggestions.setText("- More outdoor time\n- Cut fried snacks");
                        } else {
                            edtResult.setText("Overweight/Obese (" + ageGroup + ")");
                            txtSuggestions.setText("- Pediatric consultation\n- Plan physical activities");
                        }
                    }
                } else {
                    edtResult.setText("Invalid Age");
                    txtSuggestions.setText("");
                }

            } catch (NumberFormatException e) {
                edtResult.setText("Please enter valid numbers.");
                txtSuggestions.setText("");
            }
        });

        btnClear.setOnClickListener(v -> {
            edtAge.setText("");
            edtHeightFe.setText("");
            edtHeightIn.setText("");
            edtWeight.setText("");
            edtResult.setText("Clear Successful");
            txtSuggestions.setText("");
        });
    }
}
