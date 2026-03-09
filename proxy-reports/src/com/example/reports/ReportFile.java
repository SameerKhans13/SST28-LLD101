package com.example.reports;

/**
 * Legacy class representing a report file.  After the proxy refactor this
 * class is no longer used by client code; its logic has been moved into
 * {@link RealReport}, which now serves as the real subject.  It remains here
 * only for reference.
 *
 * (Original problems: clients called this directly, loading occurred on every
 * display, and there was no access control.)
 */
public class ReportFile {

    private final String reportId;
    private final String title;
    private final String classification; // PUBLIC / FACULTY / ADMIN

    public ReportFile(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    public void display(User user) {
        String content = loadFromDisk();
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
}