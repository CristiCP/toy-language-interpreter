package com.example.temagui;

import Exception.MyException;
import Controller.Controller;
import GeneratedPrograms.GeneratedPrograms;
import Repository.Repository;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class WindowForSelectingProgramController implements Initializable {
    @FXML
    public ListView<String> listForPrograms;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listForPrograms.getItems().add(GeneratedPrograms.getProgram0().getExecutionStack().toString());
        } catch (MyException e) {
            ;
        }
        try {
            listForPrograms.getItems().add(GeneratedPrograms.getProgram1().getExecutionStack().toString());
        } catch (MyException e) {
            ;
        }
        try {
            listForPrograms.getItems().add(GeneratedPrograms.getProgram2().getExecutionStack().toString());
        } catch (MyException e) {
            ;
        }
        try {
            listForPrograms.getItems().add(GeneratedPrograms.getProgram3().getExecutionStack().toString());
        } catch (MyException e) {
            ;
        }
        try {
            listForPrograms.getItems().add(GeneratedPrograms.getProgram4().getExecutionStack().toString());
        } catch (MyException e) {
            ;
        }
        try {
            listForPrograms.getItems().add(GeneratedPrograms.getProgram5().getExecutionStack().toString());
        } catch (MyException e) {
            ;
        }
        try {
            listForPrograms.getItems().add(GeneratedPrograms.getProgram6().getExecutionStack().toString());
        } catch (MyException e) {
            ;
        }
        try {
            listForPrograms.getItems().add(GeneratedPrograms.getProgram7().getExecutionStack().toString());
        } catch (MyException e) {
            ;
        }
        try {
            listForPrograms.getItems().add(GeneratedPrograms.getProgram8().getExecutionStack().toString());
        } catch (MyException e) {
            ;
        }
    }

    public void errorBox() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Please select a program from the list.");
        alert.showAndWait();
    }

    @FXML
    public void selectButton() throws IOException, MyException {
        if (listForPrograms.getSelectionModel().isEmpty()) {
            errorBox();
        } else {
            String selectedProgram = listForPrograms.getSelectionModel().getSelectedItems().toString();
            Repository repository = new Repository();
            Controller c = new Controller(repository);
            if (Objects.equals(selectedProgram, "[" + GeneratedPrograms.getProgram0().getExecutionStack().toString() + "]")) {
                c.setProgram(GeneratedPrograms.getProgram0());
                WindowExecutionProgram newWindow = new WindowExecutionProgram();
                newWindow.openNewWindow(c);
            } else if (Objects.equals(selectedProgram, "[" + GeneratedPrograms.getProgram1().getExecutionStack().toString() + "]")) {
                c.setProgram(GeneratedPrograms.getProgram1());
                WindowExecutionProgram newWindow = new WindowExecutionProgram();
                newWindow.openNewWindow(c);
            } else if (Objects.equals(selectedProgram, "[" + GeneratedPrograms.getProgram2().getExecutionStack().toString() + "]")) {
                c.setProgram(GeneratedPrograms.getProgram2());
                WindowExecutionProgram newWindow = new WindowExecutionProgram();
                newWindow.openNewWindow(c);
            } else if (Objects.equals(selectedProgram, "[" + GeneratedPrograms.getProgram3().getExecutionStack().toString() + "]")) {
                c.setProgram(GeneratedPrograms.getProgram3());
                WindowExecutionProgram newWindow = new WindowExecutionProgram();
                newWindow.openNewWindow(c);
            } else if (Objects.equals(selectedProgram, "[" + GeneratedPrograms.getProgram4().getExecutionStack().toString() + "]")) {
                c.setProgram(GeneratedPrograms.getProgram4());
                WindowExecutionProgram newWindow = new WindowExecutionProgram();
                newWindow.openNewWindow(c);
            } else if (Objects.equals(selectedProgram, "[" + GeneratedPrograms.getProgram5().getExecutionStack().toString() + "]")) {
                c.setProgram(GeneratedPrograms.getProgram5());
                WindowExecutionProgram newWindow = new WindowExecutionProgram();
                newWindow.openNewWindow(c);
            } else if (Objects.equals(selectedProgram, "[" + GeneratedPrograms.getProgram6().getExecutionStack().toString() + "]")) {
                c.setProgram(GeneratedPrograms.getProgram6());
                WindowExecutionProgram newWindow = new WindowExecutionProgram();
                newWindow.openNewWindow(c);
            } else if (Objects.equals(selectedProgram, "[" + GeneratedPrograms.getProgram7().getExecutionStack().toString() + "]")) {
                c.setProgram(GeneratedPrograms.getProgram7());
                WindowExecutionProgram newWindow = new WindowExecutionProgram();
                newWindow.openNewWindow(c);
            } else if (Objects.equals(selectedProgram, "[" + GeneratedPrograms.getProgram8().getExecutionStack().toString() + "]")) {
                c.setProgram(GeneratedPrograms.getProgram8());
                WindowExecutionProgram newWindow = new WindowExecutionProgram();
                newWindow.openNewWindow(c);
            }
        }
    }


}