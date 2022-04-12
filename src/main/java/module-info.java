module com.example.videogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.videogame to javafx.fxml;
    exports com.example.videogame;
    exports com.example.videogame.sprites;
    opens com.example.videogame.sprites to javafx.fxml;
}