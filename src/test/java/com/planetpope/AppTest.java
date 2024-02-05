package com.planetpope;

import org.junit.jupiter.api.Test;
import rife.test.MockConversation;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    @Test
    void verifyRoot() {
        var m = new MockConversation(new AppSite());
        assertEquals(m.doRequest("/").getStatus(), 302);
    }

    @Test
    void verifyHello() {
        var m = new MockConversation(new AppSite());
        assertEquals("Hello App", m.doRequest("/hello")
            .getTemplate().getValue("title"));
    }
}
