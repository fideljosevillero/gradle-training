package com.fideljose.themepark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RideStatusService {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("A single ride name must be passed, put: \n teacups \n logflume \n or rollercoaster");
            System.exit(1);
        }

        String rideName = args[0];
        String rideStatus = getRideStatus(rideName);

        System.out.printf("Current status of %s is '%s'%n", rideName, rideStatus);
        System.out.printf("version -> " + System.getProperty("java.version"));
    }

    public static String getRideStatus(String ride) {
        List<String> rideStatuses = readFile(String.format("%s.txt", ride));
        return rideStatuses.get(new Random().nextInt(rideStatuses.size()));
    }

    private static List<String> readFile(String filename) {
        InputStream resourceStream = RideStatusService.class.getClassLoader().getResourceAsStream(filename);
        if (resourceStream == null) {
            throw new IllegalArgumentException("Ride not found");
        }

        List<String> result = new ArrayList<>();
        try (BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8))) {
            while (bufferedInputStream.ready()) {
                result.add(bufferedInputStream.readLine());
            }
        } catch (IOException exception) {
            throw new RuntimeException("Couldn't read file", exception);
        }

        return result;
    }
}
