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
}
