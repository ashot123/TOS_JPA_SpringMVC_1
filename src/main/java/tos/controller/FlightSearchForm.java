package tos.controller;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Ashot Karakhanyan on 30-04-2014
 */
public class FlightSearchForm {

    private int departureCityId;
    private int arrivalCityId;


    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotNull
    private Date departureDate = new Date();

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotNull
    private Date arrivalDate = new Date();


    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private Date departureTime = getFirstMinuteOfDay(); // 00:00

    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private Date arrivalTime = getLastMinuteOfDay(); //23:59

    private boolean departureTimeSelected = false;

    private boolean arrivalTimeSelected = false;

    // Departure and Arrival city id-s
    public int getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCityId(int departureCityId) {
        this.departureCityId = departureCityId;
    }

    // Getters and Setters
    public int getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(int arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    // Departure Date, Time and time selection
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    // Departure time, Arrival time
    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    //Departure time selected
    public boolean isDepartureTimeSelected() {
        return departureTimeSelected;
    }

    public void setDepartureTimeSelected(boolean departureTimeSelected) {
        this.departureTimeSelected = departureTimeSelected;
    }

    //Arrival time selected
    public boolean isArrivalTimeSelected() {
        return arrivalTimeSelected;
    }

    public void setArrivalTimeSelected(boolean arrivalTimeSelected) {
        this.arrivalTimeSelected = arrivalTimeSelected;
    }


    // Return time 00:00
    public Date getFirstMinuteOfDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

    // Return time 23:59
    public Date getLastMinuteOfDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        return calendar.getTime();
    }


    public Date getDepartureDateTime() {
        return getDepartureDateTime(departureDate, departureTime);
    }

    public Date getArrivalDateTime() {
        return getDepartureDateTime(arrivalDate, arrivalTime);
    }

    private Date getDepartureDateTime(Date date, Date time){
        Calendar targetCalendar = Calendar.getInstance();

        Calendar dateCalendar = new GregorianCalendar();
        dateCalendar.setTime(date);

        Calendar timeCalendar = new GregorianCalendar();
        timeCalendar.setTime(time);

        targetCalendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
        targetCalendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
        targetCalendar.set(Calendar.DAY_OF_MONTH, dateCalendar.get(Calendar.DAY_OF_MONTH));

        targetCalendar.set(Calendar.HOUR, timeCalendar.get(Calendar.HOUR));
        targetCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));

        return targetCalendar.getTime();
    }


    /*public Date getArrivalDateTime() {

        if (!arrivalTimeSelected) {
            arrivalTime = DEFAULT_END_TIME;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        try {
            return dateFormat.parse(departureDate + " " + departureTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }*/





    /*    public FlightSearchForm() {
        System.out.println("In constructor");
    }*/

 /*   public static Date getDefaultStartTime() {
        return DEFAULT_START_TIME;
    }
*/

   /* public static String getDefaultEndTime() {
        return DEFAULT_END_TIME;
    }*/


}
