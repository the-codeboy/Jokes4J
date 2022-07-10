package com.github.codeboy.jokes4j;

import com.github.codeboy.jokes4j.api.ApiResponse;
import com.github.codeboy.jokes4j.api.JokeRequest;
import com.github.codeboy.jokes4j.api.JokeType;
import com.github.codeboy.jokes4j.api.Language;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Jokes4JTest {


    @Test
    void getInstance() {
        assertNotNull(Jokes4J.getInstance(),"Jokes4J singleton is null");
    }
    @Test
    void getJoke() {
        assertNotNull(Jokes4J.getJokeString(),"Jokes4J.getJokeString() returned null");
    }

    @Test
    void testGetJoke() {
        assertNotNull(Jokes4J.getInstance().getJoke());
    }

    @Test
    void testGetJoke1() {
        assertNotNull(new JokeRequest.Builder().type(JokeType.twopart).build());
    }

    @Test
    void getJokes() throws IOException {
        ApiResponse response=Jokes4J.getInstance().getJokes(new JokeRequest.Builder().type(JokeType.single).amount(2).build());
        assertNotNull(response);
        assertEquals(response.getAmount(),2);
    }
}