package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Type getType() {
        return new StringType();
    }

    public Value deepCopy() {
        return new StringValue(this.value);
    }

    public String toString() {
        return String.format("\"%s\"", this.value);
    }
}
