package com.minesweepergui.minesweeper_gui;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainMenu extends VBox
{
    public MainMenu(Application app)
    {
        super(10);
        this.setAlignment(Pos.CENTER);

        Button startGameButton = new Button("StartGame");
        Button loadGameButton = new Button("Load Game");
        Button quitButton = new Button("Quit");

        startGameButton.setOnAction(e -> ((MainApplication)app).startGame());
        loadGameButton.setOnAction(e -> ((MainApplication)app).loadGame());
        quitButton.setOnAction(e -> ((MainApplication)app).quitApplication());

        this.getChildren().addAll(startGameButton, loadGameButton, quitButton);
    }
}
