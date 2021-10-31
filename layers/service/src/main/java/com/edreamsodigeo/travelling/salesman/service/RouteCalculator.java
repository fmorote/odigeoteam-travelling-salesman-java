package com.edreamsodigeo.travelling.salesman.service;

import com.edreamsodigeo.travellingsalesman.model.Flight;
import com.edreamsodigeo.travellingsalesman.store.FlightStore;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class RouteCalculator {

    @Inject
    private final FlightStore flightStore;

    public List<Flight> calculate(String originCity, String destinationCity) {
        List<Flight> flights = flightStore.findsFlightsByCities(originCity, destinationCity);

        List<Flight> shortest = flights.stream()
            .filter(flight -> SECONDS.between(flight.getDepartureDateTime(), flight.getArrivalDateTime()) == minLength(flights))
            .collect(toList());

        return shortest.stream()
            .filter(flight -> flight.getPrice().longValue() == minPrice(shortest))
            .collect(toList());

    }

    private long minLength(List<Flight> fligths) {
        return fligths.stream()
            .mapToLong(flight -> SECONDS.between(flight.getDepartureDateTime(), flight.getArrivalDateTime()))
            .min()
            .orElse(-1);
    }

    private long minPrice(List<Flight> fligths) {
        return fligths.stream()
            .mapToLong(flight -> flight.getPrice().longValue())
            .min()
            .orElse(-1);
    }

}
