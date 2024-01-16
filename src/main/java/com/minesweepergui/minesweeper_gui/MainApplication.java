package com.minesweepergui.minesweeper_gui;

import game.App;
import game.Box;
import game.Game;
import game.Grid;
import game.Player;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainApplication extends Application {
    private static final int GRID_SIZE = 10;
    private static final int NUM_IMAGES = 7;
    private static final int NUM_BOMBS = 10;

    private Map<Button, Image> buttonImageMap = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Minesweeper");
        GridPane grid = new GridPane();

        Image hiddenImage = new Image("hidden.png");
        Image bombImage = new Image("bomb.png");
        Image flagImage = new Image("flag.png");
        Image[] images = new Image[NUM_IMAGES];
        for (int i = 0; i < NUM_IMAGES; i++) {
            images[i] = new Image((i + 1) + ".png");
        }

        initializeGrid(grid, hiddenImage, bombImage, flagImage, images);

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initializeGrid(GridPane grid, Image hiddenImage, Image bombImage, Image flagImage, Image[] images) {
        Random random = new Random();

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = createButton(row, col, hiddenImage, bombImage, flagImage, images);
                grid.add(button, col, row);
            }
        }

        for (int i = 0; i < NUM_BOMBS; i++) {
            int randomRow = random.nextInt(GRID_SIZE);
            int randomCol = random.nextInt(GRID_SIZE);

            Button button = (Button) grid.getChildren().get(randomRow * GRID_SIZE + randomCol);
            button.setUserData(bombImage);
        }
    }

    private Button createButton(int row, int col, Image hiddenImage, Image bombImage, Image flagImage, Image[] images) {
        Button button = new Button();
        button.setMinSize(40, 40);

        ImageView imageView = new ImageView(hiddenImage);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        button.setGraphic(imageView);

        button.setOnAction(event -> handleButtonClick(button, images));
        button.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                handleRightClick(button, flagImage, hiddenImage);
            }
        });

        return button;
    }

    private void handleButtonClick(Button button, Image[] images) {
        if (button.isArmed() && button.isPressed() && button.isFocused()) {

            return;
        }

        Image bombImage = (Image) button.getUserData();

        if (bombImage != null) {

            ImageView newImageView = new ImageView(bombImage);
            newImageView.setFitWidth(40);
            newImageView.setFitHeight(40);
            button.setGraphic(newImageView);

        } else {
            Random random = new Random();
            int randomImageIndex = random.nextInt(NUM_IMAGES);
            Image randomImage = images[randomImageIndex];

            if (!randomImage.equals(buttonImageMap.get(button))) {

                ImageView newImageView = new ImageView(randomImage);
                newImageView.setFitWidth(40);
                newImageView.setFitHeight(40);
                button.setGraphic(newImageView);

                buttonImageMap.put(button, randomImage);

            }
        }
    }

    private void handleRightClick(Button button, Image flagImage, Image hiddenImage) {
        ImageView currentImageView = (ImageView) button.getGraphic();

        if (currentImageView != null && currentImageView.getImage() == flagImage) {

            ImageView hiddenImageView = new ImageView(hiddenImage);
            hiddenImageView.setFitWidth(40);
            hiddenImageView.setFitHeight(40);
            button.setGraphic(hiddenImageView);
        } else {

            ImageView flagImageView = new ImageView(flagImage);
            flagImageView.setFitWidth(40);
            flagImageView.setFitHeight(40);
            button.setGraphic(flagImageView);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}