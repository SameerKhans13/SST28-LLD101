public interface IInvoiceStore {
    void save(String id, String content);
    int countLines(String id);
}
