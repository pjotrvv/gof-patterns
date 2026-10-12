package templatemethod;

import java.util.LinkedHashMap;
import java.util.Map;

/** Concrete importer for JSON. Only the format specific steps are implemented. */
public final class JsonImporter extends DataImporter {

    private static final String SOURCE = """
            { "title": "Design Patterns", "pages": "320", "author": "Gamma" }""";

    @Override
    protected String formatName() {
        return "json";
    }

    @Override
    protected String readSource() {
        return SOURCE;
    }

    /**
     * Minimal hand-rolled parse of a flat {@code {"k": "v", ...}} object - just
     * enough to keep this example free of dependencies.
     */
    @Override
    protected Map<String, String> parse(String raw) {
        Map<String, String> fields = new LinkedHashMap<>();
        String body = raw.strip();
        body = body.substring(body.indexOf('{') + 1, body.lastIndexOf('}'));
        for (String pair : body.split(",")) {
            int colon = pair.indexOf(':');
            if (colon < 0) {
                continue;
            }
            String key = unquote(pair.substring(0, colon));
            String value = unquote(pair.substring(colon + 1));
            fields.put(key, value);
        }
        return fields;
    }

    @Override
    protected Map<String, String> transform(Map<String, String> fields) {
        Map<String, String> normalized = new LinkedHashMap<>();
        fields.forEach((key, value) -> normalized.put(key.toUpperCase(), value.strip()));
        return normalized;
    }

    private static String unquote(String text) {
        return text.strip().replace("\"", "");
    }
}