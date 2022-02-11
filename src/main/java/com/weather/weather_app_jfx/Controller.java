package com.weather.weather_app_jfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

public class Controller implements Helper {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getInfo;

    @FXML
    private Text humidity;

    @FXML
    private ImageView icon;

    @FXML
    private Text nameText;

    @FXML
    private Text pressure;

    @FXML
    private Text temp;

    @FXML
    private Text temp_feel;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    void initialize() {
        getInfo.setOnAction(event -> setWeather());
        city.setOnAction(event -> setWeather());
    }

    private void setWeather() {
        if (!city.equals("")) {
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

    private String getWeatherInfo(String address) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(address);
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String text;

            while ((text = bufferedReader.readLine()) != null) {
                content.append(text + "\n");
            }
            bufferedReader.close();
        } catch (Exception ex) {
            //System.out.println("Check city input");
            city.setTooltip(new Tooltip("Check city input"));
        }

        return content.toString();
    }
}
