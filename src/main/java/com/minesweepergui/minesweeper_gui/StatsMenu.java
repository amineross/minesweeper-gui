package com.minesweepergui.minesweeper_gui;

import game.Player;
import game.PlayerStorage;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.List;

/**
 * The StatsMenu class represents the statistics menu in the Minesweeper game.
 */
public class StatsMenu extends VBox {

    /**
     * Constructs a StatsMenu instance.
     *
     * @param app The application this menu is a part of.
     */
    public StatsMenu(final Application app) {
        super(10);
        this.setAlignment(Pos.CENTER);
        List<Player> players = PlayerStorage.loadPlayers();

        players.sort((p1, p2) -> Double.compare(calculateWinRate(p2), calculateWinRate(p1)));

        for (Player player : players) {
            double winRate = calculateWinRate(player);
            String playerStats = String.format("%s - %d Wins - %d Games - %.2f%% Win Rate",
                    player.getName(), player.getNbGamesWon(), player.getNbGamesPlayed(), winRate * 100);
            this.getChildren().add(new Text(playerStats));
        }

        Button returnButton = new Button("Return");
        returnButton.setOnAction(e -> ((MainApplication)app).showMainMenu());
        this.getChildren().add(returnButton);
    }

    /**
     * Calculates the win rate of a player.
     *
     * @param player The player whose win rate is calculated.
     * @return The win rate of the player.
     */
    private static double calculateWinRate(final Player player) {
        if (player.getNbGamesPlayed() == 0) {
            return 0;
        }
        return (double) player.getNbGamesWon() / player.getNbGamesPlayed();
    }
}
