package com.minesweepergui.minesweeper_gui;

import game.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.TextInputDialog;
import java.util.Optional;

/**
 * Main application class for the Minesweeper game.
 * This class is responsible for initializing and displaying the primary stage of the application,
 * handling menu actions, and orchestrating the game logic and UI components.
 */
public class MainApplication extends Application
{
    private static final int GRID_SIZE = 10;
    private static final int IMAGE_SIZE = 40;
    private static final int NUM_BOMBS = 10;

    private Stage primaryStage;
    App app;

    Game currentGame;
    private final Map<String, Image> imageMap = new HashMap<>();
    private final Button[][] buttons = new Button[GRID_SIZE][GRID_SIZE];

    public void showMainMenu()
    {
        MainMenu mainMenu = new MainMenu(this);
        primaryStage.setScene(new Scene(mainMenu, 300, 200));
        primaryStage.setTitle("Minesweeper Main Menu");
        primaryStage.show();
    }

    public void showEndGameMenu(boolean isGameWon)
    {
        EndGameMenu endGameMenu = new EndGameMenu(this, isGameWon);
        primaryStage.setScene(new Scene(endGameMenu, 300, 200));
        primaryStage.setTitle(isGameWon ? "Game Won" : "Game");
        primaryStage.show();
    }

    public void showStatsMenu()
    {
        StatsMenu statsMenu = new StatsMenu(this);
        primaryStage.setScene(new Scene(statsMenu, 400, 600));
        primaryStage.setTitle("Player Statistics");
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        showMainMenu();
    }

    public void startNewGame() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Game");
        dialog.setHeaderText("Enter your username:");
        dialog.setContentText("Username:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(username -> {
            Player player = new Player(username);
            PlayerStorage.savePlayer(player);
            startGame(player, false);
        });
    }

    public void startGame(Player player, boolean isLoadedGame)
    {
        loadImages();
        app = new App();
        app.startApplication();

        if (isLoadedGame)
        {
            app.setCurrentGame(this.currentGame);
        } else
        {
            app.startNewGame(GRID_SIZE, NUM_BOMBS, player);
        }

        currentGame = app.getCurrentGame();

        MenuBar menuBar = createGameMenuBar();

        GridPane gridPane = new GridPane();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Button button = createButton(i, j);
                gridPane.add(button, j, i);
                buttons[i][j] = button;
            }
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(gridPane);

        updateAllButtonImages();

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(String.format("Minesweeper - %s", player.getName()));
        primaryStage.show();
    }

    private void saveGame()
    {
        TextInputDialog dialog = new TextInputDialog("savegame");
        dialog.setTitle("Save Game");
        dialog.setHeaderText("Save your game");
        dialog.setContentText("Enter file name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(fileName -> {
            String filePath = fileName.endsWith(".json") ? fileName : fileName + ".json";
            SaveGame.saveGame(currentGame, filePath);
        });
    }

    public void loadGame()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Save game File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Save Game Files", "*.json"));

        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null)
        {
            Game loadedGame = SaveGame.loadGame(selectedFile.getAbsolutePath());
            if (loadedGame != null)
            {
                this.currentGame = loadedGame;
                startGame(loadedGame.getPlayer(), true);
            }
        }
    }

    private void resetGame()
    {
        currentGame.resetGame();
        updateAllButtonImages();
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
            if (event.getButton() == MouseButton.PRIMARY)
            {
                currentGame.revealCase(x, y);
                Player currentPlayer = currentGame.getPlayer();

                if (currentGame.getStatus() == 3)
                {
                    currentPlayer.incrementGamesPlayed();
                    PlayerStorage.updatePlayer(currentGame.getPlayer());
                    showEndGameMenu(false);
                }
                else if (currentGame.getStatus() == 2)
                {
                    currentPlayer.incrementGamesPlayed();
                    currentPlayer.incrementGamesWon();
                    PlayerStorage.updatePlayer(currentPlayer);
                    showEndGameMenu(true);
                }

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
                Button button = buttons[i][j];
                updateButtonImage(button, i, j);
            }
        }
    }

    private MenuBar createGameMenuBar()
    {
        MenuBar menuBar = new MenuBar();

        Menu gameMenu = new Menu("Game");
        MenuItem resetItem = new MenuItem("Reset");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem loadItem = new MenuItem("Load");
        MenuItem quitItem = new MenuItem("Quit");

        resetItem.setOnAction(e -> resetGame());
        saveItem.setOnAction(e -> saveGame());
        loadItem.setOnAction(e -> loadGame());
        quitItem.setOnAction(e -> primaryStage.close());

        gameMenu.getItems().addAll(resetItem, saveItem, loadItem, quitItem);
        menuBar.getMenus().add(gameMenu);

        return menuBar;
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

    public static void main(String[] args)
    {
        launch(args);
    }
}