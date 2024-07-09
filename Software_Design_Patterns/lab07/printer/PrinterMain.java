import java.util.ArrayList;
import java.util.List;


public class PrinterMain {

    public static void main(String[] args) {
        AdvancedPrinterInterface printer = new BasicToAdvanced(new BasicPrinter());

        Document doc1 = new Document("doc1");
        Document doc2 = new Document("doc2");
        Document doc3 = new Document("doc3");

        List<Document> docs = new ArrayList<>();

        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);

        printer.print(doc1);
        printer.print(doc2);
        printer.print(doc3);

        printer.showQueuedJobs();
        
        printer.cancelJob(1);
        printer.cancelJob(2);

        printer.showQueuedJobs();

        printer.cancelAll();
        printer.showQueuedJobs();
    }
    
}
