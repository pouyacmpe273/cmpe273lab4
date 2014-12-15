package edu.sjsu.cmpe.cache.client;



import com.google.common.hash.Hashing;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
//        CacheServiceInterface cache = new DistributedCacheService(
//                "http://localhost:3000");

//        cache.put(1, "foo");
//        System.out.println("put(1 => foo)");

//        String value = cache.get(1);
//        System.out.println("get(1) => " + value);

        //Listing all the servers
        List<CacheServiceInterface> servers = new ArrayList<CacheServiceInterface>();
        servers.add(new DistributedCacheService("http://localhost:3000"));
        servers.add(new DistributedCacheService("http://localhost:3001"));
        servers.add(new DistributedCacheService("http://localhost:3002"));

        char[] data = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};

        System.out.println("-------data injection----------");

        for (int i = 1; i < data.length ; i++) {
            int bucket = Hashing.consistentHash(Hashing.md5().hashString("someId"), servers.size());
            CacheServiceInterface targetedServer = servers.get(bucket);
            targetedServer.put(i, Character.toString(data[i]));
            System.out.println("server at localhost:300" + bucket + " received value " + data[i] + " for key " + i + "." );
        }

        System.out.println("-------data retrieval----------");

        for (int i = 1; i < data.length ; i++) {
            int bucket = Hashing.consistentHash(Hashing.md5().hashString("someId"), servers.size());
            CacheServiceInterface targetedServer = servers.get(bucket);
            String serverValue = targetedServer.get(i);
            System.out.println("server at localhost:300" + bucket + " contains value " + serverValue + " for key " + i );
        }


        System.out.println("Existing Cache Client...");
    }

}
