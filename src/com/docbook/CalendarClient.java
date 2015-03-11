package com.docbook;

import org.restlet.Response;
import org.restlet.resource.ClientResource;

import java.io.IOException;

public class CalendarClient extends CommonClient{
    static final String calendarEndPoint = Config.SERVER_URL + "/api/v1/calendars";     
    static final String calendarDetailEndPoint = Config.SERVER_URL + "/api/v1/calendars/%s";     
    static final String calendarAppointmentWatchEndPoint = Config.SERVER_URL + "/api/v1/calendars/%s/appointments/watch";     

    public static void main(String[] args) throws IOException{
        CalendarClient client = new CalendarClient();
        String calendarId = "2";
        String practiceId = "2";
        client.fetchCalendars(practiceId);
        client.fetchCalendarDetail(calendarId, practiceId);
        client.watchCalendarAppointments(calendarId, practiceId);
    }

    private void fetchCalendars(String practiceId) throws IOException {
        System.out.println("fetchCalendars:");
        ClientResource resource = getClientResource(calendarEndPoint);
        resource.addQueryParameter("practiceId", practiceId);
        resource.addQueryParameter("max", "10");        //If not set Default value will be 10
        resource.addQueryParameter("offset", "0");      //If not set Default value will be 0
        resource.get();
        Response response = resource.getResponse();
        String text = response.getEntity().getText();
        String status = response.getStatus().toString();
        System.out.println("status :" + status);
        System.out.println("Response :" + text);
    }

    private void fetchCalendarDetail(String calendarId, String practiceId) throws IOException {
        System.out.println("fetchCalendarDetail:");
        String requestUrl = String.format(calendarDetailEndPoint, calendarId );
        ClientResource resource = getClientResource(requestUrl);
        resource.addQueryParameter("practiceId", practiceId);
        resource.get();
        Response response = resource.getResponse();
        String text = response.getEntity().getText();
        String status = response.getStatus().toString();
        System.out.println("status :" + status);
        System.out.println("Response :" + text);
    }

    private void watchCalendarAppointments(String calendarId, String practiceId) throws IOException {
        System.out.println("watchCalendarAppointments:");
        String requestUrl = String.format(calendarAppointmentWatchEndPoint, calendarId );
        ClientResource resource = getClientResource(requestUrl);
        resource.addQueryParameter("practiceId", practiceId);
        resource.addQueryParameter("fromDate", "2015-01-01T10:00:00Z");
        resource.addQueryParameter("max", "10");        //If not set Default value will be 10
        resource.addQueryParameter("offset", "0");      //If not set Default value will be 0
        resource.get();
        Response response = resource.getResponse();
        String text = response.getEntity().getText();
        String status = response.getStatus().toString();
        System.out.println("status :" + status);
        System.out.println("Response :" + text);
    }

}
