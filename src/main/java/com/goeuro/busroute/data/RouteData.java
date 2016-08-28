package com.goeuro.busroute.data;

import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Load the routes from a given data file at start-up time and retrieve the routes list from memory.
 */
public class RouteData {

    /**
     * Minimum stations number per route.
     */
    private static final int STATIONS_MIN_SIZE = 3;
    private static final int ROUTES_UPPER_LIMIT = 100000;
    private static final int STATIONS_MAX_SIZE = 1000;
    private static final int STATIONS_OVERALL_MAX_SIZE = 1000000;


    private Map<String, List<String>> routes;

    public RouteData(final String routeFile) throws IOException {
        initContent(routeFile);
    }

    /**
     * Returns a list of routes loaded from the data file at start-up time.
     * @return
     */
    public Collection<List<String>> getRoutes() {
        return routes.values();
    }

    private void initContent(final String routeFile) throws IOException {
        List<String> content = getContent(routeFile);
        content2Routes(content);
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
        Assert.isTrue(lines.size() <= ROUTES_UPPER_LIMIT, "Too many routes.");
        routes = new HashMap<>();
        int overallStations = 0;
        for (int i = 1; i < lines.size(); i++) {
            String[] ids = lines.get(i).split(" ");
            String[] sIds =  Arrays.copyOfRange(ids, 1, ids.length);
            Assert.isNull(routes.get(ids[0]), "route ID must be unique.");
            Assert.isTrue(sIds.length >= STATIONS_MIN_SIZE, "Too few stations.");
            Assert.isTrue(sIds.length <= STATIONS_MAX_SIZE, "Too many stations.");
            routes.put(ids[0], Arrays.asList(sIds));
            overallStations+=sIds.length;
        }
        Assert.isTrue(overallStations <= STATIONS_OVERALL_MAX_SIZE, "Too many routes.");

    }

}
