package Controller;

import Exception.MyException;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIHeap;
import Model.ProgramState.ProgramState;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.IRepository;
import Exception.HeapException;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    public boolean debugFlag;
    private final IRepository repository;

    private ExecutorService executorService;

    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    public Controller(IRepository repository) {
        this.repository = repository;
        this.debugFlag = false;
    }

    public void oneStepForAllPrograms(List<ProgramState> programStates) throws MyException {
        List<Callable<ProgramState>> callList = programStates.stream()
                .map((ProgramState programState) -> (Callable<ProgramState>) programState::oneStep)
                .toList();
        try {
            List<ProgramState> newProgramStates = this.executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();
            programStates.addAll(newProgramStates);
        } catch (InterruptedException e) {
            throw new MyException(e.getMessage());
        }

        if (true) {
            programStates.forEach(programState -> {
                try {
                    this.repository.logProgramStateExecution(programState);
                } catch (MyException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        this.repository.setProgramStates(programStates);
    }


    public void allSteps() throws MyException {
        System.out.println(this.repository.getProgramStates());
        List<ProgramState> programStates = this.removeCompletedPrograms(this.repository.getProgramStates());
        while (!programStates.isEmpty()) {
            this.conservativeGarbageCollector(this.repository.getSymbolTable(), this.repository.getHeap());
            try {
                this.oneStepForAllPrograms(programStates);
            } catch (RuntimeException e) {
                throw new MyException(e.getMessage());
            }
            programStates = this.removeCompletedPrograms(this.repository.getProgramStates());
        }
    }

    public void setProgram(ProgramState program) {
        this.repository.add(program);
    }

    public void setLogFilePath(String logFilePath) {
        this.repository.setLogFilePath(logFilePath);
    }

    private void conservativeGarbageCollector(MyIDictionary<String, Value> symbolTable, MyIHeap<Value> heap) {
        Set<Integer> freedAddresses = new HashSet<>(heap.keys());
        for (Value value : symbolTable.values()) {
            while (value instanceof RefValue referenceValue) {
                freedAddresses.remove(referenceValue.getAddress());
                value = heap.get(referenceValue.getAddress());

            }
        }

        for (Integer freedAddress : freedAddresses) {
            heap.remove(freedAddress);
        }
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates) {
        return programStates.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public IRepository getRepository(){
        return this.repository;
    }
}
