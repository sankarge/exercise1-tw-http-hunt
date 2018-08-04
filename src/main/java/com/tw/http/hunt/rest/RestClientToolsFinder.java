package com.tw.http.hunt.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.tw.http.hunt.ToolsFinder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class RestClientToolsFinder {

    private static final String INPUT_URL = "https://http-hunt.thoughtworks-labs.net/challenge/input";

    private static final String OUTPUT_URL = "https://http-hunt.thoughtworks-labs.net/challenge/output";

    public static void main(String[] args) {
        try {

            URLConnectionClientHandler ch  = new URLConnectionClientHandler(new ConnectionFactory());
            Client client = new Client(ch);
            WebResource webResource = client.resource(INPUT_URL);
            ClientResponse response = webResource
                    .accept("application/json")
                    .header("userid", "BJFM1-8NQ")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            String input = response.getEntity(String.class);
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(input);

            String hiddenTools = (String) obj.get("hiddenTools");
            JSONArray jsonArray = (JSONArray) obj.get("tools");
            final List<String> inputTools = new ArrayList<>();
            jsonArray.forEach(e -> inputTools.add((String) e));

            System.out.println("Get: Input from Server .... \n");
            System.out.println(hiddenTools);
            System.out.println(inputTools);

            ToolsFinder finder = new ToolsFinder(hiddenTools, inputTools);
            ArrayList<String> output = finder.find();

            JSONObject jsonOutput = new JSONObject();
            JSONArray array = new JSONArray();
            array.addAll(output);
            jsonOutput.put("toolsFound", array);

            System.out.println("Decrypted message");
            System.out.println(jsonOutput.toJSONString());

            webResource = client.resource(OUTPUT_URL);
            ClientResponse post = webResource
                    .type("application/json")
                    .header("userid", "BJFM1-8NQ")
                    .post(ClientResponse.class, jsonOutput.toJSONString());

            if (post.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Post: Output to Server .... \n");
            String postResult = post.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
