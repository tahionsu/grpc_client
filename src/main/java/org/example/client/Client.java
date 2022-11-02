package org.example.client;

import com.example.grpc.CoffeeServiceGrpc;
import com.example.grpc.CoffeeServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.json.CustomJSON;


public class Client {

    public static CoffeeServiceOuterClass.CoffeeGetResponse getRequest(int id, ManagedChannel channel) {

        CoffeeServiceGrpc.CoffeeServiceBlockingStub stub =
                CoffeeServiceGrpc.newBlockingStub(channel);
        CoffeeServiceOuterClass.CoffeeGetRequest requestGet =
                CoffeeServiceOuterClass.CoffeeGetRequest.newBuilder().setId(id).build();
        return stub.getCoffee(requestGet);

    }

    public static CoffeeServiceOuterClass.CoffeePostResponse addRequest(String name,
                                                                        CoffeeServiceOuterClass.CustomJSON description,
                                                                        ManagedChannel channel) {

        CoffeeServiceGrpc.CoffeeServiceBlockingStub stub =
                CoffeeServiceGrpc.newBlockingStub(channel);
        CoffeeServiceOuterClass.CoffeePostRequest requestPost =
                CoffeeServiceOuterClass.CoffeePostRequest.newBuilder()
                        .setName(name)
                        .setDescription(description)
                        .build();

        return stub.addCoffee(requestPost);
    }

    public static CoffeeServiceOuterClass.CoffeeDelResponse delRequest(Integer id, ManagedChannel channel) {

        CoffeeServiceGrpc.CoffeeServiceBlockingStub stub =
                CoffeeServiceGrpc.newBlockingStub(channel);
        CoffeeServiceOuterClass.CoffeeDelRequest requestDel =
                CoffeeServiceOuterClass.CoffeeDelRequest.newBuilder()
                        .setId(id)
                        .build();

        return stub.delCoffee(requestDel);
    }
}