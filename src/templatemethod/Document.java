package templatemethod;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Result of an import: the file format it came from plus its key/value pairs,
 * in the order the source listed them.
 */
public record Document(String format, Map<String, String> values) {

    public Document {
        values = Collections.unmodifiableMap(new LinkedHashMap<>(values));
    }

    @Override
    public String toString() {
        return values.toString();
    }
}