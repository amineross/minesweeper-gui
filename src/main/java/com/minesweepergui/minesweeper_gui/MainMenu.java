package com.minesweepergui.minesweeper_gui;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

/**
 * The MainMenu class represents the main menu UI for the Minesweeper game.
 */
public class MainMenu extends VBox {

    /**
     * Constructs a MainMenu instance.
     *
     * @param app The application this menu is a part of.
     */
    public MainMenu(final Application app) {
        super(10);
        this.setAlignment(Pos.CENTER);

        Button startGameButton = new Button("Start Game");
        Button loadGameButton = new Button("Load Game");
        Button statsButton = new Button("Statistics");
        Button quitButton = new Button("Quit");

        startGameButton.setOnAction(e -> ((MainApplication)app).startNewGame());
        loadGameButton.setOnAction(e -> ((MainApplication)app).loadGame());
        statsButton.setOnAction(e -> ((MainApplication)app).showStatsMenu());
        quitButton.setOnAction(e -> ((MainApplication)app).quitApplication());

        this.getChildren().addAll(startGameButton, loadGameButton, statsButton, quitButton);
    }
}
