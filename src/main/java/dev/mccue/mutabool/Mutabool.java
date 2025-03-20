package dev.mccue.mutabool;

import java.io.Serial;
import java.io.Serializable;

/// A **Muta**ble **Bool**ean.
///
/// This is an object with identity and as such its {@link Object#equals(Object)}
/// and {@link Object#hashCode()} are based on that identity and not the boolean
/// contained within.
///
/// Changes to the contained value are done on a plain field and are therefore
/// subject to all the Java Memory Model shenanigans that implies.
///
/// Subclassing of this class is allowed and encouraged. What a subclass would do
/// is unknown, but it must handle being serialized. All public methods are final
/// to prevent a subclass from inverting the meaning of true and false - the only purpose
/// of a subclass would therefore be to add new methods.
///
/// This class exists because the name is funny. If you need an equivalent
/// for other types it is trivial to construct one.
///
/// ```java
/// class Box<T> {
///     T value;
/// }
/// ```
///
/// A legitimate use for this sort of class is for mutating data from within a lambda.
///
/// ```java
/// var allEvens = new Mutabool(true);
/// list.forEach(item -> {
///     if (item % 2 != 0) {
///         allEvens.setFalse();
///     }
/// });
/// IO.println(allEvens.get());
/// allEvens.ifFalse(() -> IO.println("Found at least one odd number));
/// ```
public class Mutabool implements Serializable {
    @Serial
    private static final long serialVersionUID = 420_69L;

    private boolean value;

    /// Creates a {@link Mutabool} initialized to false.
    public Mutabool() {}

    /// Creates a {@link Mutabool} initialized to the given value.
    ///
    /// @param value The initial value.
    public Mutabool(boolean value) {
        this.value = value;
    }

    /// Gets the current value.
    ///
    /// @return The current value
    public final boolean get() {
        return value;
    }

    /// Sets the current value.
    ///
    /// @param value The new value.
    public final void set(boolean value) {
        this.value = value;
    }

    /// Returns if the held value is {@code true}.
    ///
    /// @return If the held value is {@code true}.
    public final boolean isTrue() {
        return value;
    }

    /// Returns if the held value is {@code false}.
    ///
    /// @return If the held value is {@code false}.
    public final boolean isFalse() {
        return !value;
    }

    /// Sets the value to {@code true}.
    public final void setTrue() {
        value = true;
    }

    /// Sets the value to {@code false}.
    public final void setFalse() {
        value = false;
    }

    /// Runs the given runnable if the contained value
    /// is {@code true}.
    ///
    /// Returns {@code this} to support chaining with {@link  Mutabool#isFalse()}.
    ///
    /// @param runnable The runnable to perhaps run
    /// @return {@code this}
    public final Mutabool ifTrue(Runnable runnable) {
        if (value) {
            runnable.run();
        }
        return this;
    }

    /// Runs the given runnable if the contained value
    /// is {@code false}.
    ///
    /// Returns {@code this} to support chaining with {@link  Mutabool#isTrue()}.
    ///
    /// @param runnable The runnable to perhaps run
    /// @return {@code this}
    public final Mutabool ifFalse(Runnable runnable) {
        if (!value) {
            runnable.run();
        }
        return this;
    }

    @Override
    public final String toString() {
        return Boolean.toString(value);
    }
}
