import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Visitor {

    public static class SizeEntry {
        private final String name;
        private final long size;

        public SizeEntry(String name, long size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public long getSize() {
            return size;
        }

        @Override
        public String toString() {
            return name + ": " + size / 1024 + " kB";
        }
    }

    private static class SizeFileVisitor extends SimpleFileVisitor<Path> {
        private final List<SizeEntry> entries = new ArrayList<>();
        private long totalSize = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            entries.add(new SizeEntry(file.getFileName().toString(), attrs.size()));
            totalSize += attrs.size();
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println("Não foi possível acessar o arquivo: " + file + " (" + exc.getMessage() + ")");
            return FileVisitResult.CONTINUE;
        }

        public List<SizeEntry> getEntries() {
            return entries;
        }

        public long getTotalSize() {
            return totalSize;
        }
    }

    public static List<SizeEntry> calculateDirectorySize(Path path, boolean recursive) throws IOException {
        if (recursive) {
            SizeFileVisitor visitor = new SizeFileVisitor();
            Files.walkFileTree(path, visitor);
            return visitor.getEntries();
        } else {
            List<SizeEntry> entries = new ArrayList<>();
            try (var stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    if (Files.isRegularFile(entry)) {
                        long size = Files.size(entry);
                        entries.add(new SizeEntry(entry.getFileName().toString(), size));
                    }
                }
            }
            return entries;
        }
    }
}
