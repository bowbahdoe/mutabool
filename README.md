# MUTABOOLS

Have you ever wanted a mutable a boolean?

Requires Java 21+

```java
import dev.mccue.mutabool.Mutabool;

import java.io.IO;
import java.util.List;

void main() {
    var allEven = new Mutabool(true);
    List.of(1, 2, 3).forEach(x -> {
        if (x % 2 != 0) {
            allEven.setFalse();
        }
    });

    allEven
            .ifTrue(() -> IO.println("All Even"))
            .ifFalse(() -> IO.println("Not All Even"));
}
```

```xml
<dependency>
    <groupId>dev.mccue</groupId>
    <artifactId>mutabool</artifactId>
    <version>2025.03.20</version>
</dependency>
```