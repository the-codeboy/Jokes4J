package com.github.codeboy.jokes4j.api;

import java.net.MalformedURLException;
import java.net.URL;

import static com.github.codeboy.jokes4j.util.Util.encode;

public class JokeRequest {
    private Category[] categories;
    private Language language;
    private Flag[] blackList;
    /**
     * either {@link JokeType#single} or {@link JokeType#twopart}. Null will be considered as both
     */
    private JokeType type = JokeType.single;
    private String searchString;
    private int minId, maxId;
    private int amount;
    private boolean safeMode = false;

    public JokeRequest() {
    }

    public JokeRequest(Category[] categories, Language language) {
        this.categories = categories;
        this.language = language;
    }

    public JokeRequest(Category[] categories, Language language, JokeType type) {
        this.categories = categories;
        this.language = language;
        this.type = type;
    }

    public JokeRequest(Category[] categories, Language language, Flag[] blackList, JokeType type, String searchString, int minId, int maxId, int amount, boolean safeMode) {
        this.categories = categories;
        this.language = language;
        this.blackList = blackList;
        this.type = type;
        this.searchString = searchString;
        this.minId = minId;
        this.maxId = maxId;
        this.amount = amount;
        this.safeMode = safeMode;
    }

    private String getCategoryString() {
        if (categories == null || categories.length == 0)
            return "Any";
        StringBuilder category = new StringBuilder();
        for (int i = 0; i < categories.length; i++) {
            Category c = categories[i];
            if (c == null)
                continue;
            if (i != 0)
                category.append(",");
            category.append(c.name());
        }
        return category.toString();
    }

    private String getLanguageString() {
        return "lang=" + (language == null ? Language.ENGLISH : language);
    }

    private String getBlackListString() {
        if (blackList == null || blackList.length == 0)
            return "";
        StringBuilder list = new StringBuilder("&blacklistFlags=");
        for (int i = 0; i < blackList.length; i++) {
            Flag c = blackList[i];
            if (c == null)
                continue;
            if (i != 0)
                list.append(",");
            list.append(c.name());
        }
        return list.toString();
    }

    private String getTypeString() {
        return type == null ? "" : "&type=" + type.name();
    }

    private String getSearchString() {
        return searchString == null || searchString.length() == 0 ? "" : "&contains=" + encode(searchString);
    }

    private String getRangeString() {
        if (minId == 0 && maxId == 0)
            return "";
        return "&idRange=" + minId + "-" + maxId;
    }

    private String getAmountString() {
        if (amount == 0)
            return "";
        return "&amount=" + amount;
    }

    private String getSafeModeString() {
        return safeMode ? "&safe-mode" : "";
    }

    private String generateUrl() {
        String url = "https://v2.jokeapi.dev/joke/";
        url += getCategoryString() + "?";
        url += getLanguageString();
        url += getBlackListString();
        url += getTypeString();
        url += getSearchString();
        url += getRangeString();
        url += getAmountString();
        return url;
    }

    public URL getURl() throws MalformedURLException {
        return new URL(generateUrl());
    }

    /**
     * A Builder Pattern for {@link JokeRequest}
     */
    public static class Builder {
        private Category[] categories;
        private Language language;
        private Flag[] blackList;
        private JokeType type;
        private String searchString;
        private int minId, maxId;
        private int amount;
        private boolean safeMode;

        public Builder() {
            type = JokeType.single;
            safeMode = false;
        }

        /**
         * Set the categories to be used in the request.
         * @param categories The categories of the joke. Null will be considered as any.
         * @return This builder instance for chaining.
         */
        public Builder categories(Category[] categories) {
            this.categories = categories;
            return this;
        }

        /**
         * Set the language to be used in the request.
         * @param language The language of the joke. Null will be considered as English.
         * @return This builder instance for chaining.
         */
        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Set the flags to be blacklisted in the request.
         * @param blackList An Array of flags to be blacklisted. Null will be considered as no blacklist.
         * @return This builder instance for chaining.
         */
        public Builder blackList(Flag[] blackList) {
            this.blackList = blackList;
            return this;
        }

        /**
         * Set the type of the joke.
         * @param type The type of the joke. Null will be considered as both single and twopart.
         * @return This builder instance for chaining.
         */
        public Builder type(JokeType type) {
            this.type = type;
            return this;
        }

        /**
         * Set the search string to be used in the request.
         * @param searchString A string that the joke resulting from the request must contain. Null will be considered as no search string.
         * @return This builder instance for chaining.
         */
        public Builder searchString(String searchString) {
            this.searchString = searchString;
            return this;
        }

        /**
         * Set the smallest joke id.
         * @param minId The minimum id of the joke. 0 will be considered as no minimum.
         * @return This builder instance for chaining.
         */
        public Builder minId(int minId) {
            this.minId = minId;
            return this;
        }

        /**
         * Set the largest joke id.
         * @param maxId The maximum id of the joke. 0 will be considered as no maximum.
         * @return This builder instance for chaining.
         */
        public Builder maxId(int maxId) {
            this.maxId = maxId;
            return this;
        }

        /**
         * Set the amount of jokes to be used in the request.
         * @param amount The amount of jokes to be used. Everything below 1 will be considered as 1.
         * @return This builder instance for chaining.
         */
        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Set whether safe mode should be used in the request.
         * @param safeMode True if the request should use safe mode. False if not.
         * @return This builder instance for chaining.
         */
        public Builder safeMode(boolean safeMode) {
            this.safeMode = safeMode;
            return this;
        }

        /**
         * Build the {@link JokeRequest} instance.
         * @return The {@link JokeRequest} instance.
         */
        public JokeRequest build() {
            return new JokeRequest(categories, language, blackList, type, searchString, minId, maxId, amount, safeMode);
        }
    }

}
