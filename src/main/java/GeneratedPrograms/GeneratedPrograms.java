package GeneratedPrograms;

import Model.DataStructure.*;
import Model.Expression.*;
import Model.ProgramState.ProgramState;
import Model.Statement.*;
import Exception.MyException;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;

public class GeneratedPrograms {
    public static ProgramState getProgram0() throws MyException {
        IStatement statement = new CompoundStatement(
                new CompoundStatement(new VarDecStatement("a", new IntType()),
                        new AssignmentStatement("a", new ValueExpression(new IntValue(1)))),
                new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(new VarExpression("a"),
                        new ValueExpression(new IntValue(2)), 1)),
                        new PrintStatement(new VarExpression("a")))
        );

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            statement.typeCheck(typeEnvironment);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        MyIStack<IStatement> stack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap<Value> heap = new MyHeap<>();

        ProgramState program;
        try {
            program = new ProgramState(statement, stack, symbolTable, output, fileTable, heap, null);
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        return program;
    }

    public static ProgramState getProgram1() throws MyException {
        IStatement statement = new CompoundStatement(new VarDecStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v",
                        new ValueExpression(new IntValue(2))), new PrintStatement(new VarExpression("v"))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            statement.typeCheck(typeEnvironment);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        MyIStack<IStatement> stack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap<Value> heap = new MyHeap<>();

        ProgramState program;
        try {
            program = new ProgramState(statement, stack, symbolTable, output, fileTable, heap, null);
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        return program;
    }

    public static ProgramState getProgram2() throws MyException {
        IStatement statement = new CompoundStatement(new VarDecStatement("a", new IntType()),
                new CompoundStatement(new VarDecStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a",
                                new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression(new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5)), 3), 1)),
                                new CompoundStatement(new AssignmentStatement("b",
                                        new ArithmeticExpression(new VarExpression("a"), new ValueExpression(new IntValue(1)), 1)),
                                        new PrintStatement(new VarExpression("b"))))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            statement.typeCheck(typeEnvironment);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            throw new MyException(e.getMessage());
        }

        MyIStack<IStatement> stack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap<Value> heap = new MyHeap<>();

        ProgramState program;
        try {
            program = new ProgramState(statement, stack, symbolTable, output, fileTable, heap, null);
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        return program;
    }

    public static ProgramState getProgram3() throws MyException {
        IStatement statement = new CompoundStatement(new VarDecStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("C:\\Users\\crist\\IdeaProjects\\TemaMAP\\src\\Model\\Statement\\test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VarExpression("varf")),
                                new CompoundStatement(new VarDecStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VarExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VarExpression("varc")),
                                                                        new CloseReadFile(new VarExpression("varf"))))))))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            statement.typeCheck(typeEnvironment);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        MyIStack<IStatement> stack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap<Value> heap = new MyHeap<>();

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            statement.typeCheck(typeEnvironment);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        ProgramState program;
        try {
            program = new ProgramState(statement, stack, symbolTable, output, fileTable, heap, null);
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        return program;
    }

    public static ProgramState getProgram4() throws MyException {
        IStatement declaringV = new VarDecStatement("v", new RefType(new IntType()));
        IStatement allocatingV = new HeapAllocationStatement("v", new ValueExpression(new IntValue(20)));
        IStatement printingV = new PrintStatement(new HeapReadExpression(new VarExpression("v")));
        IStatement writingV = new HeapWriteStatement("v", new ValueExpression(new IntValue(30)));
        IStatement printingV2 = new PrintStatement(new ArithmeticExpression(new HeapReadExpression(new VarExpression("v")), new ValueExpression(new IntValue(5)), 1));

        IStatement statement = new CompoundStatement(declaringV,
                new CompoundStatement(allocatingV,
                        new CompoundStatement(printingV,
                                new CompoundStatement(writingV,
                                        printingV2))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            statement.typeCheck(typeEnvironment);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        MyIStack<IStatement> stack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap<Value> heap = new MyHeap<>();

        ProgramState program;
        try {
            program = new ProgramState(statement, stack, symbolTable, output, fileTable, heap, null);
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        return program;
    }

    public static ProgramState getProgram5() throws MyException {
        IStatement declaringV = new VarDecStatement("v", new IntType());
        IStatement assigningV = new AssignmentStatement("v", new ValueExpression(new IntValue(4)));
        IStatement printingV = new PrintStatement(new VarExpression("v"));
        IStatement decrementingV = new AssignmentStatement("v", new ArithmeticExpression(
                new VarExpression("v"), new ValueExpression(new IntValue(1)), 2));
        IStatement whileStatement = new WhileStatement(new RelationalExpression(
                new VarExpression("v"), new ValueExpression(new IntValue(0)), 3),
                new CompoundStatement(printingV, decrementingV));

        IStatement statement = new CompoundStatement(declaringV,
                new CompoundStatement(assigningV,
                        whileStatement));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            statement.typeCheck(typeEnvironment);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        MyIStack<IStatement> stack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap<Value> heap = new MyHeap<>();

        ProgramState program;
        try {
            program = new ProgramState(statement, stack, symbolTable, output, fileTable, heap, null);
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        return program;
    }

    public static ProgramState getProgram6() throws MyException {
        IStatement declaringV = new VarDecStatement("v", new IntType());
        IStatement declaringA = new VarDecStatement("a", new RefType(new IntType()));
        IStatement assigningV = new AssignmentStatement("v", new ValueExpression(new IntValue(10)));
        IStatement allocatingA = new HeapAllocationStatement("a", new ValueExpression(new IntValue(22)));
        IStatement writingA = new HeapWriteStatement("a", new ValueExpression(new IntValue(30)));
        IStatement assigningV2 = new AssignmentStatement("v", new ValueExpression(new IntValue(32)));
        IStatement printingV = new PrintStatement(new VarExpression("v"));
        IStatement printingA = new PrintStatement(new HeapReadExpression(new VarExpression("a")));
        IStatement thread1 = new ForkStatement(new CompoundStatement(writingA,
                new CompoundStatement(assigningV2, new CompoundStatement(printingV, printingA))));

        IStatement statement = new CompoundStatement(declaringV,
                new CompoundStatement(declaringA,
                        new CompoundStatement(assigningV,
                                new CompoundStatement(allocatingA,
                                        new CompoundStatement(thread1,
                                                new CompoundStatement(printingV,
                                                        printingA))))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            statement.typeCheck(typeEnvironment);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIStack<IStatement> stack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIHeap<Value> heap = new MyHeap<>();
        ProgramState program;
        try {
            program = new ProgramState(statement, stack, symbolTable, output, fileTable, heap, null);
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        return program;
    }

    //barrier example
    public static ProgramState getProgram7() throws MyException {
        IStatement declaringV1 = new VarDecStatement("v1", new RefType(new IntType()));
        IStatement declaringV2 = new VarDecStatement("v2", new RefType(new IntType()));
        IStatement declaringV3 = new VarDecStatement("v3", new RefType(new IntType()));
        IStatement declaringCnt = new VarDecStatement("cnt", new IntType());
        IStatement allocatingV1 = new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2)));
        IStatement allocatingV2 = new HeapAllocationStatement("v2", new ValueExpression(new IntValue(3)));
        IStatement allocatingV3 = new HeapAllocationStatement("v3", new ValueExpression(new IntValue(4)));

        Expression readV1 = new HeapReadExpression(new VarExpression("v1"));
        Expression readV2 = new HeapReadExpression(new VarExpression("v2"));
        Expression readV3 = new HeapReadExpression(new VarExpression("v3"));
        IStatement awaitBarrier = new BarrierAwaitStatement("cnt");

        IStatement newBarrier = new BarrierDecStatement("cnt", readV2);
        IStatement fork1 = new ForkStatement(
                new CompoundStatement(
                        awaitBarrier,
                        new CompoundStatement(
                                new HeapWriteStatement("v1", new ArithmeticExpression(readV1, new ValueExpression(new IntValue(10)), 3)),
                                new PrintStatement(readV1)
                        )
                )
        );
        IStatement fork2 = new ForkStatement(
                new CompoundStatement(
                        awaitBarrier,
                        new CompoundStatement(
                                new HeapWriteStatement("v2", new ArithmeticExpression(readV2, new ValueExpression(new IntValue(10)), 3)),
                                new CompoundStatement(
                                        new HeapWriteStatement("v2", new ArithmeticExpression(readV2, new ValueExpression(new IntValue(10)), 3)),
                                        new PrintStatement(readV2)
                                )
                        )
                )
        );
        IStatement printingV3 = new PrintStatement(readV3);

        IStatement statement = new CompoundStatement(declaringV1,
                new CompoundStatement(declaringV2,
                        new CompoundStatement(declaringV3,
                                new CompoundStatement(declaringCnt,
                                        new CompoundStatement(allocatingV1,
                                                new CompoundStatement(allocatingV2,
                                                        new CompoundStatement(allocatingV3,
                                                                new CompoundStatement(newBarrier,
                                                                        new CompoundStatement(fork1,
                                                                                new CompoundStatement(fork2,
                                                                                        new CompoundStatement(awaitBarrier, printingV3)))))))))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            statement.typeCheck(typeEnvironment);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIStack<IStatement> stack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIHeap<Value> heap = new MyHeap<>();
        MyBarrierTable barrierTable = new MyBarrierTable();
        ProgramState program;
        try {
            program = new ProgramState(statement, stack, symbolTable, output, fileTable, heap, barrierTable);
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        return program;
    }


    //repeat until example
    public static ProgramState getProgram8() throws MyException {
        IStatement declaringV = new VarDecStatement("v", new IntType());
        IStatement declaringX = new VarDecStatement("x", new IntType());
        IStatement declaringY = new VarDecStatement("y", new IntType());
        IStatement assigningV = new AssignmentStatement("v", new ValueExpression(new IntValue(0)));

        IStatement nop = new NopStatement();

        IStatement forkStatement = new ForkStatement(
                new CompoundStatement(
                        new PrintStatement(new VarExpression("v")),
                        new AssignmentStatement("v", new ArithmeticExpression(
                                new VarExpression("v"), new ValueExpression(new IntValue(1)), 2))
                )
        );
        IStatement repeatStatement = new RepeatUntilStatement(
                new CompoundStatement(
                        forkStatement,
                        new AssignmentStatement("v", new ArithmeticExpression(
                                new VarExpression("v"), new ValueExpression(new IntValue(1)), 1))
                ),
                new RelationalExpression(new VarExpression("v"), new ValueExpression(new IntValue(3)), 5)
        );

        IStatement assigningX = new AssignmentStatement("x", new ValueExpression(new IntValue(1)));
        IStatement assigningY = new AssignmentStatement("y", new ValueExpression(new IntValue(3)));
        IStatement printingXV10 = new PrintStatement(new ArithmeticExpression(
                new VarExpression("v"), new ValueExpression(new IntValue(10)), 3));

        IStatement statement = new CompoundStatement(declaringV,
                new CompoundStatement(declaringX,
                        new CompoundStatement(declaringY,
                                new CompoundStatement(assigningV,
                                        new CompoundStatement(repeatStatement,
                                                new CompoundStatement(assigningX,
                                                        new CompoundStatement(nop,
                                                                new CompoundStatement(assigningY,
                                                                        new CompoundStatement(nop, printingXV10)))))))));
        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            statement.typeCheck(typeEnvironment);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIStack<IStatement> stack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIHeap<Value> heap = new MyHeap<>();
        MyBarrierTable barrierTable = new MyBarrierTable();
        ProgramState program;
        try {
            program = new ProgramState(statement, stack, symbolTable, output, fileTable, heap, barrierTable);
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        return program;
    }

}
