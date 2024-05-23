package Model.ProgramState;

import Model.DataStructure.*;
import Model.Statement.IStatement;
import Model.Value.Value;
import Exception.MyException;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ProgramState {
    private MyIStack<IStatement> executionStack;
    private MyIDictionary<String, Value> symbolTable;
    private final MyIDictionary<String, BufferedReader> fileTable;

    private final ISyncTable barrierTable;
    private MyIList<Value> output;
    private static final Set<Integer> ids = new HashSet<>();
    private final int id;
    IStatement originalProgram;
    private MyIHeap<Value> heap;

    public ProgramState(IStatement originalProgram, MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable,
                        MyIList<Value> output, MyIDictionary<String, BufferedReader> fileTable, MyIHeap<Value> heap, ISyncTable barrierTable) throws MyException {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
        this.originalProgram = originalProgram.deepCopy();
        this.heap = heap;
        this.id = ProgramState.generateNewId();
        this.barrierTable = barrierTable;
        executionStack.push(originalProgram);
    }

    public MyIStack<IStatement> getExecutionStack() {
        return this.executionStack;
    }

    public MyIDictionary<String, Value> getSymbolTable() {
        return this.symbolTable;
    }

    public MyIList<Value> getOutput() {
        return this.output;
    }

    public MyIHeap<Value> getHeap() {
        return this.heap;
    }

    public ISyncTable getBarrierTable() {
        return this.barrierTable;
    }

    public void setHeap(MyIHeap<Value> heap) {
        this.heap = heap;
    }

    public boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws MyException {
        if (executionStack.isEmpty()) {
            throw new MyException("Execution stack is empty!");
        }
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public int getId() {
        return this.id;
    }

    private static int generateNewId() {
        Random random = new Random();
        int id;
        synchronized (ProgramState.ids) {
            do {
                id = random.nextInt(1000);
            } while (ProgramState.ids.contains(id));
            ProgramState.ids.add(id);
        }
        return id;
    }

    public MyIDictionary<String, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public String toString() {
        return "Program State: " + this.id + "\n" +
                "Execution Stack:\n" +
                this.executionStack +
                "\n" +
                "Symbol Table:\n" +
                this.symbolTable +
                "\n" +
                "Heap:\n" +
                this.heap +
                "\n" +
                "File Table:\n" +
                this.fileTable +
                "\n" +
                "Output:\n" +
                this.output +
                "\nBarrier Table:\n" +
                this.barrierTable +
                "\n";
    }
}
