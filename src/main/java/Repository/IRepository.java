package Repository;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.ProgramState.ProgramState;
import Exception.MyException;
import Model.Value.Value;

import java.util.List;

public interface IRepository {
    void add(ProgramState programState);

    ProgramState get(int index);

    void set(int index, ProgramState programState);

    int size();

    List<ProgramState> getProgramStates();

    void setProgramStates(List<ProgramState> programState);

    void logProgramStateExecution(ProgramState programState) throws MyException;

    void setLogFilePath(String logFilePath);


    MyIHeap getHeap();

    MyIDictionary<String, Value> getSymbolTable();
}
