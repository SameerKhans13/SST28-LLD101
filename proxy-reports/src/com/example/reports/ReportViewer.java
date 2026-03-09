package com.example.reports;

/**
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - Viewer depends directly on concrete ReportFile
 * - No Proxy involved
 */
public class ReportViewer {

    // viewer now depends only on the Report interface
    public void open(Report report, User user) {
        report.display(user);
    }
}