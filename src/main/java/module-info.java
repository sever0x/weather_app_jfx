module com.weather.weather_app_jfx {
    requires org.json;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.weather.weather_app_jfx to javafx.fxml;
    exports com.weather.weather_app_jfx;

}