package com.goeuro.busroute.data;

import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RouteData {

    private static final String DEFAULT_ROUTES_DATA = "/routes.txt";

    private static final int STATIONS_MIN_SIZE = 3;


    private Map<String, List<String>> routes;

    public RouteData(final String routeFile) throws IOException {
        initContent(routeFile);
    }

    private void initContent(final String routeFile) throws IOException {
        List<String> content = getContent(routeFile);
        content2Routes(content);
    }

    public Collection<List<String>> getRoutes() {
        return routes.values();
    }

    private List<String> getContent(final String routesFile) throws IOException {
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(routesFile))) {
            for(String line; (line = br.readLine()) != null; ) {
                lines.add(line);
            }
        }
        return lines;
    }

    private void content2Routes(final List<String> lines) {
        routes = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] ids = lines.get(i).split(" ");
            String[] sIds =  Arrays.copyOfRange(ids, 1, ids.length);
            Assert.isNull(routes.get(ids[0]), "route ID must be unique!");
            Assert.isTrue(sIds.length >= STATIONS_MIN_SIZE, "Station list must contain at least 3 ID!");
            routes.put(ids[0], Arrays.asList(sIds));
        }
    }

}
