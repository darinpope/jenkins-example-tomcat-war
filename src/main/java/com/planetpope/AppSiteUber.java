package com.planetpope;

import rife.engine.Server;

public class AppSiteUber extends AppSite {
    public static void main(String[] args) {
        new Server()
            .staticUberJarResourceBase("webapp")
            .start(new AppSiteUber());
    }
}