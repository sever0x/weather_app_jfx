package com.weather.weather_app_jfx;

import javafx.scene.control.Alert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Helper {
    public String API_KEY = "041e61479b4cb2e92b34a1044e15afaa";

    public String getWeatherInfo(String address) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(address);
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String text;

            while ((text = bufferedReader.readLine()) != null) {
                content.append(text).append("\n");
            }
            bufferedReader.close();
        } catch (Exception ex) {
            showError();
        }

        return content.toString();
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input error");
        alert.setHeaderText("No such city found! Check input.");
        alert.showAndWait();
    }
}
