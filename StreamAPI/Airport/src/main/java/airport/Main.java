package airport;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.Instant;
import java.util.*;
import java.util.stream.*;

public class Main {
public static void main(String[] args) {
    Instant in = Instant.now().plusSeconds(3500);

    System.out.println(in.isBefore(Instant.now().plusSeconds(3600)));
}
    public static long findCountAircraftWithModelAirbus(Airport airport, String model) {
        //TODO Метод должен вернуть количество самолетов указанной модели.
        // подходят те самолеты, у которых name начинается со строки model
        return
        Stream.of(List.of(airport.getAllAircrafts()))
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .filter(air -> air.getModel().contains(model))
                .count();
    }

    public static Map<String, Integer> findMapCountParkedAircraftByTerminalName(Airport airport) {
        //TODO Метод должен вернуть словарь с количеством припаркованных самолетов в каждом терминале.
        Map<String, Integer> map = new TreeMap<>();
        airport
                .getTerminals()
                .forEach(list -> map.put(list.getName(), list.getParkedAircrafts().size()));
        return map;
    }

    public static List<Flight> findFlightsLeavingInTheNextHours(Airport airport, int hours) {
        //TODO Метод должен вернуть список отправляющихся рейсов в ближайшее количество часов.
        List<Flight> flight = new ArrayList<>();
        airport
                .getTerminals()
                .stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(type -> type.getType() == Flight.Type.DEPARTURE)
                .filter(time -> time.getDate().isBefore(Instant.now().plusSeconds(hours * 3600L)))
                .filter(time -> time.getDate().isAfter(Instant.now()))
                .forEach(flight::add);
        return flight;
    }

    public static Optional<Flight> findFirstFlightArriveToTerminal(Airport airport, String terminalName) {
        //TODO Найти ближайший прилет в указанный терминал.
        List<Flight> flights = new ArrayList<>();
        airport.
                getTerminals()
                .stream()
                .filter(terminal -> terminal.getName().equalsIgnoreCase(terminalName))
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Flight::getDate))
                .filter(type -> type.getType() == Flight.Type.ARRIVAL)
                .limit(1)
                .forEach(flights::add);
                return flights.isEmpty() ? Optional.empty() : Optional.ofNullable(flights.get(0));
    }
}