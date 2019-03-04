package net.orekyuu.bitemporaldomaexample.domain.model.type;

import org.seasar.doma.Domain;

import java.io.Serializable;
import java.util.Objects;

@Domain(valueType = long.class, factoryMethod = "of")
public class Id<T> implements Serializable, Comparable {
    private static final long serialVersionUId = 1L;
    private final long value;
    private Id(final long value) {
        this.value = value;
    }
    public long getValue() {
        return value;
    }

    public static <R> Id<R> of(final long value) {
        if (value < 0) throw new IllegalArgumentException(
                "value should be positive. " + value
        );
        return new Id<>(value);
    }
    private static final Id<Object> NOT_ASSIGNED = new Id<>(-1);
    @SuppressWarnings("unchecked")
    public static <R> Id<R> notAssigned() {
        return (Id<R>) NOT_ASSIGNED;
    }

    @Deprecated
    public static <R> Id<R> valueOf(String value) {
        return of(Long.valueOf(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id<?> id = (Id<?>) o;
        return value == id.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Id{");
        sb.append("value=").append(value);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Id)) {
            return 0;
        }
        Id id = (Id) o;
        return (int) (value - id.value);
    }
}
