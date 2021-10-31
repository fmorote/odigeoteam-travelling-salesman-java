package com.edreamsodigeo.travellingsalesman.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public interface Flight {

    String getCode();

    String getOriginCity();

    String getDestinationCity();

    BigDecimal getPrice();

    ZonedDateTime getDepartureDateTime();

    ZonedDateTime getArrivalDateTime();

}
