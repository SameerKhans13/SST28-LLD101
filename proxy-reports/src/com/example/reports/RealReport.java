package com.example.reports;

/**
 * Real subject implementing {@link Report}.  The expensive file‑loading logic
 * lives here and is executed lazily when the object is constructed (via the
 * proxy).  Once created, the content is cached in the instance so repeated
 * displays do not reload from disk.
 */
public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;

    // content is expensive to obtain; we load it once in the constructor
    private final String content;

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
        this.content = loadFromDisk();
    }

    @Override
    public void display(User user) {
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + content);
    }

    private String loadFromDisk() {
        System.out.println("[disk] loading report " + reportId + " ...");
        try { Thread.sleep(120); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Internal report body for " + title;
    }

    public String getClassification() {
        return classification;
    }
}