package Model.Type;

import Model.Value.RefValue;
import Model.Value.Value;

public class RefType implements Type {
    Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    public boolean equals(Object another) {
        return another instanceof RefType;
    }

    public String toString() {
        return "ref (" + this.inner + ")";
    }

    public Value defaultValue() {
        return new RefValue(0, inner);
    }

    public Type deepCopy() {
        return new RefType(inner.deepCopy());
    }
}
