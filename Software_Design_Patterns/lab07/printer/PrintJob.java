public class PrintJob implements Runnable {

    private final Document doc;
    private int jobId;
    private static int jobIdCount = 0;    

    public PrintJob(Document doc) {
        this.doc = doc;
        this.jobId = jobIdCount++;
    }

    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Printing " + doc.getName());
    }

    public int getJobId() {
        return jobId;
    }

    public Document getDocument() {
        return doc;
    }

    @Override
    public String toString() {
        return this.jobId + ": " + this.doc.getName();
    }
    
}
