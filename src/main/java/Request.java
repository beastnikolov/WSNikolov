import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.ClientResponse;
import org.json.JSONObject;

import javax.swing.*;
import javax.ws.rs.core.MediaType;

public class Request {
    private Client client;
    private WebResource webResource;
    private ClientResponse clientResponse;
    private String input;
    private String output;
    private JLabel httpcode;


    public Request() {

    }


    // https://www.gladtolink.com:8080/api/G2LIntegration/8646d274-13e6-43ed-8590-81b0b0b49a62
    public String requestPOST(String userInput, String url) {
        client = Client.create();
        webResource = client.resource(url);
        clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, userInput);
        output = clientResponse.getEntity(String.class);
        httpcode.setText("HTTP Response Code: " + clientResponse.getStatus());
        JSONObject jsonObject = new JSONObject(output);
        //output = jsonObject.getString("triggerResponse");
        jsonObject = jsonObject.getJSONObject("documentB64");
        output = jsonObject.getString("documentB64");
        output.replace("documentB64","");
        output.replaceAll("[^\\\\d\\\\. ]| \\\\.|\\\\.$", "");
        return output;
    }

    public void setHttpcode(JLabel httpcode) {
        this.httpcode = httpcode;
    }
}
