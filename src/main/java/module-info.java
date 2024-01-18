module com.minesweepergui.minesweeper_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.minesweepergui.minesweeper_gui to javafx.fxml;
    exports com.minesweepergui.minesweeper_gui;
    exports game to com.fasterxml.jackson.databind;


}