package com.minesweepergui.minesweeper_gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The EndGameMenu class represents the menu shown at the end of a game.
 */
public class EndGameMenu extends VBox {

    /**
     * Constructs an EndGameMenu instance.
     *
     * @param app        The application this menu is a part of.
     * @param isGameWon  Indicates whether the game was won or lost.
     */
    public EndGameMenu(final Application app, final boolean isGameWon) {
        super(10);
        this.setAlignment(Pos.CENTER);

        Text endGameMessage = new Text(isGameWon ? "Game Won!" : "Game Lost!");
        Button returnToMainMenuButton = new Button("Return to Main Menu");

        returnToMainMenuButton.setOnAction(e -> ((MainApplication)app).showMainMenu());

        this.getChildren().addAll(endGameMessage, returnToMainMenuButton);
    }
}
