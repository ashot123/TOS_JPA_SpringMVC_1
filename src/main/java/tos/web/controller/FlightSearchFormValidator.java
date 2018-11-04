package tos.web.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Ashot Karakhanyan on 04-05-2014
 */
public class FlightSearchFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FlightSearchForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        FlightSearchForm flightForm = (FlightSearchForm) target;

        // if both departure and arrival cities are empty
        if(flightForm.getDepartureCityId() == 0 && flightForm.getArrivalCityId() == 0) {
            errors.reject("differenceMismatch.flightForm.citiesAreEmpty",
                    new Object[]{}, "At least one city should be selected");
        }

        // if departure city is the same as arrival city
        if(flightForm.getDepartureCityId() != 0 && flightForm.getArrivalCityId() != 0 &&
                flightForm.getDepartureCityId() == flightForm.getArrivalCityId()) {

            errors.reject("differenceMismatch.flightForm.citiesAreEqual",
                    new Object[]{}, "Departure and arrival cities can not be the same");
        }

        if (flightForm.getArrivalDate() != null &&
                flightForm.getDepartureDate() != null &&
                flightForm.getArrivalTime() != null &&
                flightForm.getDepartureTime() != null &&
                flightForm.getArrivalDateTime().before(flightForm.getDepartureDateTime())) {


            DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            String departureDateStr = sdf.format(flightForm.getDepartureDateTime());
            String arrivalDateStr = sdf.format(flightForm.getArrivalDateTime());

            errors.reject("differenceMismatch.flightForm.departureDate.arrivalDate",
                    new Object[]{departureDateStr, arrivalDateStr}, "Date are different");


        }




    }
}