import java.util.List;
import java.util.Queue;
import java.util.ArrayList;


public class BasicToAdvanced implements AdvancedPrinterInterface {

    private BasicPrinter basicPrinter;
    private Queue<PrintJob> printQueue;

    public BasicToAdvanced(BasicPrinter basicPrinter) {
        this.basicPrinter = basicPrinter;
    }

    public int print(Document doc) {
        PrintJob new_job = new PrintJob(doc);
        printQueue.add(new_job);
        if (!basicPrinter.hasInk() || !basicPrinter.hasPaper()) {
            return -1;
        }
        return new_job.getJobId();
    }

    public List<Integer> print(List<Document> docs) {
        List<Integer> jobIds = new ArrayList<>();
        for (Document doc : docs) {
            jobIds.add(print(doc));
        }
        for (int i = 0; i < jobIds.size(); i++) {
            if (!basicPrinter.hasInk() || !basicPrinter.hasPaper()) {
                jobIds.set(i, -1);
            }
            else {
                jobIds.set(i, print(docs.get(i)));
            }
        }
        return jobIds;
    }
    
    public void showQueuedJobs() {
        if (printQueue.isEmpty()) {
            System.out.println("No jobs in queue");
        }
        else {
            for (PrintJob job : printQueue) {
                System.out.println(job);
            }
        }
    }

    public boolean cancelJob(int jobId) {
        return printQueue.removeIf(job -> job.getJobId() == jobId);
    }

    public void cancelAll() {
        printQueue.clear();
    }


}
