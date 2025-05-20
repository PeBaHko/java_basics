public class ConnectionMosMetro {
  private final String name;
  private final String line;

    public ConnectionMosMetro(String line, String name) {
        this.name = name;
        this.line = line;
    }
    public String getName() { return name; }
    public String getLine() { return line; }
}