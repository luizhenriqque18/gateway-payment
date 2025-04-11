package io.github.luizhenriqque18.gateway_api.utils;

import java.util.UUID;

public class GenerateUtil {
    

    public static UUID generateUUID() {
        return UUID.randomUUID();
    }

    public static String generateApiKey() {
        // Generate a random UUID and convert it to a string positio 16
        return UUID.randomUUID().toString();
    }
}
