package com.tw.http.hunt.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.tw.http.hunt.Decryptor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RestClientDecryptor {

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

            String encryptedMessage = (String) obj.get("encryptedMessage");
            Long key = (Long) obj.get("key");

            System.out.println("Get: Output from Server .... \n");
            System.out.println(encryptedMessage);
            System.out.println(key);

            Decryptor decryptor = new Decryptor(encryptedMessage, key);
            String output = decryptor.decrypt();

            JSONObject jsonOutput = new JSONObject();
            jsonOutput.put("message", output);

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

            System.out.println("Post: Output from Server .... \n");
            output = post.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
