package org.example;

import com.example.grpc.CoffeeServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.example.client.Client;
import org.example.json.CustomJSON;


public class Main {
    public static void main(String[] args) {
        try {

            ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081")
                    .usePlaintext()
                    .build();

            CoffeeServiceOuterClass.CoffeeGetResponse responseGet = Client.getRequest(5, channel);

            System.out.println("id: " + responseGet.getId());
            System.out.println("name: " + responseGet.getName());
            System.out.println("description: \n" + responseGet.getDescription());

            CoffeeServiceOuterClass.CoffeePostResponse responsePost =
                    Client.addRequest("Lavazza", responseGet.getDescription(), channel);

            System.out.println(responsePost.getId());

            CoffeeServiceOuterClass.CoffeeDelResponse retCode = Client.delRequest(10, channel);

            System.out.println(retCode.getRetCode());

            channel.shutdown();

        } catch (StatusRuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
