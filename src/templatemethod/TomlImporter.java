package templatemethod;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Concrete importer for TOML. It overrides {@link #shouldTransform()} to skip
 * the transform step, showing that a hook may also remove a step from the
 * skeleton for one specific format.
 */
public final class TomlImporter extends DataImporter {

    private static final String SOURCE = """
            title = "Clean Code"
            pages = "464"
            author = "Martin"
            """;

    @Override
    protected String formatName() {
        return "toml";
    }

    @Override
    protected String readSource() {
        return SOURCE;
    }

    /** Reads {@code key = "value"} lines. */
    @Override
    protected Map<String, String> parse(String raw) {
        Map<String, String> fields = new LinkedHashMap<>();
        for (String line : raw.lines().toList()) {
            int equals = line.indexOf('=');
            if (equals < 0) {
                continue;
            }
            fields.put(line.substring(0, equals).strip(), line.substring(equals + 1).strip().replace("\"", ""));
        }
        return fields;
    }

    @Override
    protected Map<String, String> transform(Map<String, String> fields) {
        throw new UnsupportedOperationException("toml files are imported without transforming them");
    }

    /** Hook override: TOML needs no normalisation. */
    @Override
    protected boolean shouldTransform() {
        return false;
    }
}