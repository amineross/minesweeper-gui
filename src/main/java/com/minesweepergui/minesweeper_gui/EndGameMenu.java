package com.minesweepergui.minesweeper_gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EndGameMenu extends VBox
{
    public EndGameMenu (Application app, boolean isGameWon)
    {
        super(10);
        this.setAlignment(Pos.CENTER);

        Text endGameMessage = new Text(isGameWon ? "Game Won!" : "Game Lost!");
        Button returnToMainMenuButton = new Button("Return to Main Menu");

        returnToMainMenuButton.setOnAction(e -> ((MainApplication)app).showMainMenu());

        this.getChildren().addAll(endGameMessage, returnToMainMenuButton);
    }
}

