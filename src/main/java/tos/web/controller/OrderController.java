package tos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tos.entity.Flight;
import tos.service.FlightService;
import tos.service.OrderService;
import tos.service.exception.ServiceException;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

/**
 * Created by Ashot Karakhanyan on 11-05-2014
 */

@Controller
@RequestMapping("/order")
@SessionAttributes({"orderForm"/*, "flightId"*/})
public class OrderController {


    @Autowired
    private FlightService flightService;

    @Autowired
    private OrderService orderService;


    // Injecting validators
   /* @Autowired
    @Qualifier("standardValidator")
    private Validator validator;
*/
    @Autowired
    @Qualifier("orderFormValidator")
    private Validator orderValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(orderValidator);
        //binder.replaceValidators(validator, orderValidator);
    }


    /*   @RequestMapping()
       //@RequestMapping(*//*value = "/setupForm/{flightId}", *//*method = RequestMethod.GET)
    public String setupForm1(Model model) {
        //model.addAttribute("flightForm", new FlightSearchForm());
        return "findFlightsForm";
    }

    @RequestMapping(value = "/setupForm")
    //@RequestMapping(*//*value = "/setupForm/{flightId}", *//*method = RequestMethod.GET)
    public String setupForm2(Model model) {
        //model.addAttribute("flightForm", new FlightSearchForm());
        return "findFlightsForm";
    }
*/
    @RequestMapping(value = "/{flightId}", method = RequestMethod.GET)
    public String setupForm(@PathVariable Long flightId, Model model) {
        Flight selectedFlight = flightService.selectById(flightId);


        OrderForm orderForm = new OrderForm();
        orderForm.initFrom(selectedFlight);
        model.addAttribute("orderForm", orderForm);
        orderForm.setFlightId(selectedFlight.getId());
        //model.addAttribute("flightId", flightId);
        //model.addAttribute("selectedFlight", selectedFlight);
        return "orderFlightForm";
    }


    @RequestMapping(method = RequestMethod.POST, value = "submitForm")
    public String submitForm(@Valid @ModelAttribute("orderForm") OrderForm orderForm,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "orderFlightForm";
        } else {

            try {
                orderService.orderTickets(orderForm.getFlightId(), orderForm.getCreditCardNumber(),
                        orderForm.getClass1TicketsCount(), orderForm.getClass2TicketsCount());
            } catch (PersistenceException e) {
                // add error
                return "orderFlightForm";

            }


            return "redirect:orderSuccessController";
        }
    }

    @RequestMapping("/orderSuccessController")
    public String FoundSuccess(Model model) {
        // Return view reservationSuccess. Via resolver the view
        // will be mapped to /WEB-INF/jsp/reservationSuccess.jsp
        //model.addAttribute("successMsg", "You successfully ordered Tickets!");
        return "orderSuccess";
    }


}
