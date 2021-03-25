package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Найдите медиаконтент в ПУТИ к КЛАССУ
        URL mediaUrl = getClass().getResource("1.mp4");
        String mediaStringUrl = mediaUrl.toExternalForm();

        // --------------------------------------------------- Создание носителя
        final Media media = new Media(mediaStringUrl);

        // Создание медиаплеера
        final MediaPlayer player = new MediaPlayer(media);
        // Автоматический запуск проигрывателя
        player.setAutoPlay(true);
        player.setCycleCount(MediaPlayer.INDEFINITE); // повторение

        // Установить время проигрывателя
        //player.setStartTime(Duration.minutes(1));
        //player.setStopTime(Duration.minutes(2));

        // Создание 400X300 MediaView
        final MediaView mediaView = new MediaView(player);
        mediaView.setFitWidth(400);
        mediaView.setFitHeight(300);
        mediaView.setSmooth(true);

        // -------------------------------- Создание эффекта DropShadow effect

        DropShadow dropshadow = new DropShadow();
        dropshadow.setOffsetY(5.0);
        dropshadow.setOffsetX(5.0);
        dropshadow.setColor(Color.RED);

        mediaView.setEffect(dropshadow);

        // ----------------------------------------------- Создание Buttons
        Button playButton = new Button("ЗАПУСК");
        Button stopButton = new Button("СТОП");

        // Создайте обработчики событий кнопки ЗАПУСК
        playButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                if (player.getStatus() == MediaPlayer.Status.PLAYING)
                {
                    player.stop();
                    player.play();
                }
                else
                {
                    player.play();
                }
            }
        });

        // Создайте обработчики событий кнопки СТОП
        stopButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                player.stop();
            }
        });

        //-------------------------------------------------------- панели
        // Создайте HBox с кнопки
        HBox controlBox = new HBox(5, playButton, stopButton);

        // Создайте VBox с проигрыватилем и меню кнопок
        VBox root = new VBox(5,mediaView,controlBox);

        // Установите Стиль-свойства HBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");


        Scene scene = new Scene(root);// Создать сцену
        primaryStage.setScene(scene);// Добавить сцену на сцену
        primaryStage.setTitle("Создание медиаплеера");// Установить название сцены
        primaryStage.show();//  Отображение сцены
    }


    public static void main(String[] args) {
        launch(args);
    }
}
