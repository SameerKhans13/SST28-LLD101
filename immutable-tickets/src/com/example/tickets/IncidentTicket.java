package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - mutable fields
 * - multiple constructors
 * - public setters
 * - tags list can be modified from outside
 * - validation is scattered elsewhere
 *
 * TODO (student): refactor to immutable + Builder.
 */
public class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority;       // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags;     // immutable copy
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;    // optional
    private final String source;         // e.g. "CLI", "WEBHOOK", "EMAIL"

    private IncidentTicket(Builder b) {
        this.id = b.id;
        this.reporterEmail = b.reporterEmail;
        this.title = b.title;
        this.description = b.description;
        this.priority = b.priority;
        this.tags = List.copyOf(b.tags);
        this.assigneeEmail = b.assigneeEmail;
        this.customerVisible = b.customerVisible;
        this.slaMinutes = b.slaMinutes;
        this.source = b.source;
    }

    // Getters
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public List<String> getTags() { return tags; } // safe: unmodifiable
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    public Builder toBuilder() {
        return Builder.from(this);
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }

    public static Builder builder(String id, String reporterEmail, String title) {
        return new Builder(id, reporterEmail, title);
    }

    public static final class Builder {
        // required
        private final String id;
        private final String reporterEmail;
        private final String title;

        // optional
        private String description;
        private String priority;
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible;
        private Integer slaMinutes;
        private String source;

        private Builder(String id, String reporterEmail, String title) {
            this.id = id;
            this.reporterEmail = reporterEmail;
            this.title = title;
        }

        public static Builder from(IncidentTicket ticket) {
            Builder b = new Builder(ticket.id, ticket.reporterEmail, ticket.title);
            b.description = ticket.description;
            b.priority = ticket.priority;
            b.tags = new ArrayList<>(ticket.tags);
            b.assigneeEmail = ticket.assigneeEmail;
            b.customerVisible = ticket.customerVisible;
            b.slaMinutes = ticket.slaMinutes;
            b.source = ticket.source;
            return b;
        }

        public Builder description(String d) { this.description = d; return this; }
        public Builder priority(String p) { this.priority = p; return this; }
        public Builder tags(List<String> list) { this.tags = new ArrayList<>(list); return this; }
        public Builder addTag(String tag) { this.tags.add(tag); return this; }
        public Builder assigneeEmail(String e) { this.assigneeEmail = e; return this; }
        public Builder customerVisible(boolean v) { this.customerVisible = v; return this; }
        public Builder slaMinutes(Integer m) { this.slaMinutes = m; return this; }
        public Builder source(String s) { this.source = s; return this; }

        public IncidentTicket build() {
            // validation
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            if (assigneeEmail != null) {
                Validation.requireEmail(assigneeEmail, "assigneeEmail");
            }
            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            // tags list should not be null
            if (tags == null) {
                tags = new ArrayList<>();
            }
            return new IncidentTicket(this);
        }
    }
}