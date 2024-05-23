package Model.Value;

import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value {
    int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "" + this.value;
    }

    public Type getType() {
        return new IntType();
    }

    public Value deepCopy() {
        return new IntValue(this.value);
    }
}
