
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: java -jar sizeOf.jar <diretório> [-r]");
            System.exit(1);
        }

        Path directory = Paths.get(args[0]);
        boolean recursive = args.length > 1 && args[1].equals("-r");

        try {
            List<Visitor.SizeEntry> entries = Visitor.calculateDirectorySize(directory, recursive);
            long totalSize = 0;
            for (Visitor.SizeEntry entry : entries) {
                totalSize += entry.getSize();
                System.out.println(entry);
            }
            System.out.println("Total: " + totalSize / 1024 + " kB");
            System.out.println("==================================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
