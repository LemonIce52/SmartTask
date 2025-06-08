package Tasks;

public record Task(int id, String text) {
    @Override
    public String toString() {
        return id + ". " + text;
    }
}
