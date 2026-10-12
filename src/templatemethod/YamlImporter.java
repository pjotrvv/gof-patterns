package templatemethod;

import java.util.LinkedHashMap;
import java.util.Map;

/** Concrete importer for YAML. Same skeleton as every other importer. */
public final class YamlImporter extends DataImporter {

    private static final String SOURCE = """
            title: Refactoring
            pages: "448"
            author: Fowler
            """;

    @Override
    protected String formatName() {
        return "yaml";
    }

    @Override
    protected String readSource() {
        return SOURCE;
    }

    /** Reads {@code key: value} lines; the last colon separates key from value. */
    @Override
    protected Map<String, String> parse(String raw) {
        Map<String, String> fields = new LinkedHashMap<>();
        for (String line : raw.lines().toList()) {
            if (line.isBlank() || line.strip().startsWith("#")) {
                continue;
            }
            int colon = line.indexOf(':');
            if (colon < 0) {
                continue;
            }
            fields.put(line.substring(0, colon).strip(), unquote(line.substring(colon + 1)));
        }
        return fields;
    }

    @Override
    protected Map<String, String> transform(Map<String, String> fields) {
        Map<String, String> normalized = new LinkedHashMap<>();
        fields.forEach((key, value) -> normalized.put(key, value.strip().toLowerCase()));
        return normalized;
    }

    @Override
    protected void beforeTransform() {
        System.out.println("  4. beforeTransform: yaml values are lower-cased");
    }

    private static String unquote(String text) {
        return text.strip().replace("\"", "");
    }
}