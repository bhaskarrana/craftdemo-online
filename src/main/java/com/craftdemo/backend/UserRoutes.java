package com.craftdemo.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.servlet.SparkApplication;

import java.net.URLDecoder;

import static spark.Spark.get;

public class UserRoutes implements SparkApplication {

    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private UserService service;

    public UserRoutes(UserService service) {
        this.service = service;
    }

    public void init() {
        get("/search", (req, res) -> gson.toJson(service.search(req.queryParams("q"))));
    }
}
