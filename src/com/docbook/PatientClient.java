package com.docbook;

import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;

import java.io.IOException;

public class PatientClient extends CommonClient{
    static final String patientEndPoint = Config.SERVER_URL + "/api/v1/patients";
    static final String practiceDetailEndPoint = Config.SERVER_URL + "/api/v1/patients/%s";

    /**
     * User need to update the practice, and patient id
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        PatientClient client = new PatientClient();
        client.fetchPatients("2");
        client.fetchPracticePatientDetail("339", "2");
        client.createNewPracticePatient("2");
        client.updatePracticePatient("339", "2");
    }

    private void fetchPatients(String practiceId) throws IOException {
        System.out.println("fetchPractices:");
        ClientResource resource = getClientResource(patientEndPoint);
        resource.addQueryParameter("practiceId", practiceId);
        resource.addQueryParameter("max", "10");        //If not set Default value will be 10 (optional)
        resource.addQueryParameter("offset", "0");      //If not set Default value will be 0 (optional)
        resource.addQueryParameter("includes", "id, firstName, lastName");      // Includes specified fields only in response (optional)

        resource.get();
        Response response = resource.getResponse();
        String text = response.getEntity().getText();
        String status = response.getStatus().toString();
        System.out.println("status :" + status);
        System.out.println("Response :" + text);
    }

    private void fetchPracticePatientDetail(String patientId, String practiceId) throws IOException {
        System.out.println("fetchPracticeDetail:");
        String requestUrl = String.format(practiceDetailEndPoint, patientId );
        ClientResource resource = getClientResource(requestUrl);
        resource.addQueryParameter("practiceId", practiceId);
        resource.addQueryParameter("includes", "id,firstName,lastName");      // Includes specified fields only in response (optional)
        resource.get();
        Response response = resource.getResponse();
        String text = response.getEntity().getText();
        String status = response.getStatus().toString();
        System.out.println("status :" + status);
        System.out.println("Response :" + text);
    }

    private void createNewPracticePatient(String practiceId) throws IOException  {
        System.out.println("createNewPracticePatient:");
        ClientResource resource = getClientResource(patientEndPoint);
        resource.setMethod(Method.POST);
        resource.addQueryParameter("practiceId", practiceId);
        resource.addQueryParameter("localId", "21");
        resource.addQueryParameter("firstName", "firstName1");
        resource.addQueryParameter("lastName", "lastName1");
        resource.addQueryParameter("nickname", "nickName");
        resource.addQueryParameter("street", "Street-1");
        resource.addQueryParameter("houseNumber", "22");
        resource.addQueryParameter("postboxNumber", "12015");
        resource.addQueryParameter("zip", "12015");
        resource.addQueryParameter("countryIsoCode", "BL");
        resource.addQueryParameter("country", "Belgium");
        resource.addQueryParameter("telephone", "231542623");
        resource.addQueryParameter("mobile", "9856321254");
        resource.addQueryParameter("email", "testpat@docbook.com");
        resource.addQueryParameter("ssn", "");
        resource.addQueryParameter("birthday", "1986-05-21");
        resource.addQueryParameter("language", "nl");
        resource.addQueryParameter("gender", "M");
        resource.addQueryParameter("nationality", "Belgium");
        resource.addQueryParameter("info", "Patient related info");
        resource.addQueryParameter("deceased", "false");
        resource.addQueryParameter("status", "true");
        resource.addQueryParameter("mergesIntoRpId", "");

        resource.post(new StringRepresentation(""));
        Response response = resource.getResponse();
        String text = response.getEntity().getText();
        String status = response.getStatus().toString();
        System.out.println("status :" + status);
        System.out.println("Response :" + text);
    }

    private void updatePracticePatient(String patientId, String practiceId) throws IOException  {
        System.out.println("createNewPracticePatient:");
        String requestUrl = String.format(practiceDetailEndPoint, patientId );
        ClientResource resource = getClientResource(requestUrl);
        resource.setMethod(Method.PUT);
        resource.addQueryParameter("practiceId", practiceId);
        resource.addQueryParameter("localId", "1");
        resource.addQueryParameter("firstName", "firstNameUpdated");
        resource.addQueryParameter("lastName", "lastNameUpdated");
        resource.addQueryParameter("nickname", "nickName");
        resource.addQueryParameter("street", "Street-1");
        resource.addQueryParameter("houseNumber", "22");
        resource.addQueryParameter("postboxNumber", "12015");
        resource.addQueryParameter("zip", "12015");
        resource.addQueryParameter("countryIsoCode", "BL");
        resource.addQueryParameter("country", "Belgium");
        resource.addQueryParameter("telephone", "231542623");
        resource.addQueryParameter("mobile", "9856321254");
        resource.addQueryParameter("email", "testpat@docbook.com");
        resource.addQueryParameter("ssn", "");
        resource.addQueryParameter("birthday", "1986-05-22");
        resource.addQueryParameter("language", "nl");
        resource.addQueryParameter("gender", "M");
        resource.addQueryParameter("nationality", "Belgium");
        resource.addQueryParameter("info", "Patient related info");
        resource.addQueryParameter("deceased", "false");
        resource.addQueryParameter("status", "true");
        resource.addQueryParameter("mergesIntoRpId", "");

        resource.put(new StringRepresentation(""));
        Response response = resource.getResponse();
        String text = response.getEntity().getText();
        String status = response.getStatus().toString();
        System.out.println("status :" + status);
        System.out.println("Response :" + text);
    }

}
