package com.edreamsodigeo.travelling.salesman.service;

import com.edreamsodigeo.travellingsalesman.model.Flight;
import com.edreamsodigeo.travellingsalesman.store.FlightStore;

import javax.inject.Inject;
import java.util.List;

public class RouteCalculator {

    @Inject
    private FlightStore flightStore;

    public List<Flight> calculate(String originCity, String destinationCity) {
        return null;
    }
}
