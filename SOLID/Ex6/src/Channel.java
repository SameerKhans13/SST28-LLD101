public enum Channel {
    EMAIL("Email"),
    SMS("SMS"),
    WA("WhatsApp");

    private final String display;
    Channel(String display) { this.display = display; }
    @Override
    public String toString() { return display; }
}