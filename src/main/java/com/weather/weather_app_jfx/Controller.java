package com.weather.weather_app_jfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller extends Helper {

    @FXML
    public TextField city;

    @FXML
    public Button getInfo;

    @FXML
    public Text humidity;

    @FXML
    public Text pressure;

    @FXML
    public Text temp;

    @FXML
    public Text temp_feel;

    @FXML
    public Text temp_max;

    @FXML
    public Text temp_min;

    @FXML
    void initialize() {
        getInfo.setOnAction(event -> setWeather());
        city.setOnAction(event -> setWeather());
    }

    private void setWeather() {
        if (!city.getText().equals("")) {
            String output = getWeatherInfo(
                    "https://api.openweathermap.org/data/2.5/weather?q="
                            + city.getText().trim()
                            + "&appid=" + API_KEY + "&units=metric"
            );
            if (!output.isEmpty()) {
                JSONObject jsonObject = new JSONObject(output);
                temp.setText("Temperature: " + jsonObject.getJSONObject("main").getDouble("temp") + " °C");
                temp_feel.setText("Feel like: " + jsonObject.getJSONObject("main").getDouble("feels_like") + " °C");
                temp_min.setText("Minimum: " + jsonObject.getJSONObject("main").getDouble("temp_min") + " °C");
                temp_max.setText("Maximum: " + jsonObject.getJSONObject("main").getDouble("temp_min") + " °C");
                pressure.setText("Pressure: " + jsonObject.getJSONObject("main").getDouble("pressure") + " hPa");
                humidity.setText("Humidity: " + jsonObject.getJSONObject("main").getDouble("humidity") + "%");
            }
        }
    }
}
