package templatemethod;

/** Runnable demonstration of the Template method pattern. */
public final class Demo {

    private Demo() {
    }

    public static void main(String[] args) {
        System.out.println("=== Template method: one algorithm, different file formats ===");

        DataImporter[] importers = {new JsonImporter(), new YamlImporter(), new TomlImporter()};

        for (DataImporter importer : importers) {
            importer.importData();
        }

        System.out.println("importData() is final, so the steps run in the same order every time.");
        System.out.println("The subclasses only fill in what differs: read, parse and transform.");
    }
}