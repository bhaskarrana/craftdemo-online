package com.craftdemo.backend;

import com.craftdemo.util.Util;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.setPort;
public class UserServer {

    public static void main(String[] args) {
        setPort(Util.getWebPort());
        externalStaticFileLocation("src/main/webapp");
        final UserService service = new UserService(Util.getNeo4jUrl());
        new UserRoutes(service).init();
    }
}
