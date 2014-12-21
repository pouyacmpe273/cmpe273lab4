package edu.sjsu.cmpe.cache.client;

import com.mashape.unirest.http.Unirest;

import java.lang.*;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
        CRDTClient crdtClient = new CRDTClient();

        // PUT -> sleep for 30 secs
        boolean crdtResult = crdtClient.put(1, "a");
        System.out.println("result is " + crdtResult);
        Thread.sleep(30*1000);
        System.out.println("Step 1: put(1 => a); sleeping 30 secs");


        // PUT -> sleep for 30 secs
        crdtClient.put(1, "b");
        Thread.sleep(30*1000);
        System.out.println("Step 2: put(1 => b); sleeping 30s");


        // GET call
        String value = crdtClient.get(1);
        System.out.println("Step 3: get(1) => " + value);

        System.out.println("Exiting Client...");
        Unirest.shutdown();
    }

}
