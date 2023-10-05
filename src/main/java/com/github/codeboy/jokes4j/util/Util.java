package com.github.codeboy.jokes4j.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Util {
    /**
     * sends a get request to the specified url
     * @param url the remote url to contact
     * @return the response of the server
     * @throws java.io.IOException Exception on remote HTTP/GET
     */
    public static String get(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();
        }
    }

    /**
     * urlencodes a value
     * @param value value to encode
     * @return the encoded string
     */
    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}
