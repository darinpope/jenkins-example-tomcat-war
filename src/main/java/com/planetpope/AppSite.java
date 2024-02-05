package com.planetpope;

import rife.engine.*;

public class AppSite extends Site {
    public void setup() {
        var hello = get("/hello", c -> c.print(c.template("hello")));
        get("/", c -> c.redirect(hello));
    }

    public static void main(String[] args) {
        new Server()
            .staticResourceBase("src/main/webapp")
            .start(new AppSite());
    }
}