package Repository;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.ProgramState.ProgramState;
import Exception.MyException;
import Model.Value.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private final List<ProgramState> programStates = new ArrayList<ProgramState>();

    private String logFilePath = "log.txt";
    private int currentProgramIndex = -1;

    public void add(ProgramState programState) {
        this.programStates.add(programState);
        this.currentProgramIndex++;
    }

    public ProgramState get(int index) {
        return this.programStates.get(index);
    }

    public void set(int index, ProgramState programState) {
        this.programStates.set(index, programState);
    }

    public int size() {
        return this.programStates.size();
    }

    public List<ProgramState> getProgramStates() {
        return List.copyOf(this.programStates);
    }

    public void setProgramStates(List<ProgramState> programState) {
        this.programStates.clear();
        this.programStates.addAll(programState);
    }

    public void logProgramStateExecution(ProgramState programState) throws MyException {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)))) {
            logFile.println(programState);
        } catch (IOException e) {
            throw new MyException("Could not open log file!");
        }
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public MyIHeap getHeap() {
        return this.programStates.get(0).getHeap();
    }

    public MyIDictionary<String, Value> getSymbolTable() {
        return this.programStates.get(0).getSymbolTable();
    }

}
