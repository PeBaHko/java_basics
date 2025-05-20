import java.util.*;
public class StationMosMetro {
  private final String name;
  private final String line;
  private final String  number;
  private final String date;
  private final String depth;
  private final boolean hasConnection;
  private final ArrayList<ConnectionMosMetro> connections;

  public StationMosMetro(String number, String  name, String line, String date, String depth, boolean hasConnection, ArrayList<ConnectionMosMetro> connections) {
    this.name = name;
    this.number = number;
    this.line = line;
    this.date = date;
    this.depth = depth;
    this.hasConnection = hasConnection;
    //connections = new ArrayList<>();
    this.connections = connections;
  }
//  public boolean addConnection(ConnectionMosMetro connection) {
//    if (hasConnection) {
//      connections.add(connection);
//      return true;
//    } else { return false; }
//  }
  public String getName() { return name; }
  public String getNumber() { return number; }
  public String getLine() { return line; }
  public String getDate() { return date; }
  public String getDepth() { return depth; }
  public boolean isHasConnection() { return hasConnection; }
  public ArrayList<ConnectionMosMetro> getConnections() {
    if (hasConnection) { return new ArrayList<>(connections);
    } else { return null; }
  }

  @Override
  public String toString() {
    return "StationMosMetro{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
  }
}