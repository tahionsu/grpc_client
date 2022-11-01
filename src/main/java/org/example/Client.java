package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.example.grpc.CoffeeServiceGrpc;
import com.example.grpc.CoffeeServiceOuterClass;


public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081")
                .usePlaintext()
                .build();

        CoffeeServiceGrpc.CoffeeServiceBlockingStub stub =
                CoffeeServiceGrpc.newBlockingStub(channel);

        CoffeeServiceOuterClass.CoffeeGetRequest requestGet =
                CoffeeServiceOuterClass.CoffeeGetRequest.newBuilder().setId(10).build();

        CoffeeServiceOuterClass.CoffeeGetResponse responseGet = stub.getCoffee(requestGet);

        System.out.println(responseGet.getId());
        System.out.println(responseGet.getName());
        System.out.println(responseGet.getDescription());

        CoffeeServiceOuterClass.CoffeePostRequest requestPost =
                CoffeeServiceOuterClass.CoffeePostRequest.newBuilder()
                        .setName("Test-coffee-pro-limited-blend")
                        .setDescription(responseGet.getDescription())
                        .build();

        CoffeeServiceOuterClass.CoffeePostResponse responsePost = stub.addCoffee(requestPost);

        System.out.println(responsePost.getId());

        CoffeeServiceOuterClass.CoffeeDelRequest requestDel =
                CoffeeServiceOuterClass.CoffeeDelRequest.newBuilder()
                        .setId(4)
                        .build();

        CoffeeServiceOuterClass.CoffeeDelResponse responseDel = stub.delCoffee(requestDel);

        System.out.println(responseDel.getRetCode());



        channel.shutdown();
    }
}
