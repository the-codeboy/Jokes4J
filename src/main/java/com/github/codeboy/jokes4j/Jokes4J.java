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

    /**
     * @return the singleton instance of Jokes4J
     */
    public static Jokes4J getInstance() {
        return instance == null ? (instance = new Jokes4J()) : instance;
    }

    /**
     * @return a single joke from the api
     */
    public static String getJokeString() {
        return getInstance().getJoke().getJoke();
    }

    /**
     * @param language the language the joke is in
     * @param type the type of joke - if null the type will be random
     * @param categories the categories the joke can be from
     * @return the joke
     */
    public Joke getJoke(Language language, JokeType type, Category... categories) {
        return getJoke(new JokeRequest(categories, language, type));
    }

    /**
     * @param language the language the joke is in
     * @param categories the categories the joke can be from
     * @return the joke
     */
    public Joke getJoke(Language language, Category... categories) {
        return getJoke(language, JokeType.single, categories);
    }

    /**
     * @return a single joke in english
     */
    public Joke getJoke() {
        return getJoke(Language.ENGLISH);
    }

    /**
     * @return a joke that fits the specified request
     */
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

    /**
     * @return an api response object for the specified joke request
     */
    public ApiResponse getJokes(JokeRequest request) throws IOException {
        URL url = request.getURl();
        String rawJson = Util.get(url);
        JsonObject jsonObject = JsonParser.parseString(rawJson).getAsJsonObject();
        return new ApiResponse(jsonObject);
    }
}
