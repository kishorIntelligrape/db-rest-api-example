package com.docbook;

import org.restlet.Response;
import org.restlet.resource.ClientResource;

import java.io.IOException;

public class VisitReasonClient extends CommonClient{
    static final String visitReasonEndPoint = Config.SERVER_URL + "/api/v1/visitReasons";
    static final String visitReasonDetailEndPoint = Config.SERVER_URL + "/api/v1/visitReasons/%s";

    public static void main(String[] args) throws IOException{
        VisitReasonClient client = new VisitReasonClient();
        client.fetchCalendarVisitReasons("2", "2");
        client.fetchCalendarVisitReasonDetail("21", "2", "2");
    }

    private void fetchCalendarVisitReasons(String calendarId, String practiceId) throws IOException {
        System.out.println("fetchCalendars:");
        ClientResource resource = getClientResource(visitReasonEndPoint);
        resource.addQueryParameter("calendarId", calendarId);
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

    private void fetchCalendarVisitReasonDetail(String visitReasonId, String calendarId, String practiceId) throws IOException {
        System.out.println("fetchCalendarDetail:");
        String requestUrl = String.format(visitReasonDetailEndPoint, visitReasonId );
        ClientResource resource = getClientResource(requestUrl);
        resource.addQueryParameter("calendarId", calendarId);
        resource.addQueryParameter("practiceId", practiceId);
        resource.get();
        Response response = resource.getResponse();
        String text = response.getEntity().getText();
        String status = response.getStatus().toString();
        System.out.println("status :" + status);
        System.out.println("Response :" + text);
    }
}
