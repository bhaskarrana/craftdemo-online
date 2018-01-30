package com.craftdemo.backend;

import static spark.Spark.*;
import com.craftdemo.util.Util;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.setPort;
public class UserServer {

    public static void main(String[] args) {
        port(Util.getWebPort());
        final UserService service = new UserService(Util.getNeo4jUrl());
        new UserRoutes(service).init();
    }
}
