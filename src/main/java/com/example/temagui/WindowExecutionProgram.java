package com.example.temagui;

import Controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Exception.MyException;
import java.io.IOException;

public class WindowExecutionProgram {
    public void openNewWindow(Controller c) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(WindowForSelectingProgram.class.getResource("WindowExecutionProgram.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        WindowExecutionProgramController controller = fxmlLoader.getController();
        controller.changeController(c);
        stage.setTitle("Toy Language Interpreter");
        stage.setScene(scene);
        stage.show();
    }
}
