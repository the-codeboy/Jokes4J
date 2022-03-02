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

    public boolean isError() {
        return error;
    }

    public int getAmount() {
        return amount;
    }

    public Joke[] getJokes() {
        return jokes;
    }

    public Joke getJoke() {
        return jokes.length == 0 ? null : jokes[0];
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
