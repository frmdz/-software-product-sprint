public final class Message {
  public long id;
  public String name;
  public String message;
  public long timestamp;

  public Message(long id, String name, String message, long timestamp) {
    this.id = id;
    this.name = name;
    this.message = message;
    this.timestamp = timestamp;
  }
}