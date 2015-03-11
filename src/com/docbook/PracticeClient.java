package com.docbook;

import org.restlet.Response;
import org.restlet.resource.ClientResource;

import java.io.IOException;

public class PracticeClient extends CommonClient{
    static final String practiceEndPoint = Config.SERVER_URL + "/api/v1/practices";     
    static final String practiceDetailEndPoint = Config.SERVER_URL + "/api/v1/practices/%s";     
    static final String practicePatientWatchEndPoint = Config.SERVER_URL + "/api/v1/practices/%s/patients/watch";     

    public static void main(String[] args) throws IOException{
        PracticeClient client = new PracticeClient();
        client.fetchPractices();
        client.fetchPracticeDetail("2");
        client.watchPracticePatient("2");
    }

    private void fetchPractices() throws IOException {
        System.out.println("fetchPractices:");
        ClientResource resource = getClientResource(practiceEndPoint);
        resource.get();
        Response response = resource.getResponse();
        String text = response.getEntity().getText();
        String status = response.getStatus().toString();
        System.out.println("status :" + status);
        System.out.println("Response :" + text);
    }

    private void fetchPracticeDetail( String practiceId) throws IOException {
        System.out.println("fetchPracticeDetail:");
        String requestUrl = String.format(practiceDetailEndPoint, practiceId );
        ClientResource resource = getClientResource(requestUrl);
        resource.get();
        Response response = resource.getResponse();
        String text = response.getEntity().getText();
        String status = response.getStatus().toString();
        System.out.println("status :" + status);
        System.out.println("Response :" + text);
    }

    private void watchPracticePatient(String practiceId) throws IOException {
        System.out.println("watchPracticePatient:");
        String requestUrl = String.format(practicePatientWatchEndPoint, practiceId );
        ClientResource resource = getClientResource(requestUrl);
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
