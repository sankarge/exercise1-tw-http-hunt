package com.tw.http.hunt.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.tw.http.hunt.BestOfToolsFinder;
import com.tw.http.hunt.MaxUsedToolFinder;
import com.tw.http.hunt.ToolsInfoVO;
import com.tw.http.hunt.ToolsUsageVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class RestClientBestOfToolsFinder {

    private static final String INPUT_URL = "https://http-hunt.thoughtworks-labs.net/challenge/input";

    private static final String OUTPUT_URL = "https://http-hunt.thoughtworks-labs.net/challenge/output";

    public static void main(String[] args) {
        try {
            URLConnectionClientHandler ch = new URLConnectionClientHandler(new ConnectionFactory());
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
            JSONObject jsonObject = (JSONObject) parser.parse(input);
            JSONArray jsonArray = (JSONArray) jsonObject.get("tools");


            List<ToolsInfoVO> voList = new ArrayList<>();
            for (Object object : jsonArray) {
                JSONObject object1 = (JSONObject) object;
                String name = (String) object1.get("name");
                Long weight = (Long) object1.get("weight");
                Long value = (Long) object1.get("value");
                voList.add(new ToolsInfoVO(name, weight, value));
            }

            Long maxWeight = (Long) jsonObject.get("maximumWeight");

            System.out.println("Get: Input from Server .... \n");
            System.out.println(voList);

            BestOfToolsFinder  bestOfToolsFinder = new BestOfToolsFinder(voList, maxWeight);
            JSONArray array = bestOfToolsFinder.find();

            JSONObject jsonOutput = new JSONObject();
            jsonOutput.put("toolsToTakeSorted", array);

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
