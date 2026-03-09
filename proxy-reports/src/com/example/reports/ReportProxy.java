package com.example.reports;

/**
 * TODO (student):
 * Implement Proxy responsibilities here:
 * - access check
 * - lazy loading
 * - caching of RealReport within the same proxy
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    // cached real subject; null until we are allowed to load it
    private RealReport real;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: user=" + user.getName()
                    + " role=" + user.getRole()
                    + " cannot view report " + reportId);
            return;
        }

        if (real == null) {
            System.out.println("[proxy] creating real report for " + reportId);
            real = new RealReport(reportId, title, classification);
        }
        real.display(user);
    }
}