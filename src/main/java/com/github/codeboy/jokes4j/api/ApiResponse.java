package com.github.codeboy.jokes4j.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ApiResponse {

    private static final Gson gson = new Gson();

    private final boolean error;
    private final Joke[] jokes;
    private int amount = 1;
    private String errorMessage;

    /**
     * creates an ApiResponse object from the specified JsonObject
     */
    public ApiResponse(JsonObject object) {
        error = object.get("error").getAsBoolean();
        if (error)
            errorMessage = object.get("message").getAsString();
        if (object.has("jokes")) {
            amount = object.get("amount").getAsInt();
            JsonArray array = object.get("jokes").getAsJsonArray();
            jokes = new Joke[array.size()];
            for (int i = 0; i < array.size(); i++) {
                jokes[i] = getJoke(array.get(i).getAsJsonObject());
            }
        } else
            jokes = new Joke[]{getJoke(object)};
    }

    private Joke getJoke(JsonObject object) {
        Joke joke = gson.fromJson(object, Joke.class);
        joke.language = Language.getForCode(object.get("lang").getAsString());
        return joke;
    }

    /**
     * @return if the api returned an error
     */
    public boolean isError() {
        return error;
    }

    /**
     * @return the amount of jokes returned by the api
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return the jokes returned by the api
     */
    public Joke[] getJokes() {
        return jokes;
    }

    /**
     * @return the first returned joke or null
     */
    public Joke getJoke() {
        return jokes.length == 0 ? null : jokes[0];
    }

    /**
     * @return the error-message returned by the api
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
