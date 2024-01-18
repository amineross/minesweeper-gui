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