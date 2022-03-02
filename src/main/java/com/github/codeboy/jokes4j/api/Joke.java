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

    public Category getCategory() {
        return category;
    }

    public JokeType getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getJoke() {
        return joke;
    }

    public Map<Flag, Boolean> getFlags() {
        return flags;
    }

    public int getId() {
        return id;
    }

    public boolean isSafe() {
        return safe;
    }

    public Language getLanguage() {
        return language;
    }
}
