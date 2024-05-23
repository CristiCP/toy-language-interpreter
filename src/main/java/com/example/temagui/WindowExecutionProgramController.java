package com.example.temagui;

import Controller.Controller;
import Model.DataStructure.*;
import Model.Heap.Heap;
import Model.ProgramState.ProgramState;
import Model.SymbolTable.SymbolTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Exception.MyException;
import Model.BarrierTable.BarrierTableEntry;

import java.net.URL;
import java.util.*;

public class WindowExecutionProgramController implements Initializable {

    @FXML
    private ListView<String> executionStackList;
    private final ObservableList<String> executionStackItems = FXCollections.observableArrayList();

    @FXML
    private TableView<Heap> heapTable;
    @FXML
    private TableColumn<Heap, ArrayList> valueHeapTableColumn;
    @FXML
    private TableColumn<Heap, Set> addressHeapTableColumn;
    private ObservableList<Heap> heapTableItems = FXCollections.observableArrayList();

    @FXML
    private TableView<SymbolTable> symbolTable;
    @FXML
    private TableColumn<SymbolTable, Collection> symbolTableValueColumn;
    @FXML
    private TableColumn<SymbolTable, Set> symbolTableIdColumn;
    private final ObservableList<SymbolTable> symbolTableItems = FXCollections.observableArrayList();
    @FXML
    private ListView<String> outputList;
    private final ObservableList<String> outputListItems = FXCollections.observableArrayList();
    @FXML
    private ListView<String> fileTableList;
    private final ObservableList<String> fileTableListItems = FXCollections.observableArrayList();
    @FXML
    private ListView<Object> threadsList;
    private final ObservableList<Object> threadsListItems = FXCollections.observableArrayList();
    @FXML
    private TextField threadCountText;


    @FXML
    private TableView<BarrierTableEntry> barrierTable;
    @FXML
    private TableColumn<BarrierTableEntry, String> barrierTableAddressColumn;
    @FXML
    private TableColumn<BarrierTableEntry, String> barrierTableValueColumn;

    private int threadsCounter = 0;
    private ArrayList threads = new ArrayList();
    private Controller controller;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        executionStackList.setItems(executionStackItems);

        addressHeapTableColumn.setCellValueFactory(new PropertyValueFactory<Heap, Set>("address"));
        valueHeapTableColumn.setCellValueFactory(new PropertyValueFactory<Heap, ArrayList>("value"));
        heapTable.setItems(heapTableItems);

        symbolTableIdColumn.setCellValueFactory(new PropertyValueFactory<SymbolTable, Set>("id"));
        symbolTableValueColumn.setCellValueFactory(new PropertyValueFactory<SymbolTable, Collection>("value"));
        symbolTable.setItems(symbolTableItems);

        outputList.setItems(outputListItems);
        fileTableList.setItems(fileTableListItems);
        threadsList.setItems(threadsListItems);

        barrierTableAddressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        barrierTableValueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());

        threadsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int selectedIndex = threadsList.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                MyIStack executionStack = this.controller.getRepository().get(selectedIndex).getExecutionStack();
                executionStackItems.clear();
                executionStackItems.add(executionStack.toString());
            }
        });
    }

    public void changeController(Controller c) {
        this.controller = c;
    }

    public void oneStep() throws MyException {
        List<ProgramState> programStates = this.controller.removeCompletedPrograms(this.controller.getRepository().getProgramStates());
        if (!programStates.isEmpty()) {

            controller.oneStepForAllPrograms(programStates);

            MyIStack executionStack = this.controller.getRepository().get(0).getExecutionStack();
            executionStackItems.clear();
            executionStackItems.add(executionStack.toString());

            MyIList output = this.controller.getRepository().get(0).getOutput();
            outputListItems.clear();
            outputListItems.add(output.toString());

            MyIDictionary fileTable = this.controller.getRepository().get(0).getFileTable();
            fileTableListItems.clear();
            fileTableListItems.add(fileTable.toString());

            Set heapAddress = controller.getRepository().getHeap().keys();
            ArrayList heapValue = controller.getRepository().getHeap().values();
            heapTableItems.clear();
            heapTableItems.add(new Heap(heapAddress, heapValue));

            Set id = this.controller.getRepository().getSymbolTable().keys();
            Collection value = this.controller.getRepository().getSymbolTable().values();
            symbolTableItems.clear();
            symbolTableItems.add(new SymbolTable(id, value));

            threadsListItems.clear();
            int threadId = controller.getRepository().get(0).getId();
            for (int i = 0; i < programStates.size(); i++) {
                threadsListItems.add("Program state: " + programStates.get(i).getId());
            }

            if (!threads.contains(threadId)) {
                threads.add(threadId);
                threadsCounter = threadsCounter + 1;
                threadCountText.setText("Thread Count: " + String.valueOf(threadsCounter));
            }

            this.barrierTable.getItems().clear();
            for (ProgramState programState : this.controller.getRepository().getProgramStates()) {
                for (Integer key : programState.getBarrierTable().keys()) {
                    Pair<Integer, List<Integer>> pair = (Pair<Integer, List<Integer>>) programState.getBarrierTable().get(key);
                    BarrierTableEntry entry = new BarrierTableEntry(key, pair);
                    this.barrierTable.getItems().add(entry);
                }
                break;
            }
        }
    }

    public void allSteps() throws MyException {
        controller.allSteps();
        MyIStack executionStack = this.controller.getRepository().get(0).getExecutionStack();
        executionStackItems.clear();
        executionStackItems.add(executionStack.toString());

        MyIList output = this.controller.getRepository().get(0).getOutput();
        outputListItems.clear();
        outputListItems.add(output.toString());

        MyIDictionary fileTable = this.controller.getRepository().get(0).getFileTable();
        fileTableListItems.clear();
        fileTableListItems.add(fileTable.toString());

        Set heapAddress = controller.getRepository().getHeap().keys();
        ArrayList heapValue = controller.getRepository().getHeap().values();
        heapTableItems.clear();
        heapTableItems.add(new Heap(heapAddress, heapValue));

        Set id = this.controller.getRepository().getSymbolTable().keys();
        Collection value = this.controller.getRepository().getSymbolTable().values();
        symbolTableItems.clear();
        symbolTableItems.add(new SymbolTable(id, value));

        this.barrierTable.getItems().clear();
        for (ProgramState programState : this.controller.getRepository().getProgramStates()) {
            for (Integer key : programState.getBarrierTable().keys()) {
                Pair<Integer, List<Integer>> pair = (Pair<Integer, List<Integer>>) programState.getBarrierTable().get(key);
                BarrierTableEntry entry = new BarrierTableEntry(key, pair);
                this.barrierTable.getItems().add(entry);
            }
            break;
        }
    }
}
