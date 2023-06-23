package com.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Создание экземпляра FXMLLoader для загрузки FXML-файла
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("hello-view.fxml"));

        try {
            loader.load(); // Загрузка FXML-файла
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot(); // Получение корневого элемента из FXML
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);

        // изменение координат окна за счет мышки
        root.setOnMousePressed(pressEvent -> {
            root.setOnMouseDragged(dragEvent -> {
                primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
        scene.setFill(Color.TRANSPARENT);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene); // Создание объекта класса Scene с корневым элементом
        primaryStage.showAndWait(); // Отображение и ожидание закрытия окна
    }

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX приложения
    }
}