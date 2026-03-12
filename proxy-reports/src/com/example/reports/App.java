package com.example.reports;

/**
 * Problem: Earlier, our ReportViewer was talking directly to the ReportFile. 
 * This had major LLD issues:
 * 1. No security (anyone could see any report).
 * 2. Performance was bad because reports were loaded eagerly (immediately).
 * 3. No caching (same report loaded multiple times).
 *
 * Solution: We implemented the Proxy Design Pattern. The ReportProxy now acts as a gatekeeper.
 * It checks user permissions first. It also does 'lazy loading' (loads from disk only when needed) 
 * and caches the result for future views. This makes the system secure and fast.
 */
public class App {

    public static void main(String[] args) {
        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin = new User("Kshitij", "ADMIN");

        // create proxies instead of concrete files
        Report publicReport = new ReportProxy("R-101", "Orientation Plan", "PUBLIC");
        Report facultyReport = new ReportProxy("R-202", "Midterm Review", "FACULTY");
        Report adminReport = new ReportProxy("R-303", "Budget Audit", "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===");

        viewer.open(publicReport, student);
        System.out.println();

        viewer.open(facultyReport, student);
        System.out.println();

        viewer.open(facultyReport, faculty);
        System.out.println();

        viewer.open(adminReport, admin);
        System.out.println();

        viewer.open(adminReport, admin);
    }
}