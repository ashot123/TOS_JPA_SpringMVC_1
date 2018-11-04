package tos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tos.entity.City;
import tos.entity.Flight;
import tos.service.CityService;
import tos.service.FlightService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ashot Karakhanyan on 11-01-2014
 */


@Controller
@RequestMapping({"/flights"})
@SessionAttributes("flightForm") // keeps flightForm beside request scope
public class FlightController {

    // Init CityServiceImpl
    @Autowired
    private CityService cityService;

    @Autowired
    private FlightService flightService;


    // Populate city names
    @ModelAttribute("cityNames")
    public List<City> populateCityNames() {
        return cityService.selectAll();
    }

    // Injecting validators
   /* @Autowired
    @Qualifier("validator")
    private Validator validator;*/

    @Autowired
    @Qualifier("flightSearchFormValidator")
    private Validator flightSearchFormValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(flightSearchFormValidator);
        //binder.replaceValidators(validator, flightSearchFormValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("flightForm", new FlightSearchForm());
        return "redirect:/flights/selectFlight";
    }

    // Init flight search form
    @RequestMapping(method = RequestMethod.GET, value = {"selectFlight"})
    public String setupForm(Model model) {
        model.addAttribute("flightForm", new FlightSearchForm());
        return "findFlightsForm";
    }

    // Submitting form
    /*@Validated*/
    @RequestMapping(method = RequestMethod.POST, value = "submitForm")
    public String submitForm(@Valid @ModelAttribute("flightForm") FlightSearchForm flightForm,
                             BindingResult result, Model model) {

        //return "";
        //return "redirect:reservationSuccess";
        if (result.hasErrors()) {
            return "findFlightsForm";
        } else {


            return "redirect:foundSuccessController";
        }
    }

    @RequestMapping("/foundSuccessController")
    public String showFoundFlights(@ModelAttribute("flightForm") FlightSearchForm flightForm, Model model) {

        List<Flight> flights = flightService.selectFlightsByCriteria(flightForm.getDepartureCityId(),
                flightForm.getArrivalCityId(),
                flightForm.getDepartureDateTime(),
                flightForm.getArrivalDateTime());

        model.addAttribute("flights", flights);
        return "showFoundFlights";
    }


}
