package com.github.codeboy.jokes4j.api;

import java.util.Map;

public class Joke {
    protected Language language;
    private Category category;
    private JokeType type;
    private String setup, delivery;
    private String joke;
    private Map<Flag, Boolean> flags;
    private int id;
    private boolean safe;

    @Override
    public String toString() {
        return "Joke{" +
                "category=" + category +
                ", type=" + type +
                ", setup='" + setup + '\'' +
                ", delivery='" + delivery + '\'' +
                ", joke='" + joke + '\'' +
                ", flags=" + flags +
                ", id=" + id +
                ", safe=" + safe +
                ", language=" + language +
                '}';
    }

    /**
     * @return the category of this joke
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @return the type of joke - either single or twopart
     */
    public JokeType getType() {
        return type;
    }

    /**
     * @return the setup or null if {@link Joke#getType()} returns {@link JokeType#single}
     */
    public String getSetup() {
        return setup;
    }

    /**
     * @return the delivery or null if {@link Joke#getType()} returns {@link JokeType#single}
     */
    public String getDelivery() {
        return delivery;
    }

    /**
     * @return the joke or null if {@link Joke#getType()} returns {@link JokeType#twopart}
     */
    public String getJoke() {
        return joke;
    }

    /**
     * @return a map of the flags
     */
    public Map<Flag, Boolean> getFlags() {
        return flags;
    }

    /**
     * @return the id of the joke
     */
    public int getId() {
        return id;
    }

    /**
     * @return if this joke is safe
     */
    public boolean isSafe() {
        return safe;
    }

    /**
     * @return the language this joke is in
     */
    public Language getLanguage() {
        return language;
    }
}
