package templatemethod;

import java.util.Map;

/**
 * The base class of the Template method pattern.
 *
 * <p>{@link #importData()} is {@code final}: it defines the skeleton of the
 * algorithm (read -> parse -> validate -> hook -> transform -> report). Steps
 * marked {@code abstract} are the varying parts that subclasses must fill in;
 * {@link #validate(Map)}, {@link #beforeTransform()} and
 * {@link #shouldTransform()} are hooks with a default behaviour that a subclass
 * may override.
 *
 * <p>Every importer therefore follows the exact same steps in the exact same
 * order, and only the file-format specific work differs.
 */
public abstract class DataImporter {

    /** Runs the whole algorithm. Subclasses may not reorder or skip these steps. */
    public final void importData() {
        System.out.println("== " + formatName() + " importer ==");

        String raw = readSource();
        System.out.printf("  1. read source   : %s%n", abbreviate(raw));

        Map<String, String> fields = parse(raw);
        System.out.printf("  2. parse         : %d field(s) -> %s%n", fields.size(), new Document(formatName(), fields));

        System.out.printf("  3. validate      : %s%n", validate(fields));

        beforeTransform();

        if (shouldTransform()) {
            fields = transform(fields);
            System.out.printf("  5. transform     : %s%n", new Document(formatName(), fields));
        } else {
            System.out.println("  5. transform     : skipped (shouldTransform() returned false)");
        }

        System.out.printf("  6. report        : %d record(s) imported from %s%n%n", fields.size(), formatName());
    }

    // ---- steps every importer must provide --------------------------------

    /** Name of the file format, used in the log output. */
    protected abstract String formatName();

    /** Returns the raw file contents. */
    protected abstract String readSource();

    /** Turns the raw contents into key/value pairs. */
    protected abstract Map<String, String> parse(String raw);

    /** Normalises the parsed fields, e.g. trimming values. */
    protected abstract Map<String, String> transform(Map<String, String> fields);

    // ---- hooks with a default behaviour -----------------------------------

    /** Hook: called between validation and transformation. Does nothing here. */
    protected void beforeTransform() {
    }

    /** Hook: lets a subclass skip the transform step. Imports as-is by default. */
    protected boolean shouldTransform() {
        return true;
    }

    /** Hook: reports on the parsed fields. Says "ok" unless nothing was parsed. */
    protected String validate(Map<String, String> fields) {
        return fields.isEmpty() ? "no data found" : "ok";
    }

    // ---- small helper shared by the subclasses -----------------------------

    /** Shortens a source snippet so the log stays readable. */
    protected static String abbreviate(String raw) {
        String oneLine = raw.strip().replace("\n", " ");
        return oneLine.length() <= 48 ? oneLine : oneLine.substring(0, 47) + "~";
    }
}