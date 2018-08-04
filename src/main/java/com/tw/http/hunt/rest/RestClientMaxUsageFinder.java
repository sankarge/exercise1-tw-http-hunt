package com.tw.http.hunt.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.tw.http.hunt.MaxUsedToolFinder;
import com.tw.http.hunt.ToolsUsageVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class RestClientMaxUsageFinder {

    private static final String INPUT_URL = "https://http-hunt.thoughtworks-labs.net/challenge/input";

    private static final String OUTPUT_URL = "https://http-hunt.thoughtworks-labs.net/challenge/output";

    public static void main(String[] args) {
        try {
            URLConnectionClientHandler ch = new URLConnectionClientHandler(new ConnectionFactory());
            Client client = Client.create();
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
            JSONObject jsonObject = (JSONObject) parser.parse(input);
            JSONArray jsonArray = (JSONArray) jsonObject.get("toolUsage");


            List<ToolsUsageVO> voList = new ArrayList<>();
            for (Object object : jsonArray) {
                JSONObject object1 = (JSONObject) object;
                String name = (String) object1.get("name");
                String from = (String) object1.get("useStartTime");
                String to = (String) object1.get("useEndTime");
                voList.add(new ToolsUsageVO(name, from, to));
            }
            System.out.println("Get: Input from Server .... \n");
            System.out.println(voList);

            MaxUsedToolFinder maxUsedToolFinder = new MaxUsedToolFinder(voList);
            JSONArray byUsage = maxUsedToolFinder.sortByUsage();

            JSONObject jsonOutput = new JSONObject();
            jsonOutput.put("toolsSortedOnUsage", byUsage);

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
            System.out.println(postResult);
            System.out.println(jsonOutput.toJSONString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
