package tos.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Ashot Karakhanyan on 04-05-2014
 */


public class OrderFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return tos.controller.OrderForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        tos.controller.OrderForm orderForm = (OrderForm) target;

        int class1TicketsCount = orderForm.getClass1TicketsCount();
        int class2TicketsCount = orderForm.getClass2TicketsCount();

        int class1TicketsAvailable = orderForm.getClass1TicketsAvailable();
        int class2TicketsAvailable = orderForm.getClass2TicketsAvailable();

        if(class1TicketsCount > class1TicketsAvailable) {
            errors.rejectValue("class1TicketsCount", "differenceMismatch.orderForm.notEnoughTickets",
                    new Object[]{class1TicketsCount, class1TicketsAvailable}, "Problem");
        }

        if(class2TicketsCount > class2TicketsAvailable) {
            errors.rejectValue("class2TicketsCount", "differenceMismatch.orderForm.notEnoughTickets",
                    new Object[]{class2TicketsCount, class2TicketsAvailable}, "Problem");
        }

        if(class1TicketsCount == 0 && class2TicketsCount == 0) {
            errors.reject("differenceMismatch.orderForm.noTicketSelected",
                    new Object[]{}, "Problem 2");
        }

   /*     // if both departure and arrival cities are empty
        if(orderForm.getDepartureCityId() == 0 && orderForm.getArrivalCityId() == 0) {
            errors.reject("differenceMismatch.flightForm.citiesAreEmpty",
                    new Object[]{}, "At least one city should be selected");
        }

        // if departure city is the same as arrival city
        if(orderForm.getDepartureCityId() != 0 && orderForm.getArrivalCityId() != 0 &&
                orderForm.getDepartureCityId() == orderForm.getArrivalCityId()) {

            errors.reject("differenceMismatch.flightForm.citiesAreEqual",
                    new Object[]{}, "Departure and arrival cities can not be the same");
        }

        if (orderForm.getArrivalDate() != null &&
                orderForm.getDepartureDate() != null &&
                orderForm.getArrivalTime() != null &&
                orderForm.getDepartureTime() != null &&
                orderForm.getArrivalDateTime().before(orderForm.getDepartureDateTime())) {


            DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            String departureDateStr = sdf.format(orderForm.getDepartureDateTime());
            String arrivalDateStr = sdf.format(orderForm.getArrivalDateTime());

            errors.reject("differenceMismatch.flightForm.departureDate.arrivalDate",
                    new Object[]{departureDateStr, arrivalDateStr}, "Date are different");


        }

*/


    }
}