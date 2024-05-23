package Model.Type;

import Model.Value.BoolValue;
import Model.Value.Value;

public class BoolType implements Type {
    public boolean equals(Object another) {
        return another instanceof BoolType;
    }

    public String toString() {
        return "bool";
    }

    public Value defaultValue() {
        return new BoolValue(false);
    }

    public Type deepCopy() {
        return new BoolType();
    }
}
