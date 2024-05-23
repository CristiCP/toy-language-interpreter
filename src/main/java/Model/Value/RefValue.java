package Model.Value;

import Model.Type.RefType;
import Model.Type.Type;

public class RefValue implements Value {
    int address;
    Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return this.address;
    }

    public Type getLocationType() {
        return this.locationType;
    }

    public Type getType() {
        return new RefType(this.locationType);
    }

    public Value deepCopy() {
        return new RefValue(this.address, this.locationType.deepCopy());
    }

    public String toString() {
        return "(" + this.address + ", " + this.locationType + ")";
    }
}
