package com.example.airsciencesecurityinformation;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

//клас налаштувань повітря
public class AirSettings {

    private int pollutionLevel;
    private int humidityLevel;

    //метод що повертає поточні налаштування
    public void getSettings() {

    }
}

//клас-репозиторій для зв'язку застосунку з БД
public class AirSettingsRepository {

    private MongoDBConnection mongoDBConnection;

    //метод зберігання налаштувань до БД
    public void saveSettings(AirSettings airSettings) {

    }
}


public class MainActivity extends AppCompatActivity {

    private AirSettingsRepository airSettingsRepository;

    protected void manageAirSettings(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextAirPollution = findViewById(R.id.editTextAirPollution);
        final EditText editTextAirHumidity = findViewById(R.id.editTextAirHumidity);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        //при натисканні на кнопку відбувається отримання та валідація переданих налаштувань
        btnSubmit.setOnClickListener(view -> {
            // отримуємо значення з форми
            int pollutionLevel = Integer.parseInt(editTextAirPollution.getText().toString());
            int humidityLevel = Integer.parseInt(editTextAirHumidity.getText().toString());

            //проводимо валідацію отриманих значень
            int errorCode = validateSettings(pollutionLevel, humidityLevel);

            //якщо отримані дані не валідні - сповіщаємо науковця
            if (errorCode < 0) {
                String fieldName = getFieldNameByErrorCode(errorCode);
                showAlert("Error code: " + errorCode, fieldName + " value must be between 0 and 100.");
                return;
            }

            //якщо отримані дані валідні - зберігаємо їх у БД та сповіщаємо науковця
            airSettingsRepository.saveSettings(new AirSettings(pollutionLevel, humidityLevel));
            showSaveDialog();
        });
    }

    //метод валідації значень
    private int validateSettings(int pollutionLevel, int humidityLevel) {
        if (pollutionLevel < 0 || pollutionLevel > 100) {
            return -1;
        }

        if (humidityLevel < 0 || humidityLevel > 100) {
            return -2;
        }

        return 1;
    }

    //метод що відображає вікно при успішному сценарію
    private void showSaveDialog() {
        // Create an AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Settings Saved")
                .setMessage("Your settings have been saved.")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Close the dialog
                    dialog.dismiss();
                });

        // Show the AlertDialog
        builder.create().show();
    }

    //метод що відображає вікно при не успішному сценарію
    private void showAlert(String title, String message) {
        // Create an AlertDialog for displaying an error message
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    // Close the dialog
                    dialog.dismiss();
                });

        // Show the AlertDialog
        builder.create().show();
    }

    //отримання ім'я налаштування за кодом помилки
    private String getFieldNameByErrorCode(int errorCode) {
        String fieldName = null;
        switch (errorCode) {
            case -1:
                fieldName = "Pollution level";
                break;
            case -2:
                fieldName = "Humidity level";
                break;
            default:
                fieldName = "Internal Error";
        }
        return fieldName;
    }
}
