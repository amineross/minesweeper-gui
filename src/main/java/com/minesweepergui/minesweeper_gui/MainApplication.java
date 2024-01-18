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
    private static final int IMAGE_SIZE = 40;
    private static final int NUM_BOMBS = 10;

    private Stage primaryStage;
    App app;

    Game currentGame;
    private Map<String, Image> imageMap = new HashMap<>();
    private Button[][] buttons = new Button[GRID_SIZE][GRID_SIZE];

    private void showMainMenu()
    {
        MainMenu mainMenu = new MainMenu(this);
        primaryStage.setScene(new Scene(mainMenu, 300, 200));
        primaryStage.setTitle("Minesweeper Main Menu");
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        showMainMenu();
    }

    public void startGame()
    {
        Player player = new Player("Amine");

        loadImages();
        app = new App();
        app.startApplication();
        app.startNewGame(GRID_SIZE, NUM_BOMBS, player);

        currentGame = app.getCurrentGame();

        primaryStage.setTitle("Minesweeper");
        GridPane gridPane = new GridPane();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Button button = createButton(i, j);
                gridPane.add(button, j, i);
                buttons[i][j] = button;
            }
        }

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void loadGame()
    {
        System.out.println("Load Game");
    }

    public void quitApplication()
    {
        primaryStage.close();
    }


    private Button createButton(int x, int y)
    {
        Button button = new Button();

        button.setGraphic(new ImageView(imageMap.get("hidden")));
        button.setMinSize(IMAGE_SIZE, IMAGE_SIZE);
        button.setMaxSize(IMAGE_SIZE, IMAGE_SIZE);

        Box clickedBox = currentGame.getGrid().getBoxAt(x, y);
        button.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                currentGame.revealCase(x, y);

                if (clickedBox.getNbNeighborBombs() == 0)
                {
                    updateAllButtonImages();
                } else
                {
                    updateButtonImage(button, x, y);
                }
            } else if (event.getButton() == MouseButton.SECONDARY) {
                currentGame.flagCase(x, y);
                updateButtonImage(button, x, y);
            }
        });

        return button;
    }

    private void updateButtonImage(Button button, int x, int y)
    {
        Box box = currentGame.getGrid().getBoxAt(x, y);

        if (box.isRevealed())
        {
            if (box.getContainsBomb())
            {
                button.setGraphic(new ImageView(imageMap.get("bomb")));
            } else if (box.getNbNeighborBombs() > 0)
            {
                button.setGraphic(new ImageView(imageMap.get(String.valueOf(box.getNbNeighborBombs()))));
            } else
            {
                button.setGraphic(new ImageView(imageMap.get("none2")));
            }
        } else if (box.isFlagged())
        {
            button.setGraphic(new ImageView(imageMap.get("flag")));
        } else
        {
            button.setGraphic(new ImageView(imageMap.get("hidden")));
        }
    }

    private void updateAllButtonImages()
    {
        for (int i = 0; i<GRID_SIZE; i++)
        {
            for (int j = 0; j<GRID_SIZE; j++)
            {
                Box box = currentGame.getGrid().getBoxAt(i, j);
                Button button = buttons[i][j];
                updateButtonImage(button, i, j);
            }
        }
    }

    private void loadImages()
    {
        imageMap.put("hidden", new Image("hidden.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("bomb", new Image("bomb.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("flag", new Image("flag.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("none2", new Image("none2.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("1", new Image("1.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("2", new Image("2.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("3", new Image("3.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("4", new Image("4.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("5", new Image("5.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("6", new Image("6.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("7", new Image("7.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
        imageMap.put("8", new Image("8.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
    }

    public static void main(String[] args) {
        launch(args);
    }
}