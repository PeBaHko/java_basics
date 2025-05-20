import java.util.*;

public class LineMosMetro {
  private final String name;
  private final String number;
  private final ArrayList<StationMosMetro> stations;

  public LineMosMetro(String number, String  name) {
    this.number = number;
    this.name = name; //StationMosMetro a;
    stations = new ArrayList<>();
  }

  public void addStation(StationMosMetro station) { stations.add(station); }
  public String getName() { return name; }
  public String getNumber() { return number; }
  public ArrayList<StationMosMetro> getStations() { return new ArrayList<>(stations); }

  @Override
  public String toString() {
    return "LineMosMetro{" +
           "name='" + name + "'" +
           ", number=" + number +
           "}";
  }
}