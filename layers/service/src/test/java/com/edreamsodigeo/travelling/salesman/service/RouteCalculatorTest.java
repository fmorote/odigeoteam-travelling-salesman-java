package com.edreamsodigeo.travelling.salesman.service;

import com.edreamsodigeo.travellingsalesman.model.Flight;
import com.edreamsodigeo.travellingsalesman.store.FlightStore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@TestInstance(Lifecycle.PER_CLASS)
class RouteCalculatorTest {

    private RouteCalculator routeCalculator;

    @BeforeAll
    void setUp() {
        List<Flight> fligths = List.of(
            mockFligth("1A-B", "A", "B",
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(1), ZoneId.systemDefault()),
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(3), ZoneId.systemDefault()),
                BigDecimal.valueOf(2)),
            mockFligth("2A-B", "A", "B",
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(1), ZoneId.systemDefault()),
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(5), ZoneId.systemDefault()),
                BigDecimal.valueOf(1)),
            mockFligth("3A-B", "A", "B",
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(1), ZoneId.systemDefault()),
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(7), ZoneId.systemDefault()),
                BigDecimal.valueOf(1)),
            mockFligth("4A-B", "A", "B",
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(1), ZoneId.systemDefault()),
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(3), ZoneId.systemDefault()),
                BigDecimal.valueOf(1)),
            mockFligth("5A-B", "A", "B",
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(1), ZoneId.systemDefault()),
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(4), ZoneId.systemDefault()),
                BigDecimal.valueOf(1)),
            mockFligth("6A-B", "A", "B",
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(1), ZoneId.systemDefault()),
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(3), ZoneId.systemDefault()),
                BigDecimal.valueOf(1)),
            mockFligth("7A-B", "A", "B",
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(1), ZoneId.systemDefault()),
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(5), ZoneId.systemDefault()),
                BigDecimal.valueOf(1)),
            mockFligth("8A-B", "A", "B",
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(1), ZoneId.systemDefault()),
                ZonedDateTime.ofInstant(Instant.ofEpochSecond(6), ZoneId.systemDefault()),
                BigDecimal.valueOf(1))
        );
        FlightStore flightStore = mock(FlightStore.class);
        when(flightStore.findsFlightsByCities("A", "B")).thenReturn(fligths);
        when(flightStore.findsFlightsByCities("A", "C")).thenReturn(EMPTY_LIST);
        routeCalculator = new RouteCalculator(flightStore);
    }

    @Test
    void calculate() {
        List<Flight> flights = routeCalculator.calculate("A", "B");
        List<String> codes = flights.stream().map(Flight::getCode).collect(toList());
        assertThat(codes, contains("4A-B", "6A-B"));

        flights = routeCalculator.calculate("A", "C");
        assertThat(flights, hasSize(0));

    }

    private Flight mockFligth(String code, String origin, String destination, ZonedDateTime departure, ZonedDateTime arrival, BigDecimal price) {
        Flight mock = mock(Flight.class);
        when(mock.getCode()).thenReturn(code);
        when(mock.getOriginCity()).thenReturn(origin);
        when(mock.getDestinationCity()).thenReturn(destination);
        when(mock.getDepartureDateTime()).thenReturn(departure);
        when(mock.getArrivalDateTime()).thenReturn(arrival);
        when(mock.getPrice()).thenReturn(price);
        return mock;
    }

}