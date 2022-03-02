package com.github.codeboy.jokes4j.api;

public enum Language {
    ENGLISH("en"), GERMAN("de"), CZECH("cs"), SPANISH("es"), FRENCH("fr"), PORTUGUESE("pt");
    private final String code;


    Language(String code) {
        this.code = code;
    }

    public static Language getForCode(String code) {
        for (Language language : values()) {
            if (language.code.equals(code))
                return language;
        }
        return null;
    }

    @Override
    public String toString() {
        return code;
    }
}
