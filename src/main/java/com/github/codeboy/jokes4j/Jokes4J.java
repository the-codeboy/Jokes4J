package com.github.codeboy.jokes4j;

import com.github.codeboy.jokes4j.api.*;
import com.github.codeboy.jokes4j.util.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URL;

public class Jokes4J {
    private static Jokes4J instance;

    private Jokes4J() {
    }

    public static Jokes4J getInstance() {
        return instance == null ? (instance = new Jokes4J()) : instance;
    }

    public static String getJokeString() {
        return getInstance().getJoke().getJoke();
    }

    public Joke getJoke(Language language, JokeType type, Category... categories) {
        return getJoke(new JokeRequest(categories, language, type));
    }

    public Joke getJoke(Language language, Category... categories) {
        return getJoke(language, JokeType.single, categories);
    }

    public Joke getJoke() {
        return getJoke(Language.ENGLISH);
    }

    public Joke getJoke(JokeRequest request) {
        try {
            ApiResponse response = getJokes(request);
            if (response.isError())
                throw new JokeException(response.getErrorMessage());
            Joke joke = response.getJoke();
            if (joke == null)
                throw new JokeException("Unable to find joke");
            return joke;
        } catch (IOException e) {
            throw new JokeException(e);
        }
    }

    public ApiResponse getJokes(JokeRequest request) throws IOException {
        URL url = request.getURl();
        String rawJson = Util.get(url);
        JsonObject jsonObject = JsonParser.parseString(rawJson).getAsJsonObject();
        return new ApiResponse(jsonObject);
    }
}
