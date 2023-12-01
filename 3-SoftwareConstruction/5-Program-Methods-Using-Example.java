package com.example.airsciencesecurityinformation;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Main {


    public void test() {
        //Валідні значення
        int pollutionLevel = 25;
        int humidityLevel = 45;

        int errorCode = validateSettings(pollutionLevel, humidityLevel);
        //дані валідні, тому переходимо до іншої гілки логіки коду
        if (errorCode < 0) {
            String fieldName = getFieldNameByErrorCode(errorCode);
            showAlert("Error code: " + errorCode, fieldName + " value must be between 0 and 100.");
            return;
        }

        //дані пройшли валідацію, тому зберігаємо налаштування до БД
        airSettingsRepository.saveSettings(pollutionLevel, humidityLevel);

        //показуємо діалогове вікно, що дані збережено успішно
        showSaveDialog();

        //Невалідні значення №1
        int pollutionLevel = -1;
        int humidityLevel = 25;

        int errorCode = validateSettings(pollutionLevel, humidityLevel);
        //дані не валідні, тому переходимо до логіки екрану з помилкою, та завершаємо роботу методу
        if (errorCode < 0) {
            String fieldName = getFieldNameByErrorCode(errorCode);
            showAlert("Error code: " + errorCode, fieldName + " value must be between 0 and 100.");
            return;
        }


        //Невалідні значення №2
        int pollutionLevel = 25;
        int humidityLevel = 199;

        int errorCode = validateSettings(pollutionLevel, humidityLevel);
        //дані не валідні, тому переходимо до логіки екрану з помилкою, та завершаємо роботу методу
        if (errorCode < 0) {
            String fieldName = getFieldNameByErrorCode(errorCode);
            showAlert("Error code: " + errorCode, fieldName + " value must be between 0 and 100.");
            return;
        }

    }

}