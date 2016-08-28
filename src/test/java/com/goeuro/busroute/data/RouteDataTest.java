package com.goeuro.busroute.data;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RouteDataTest {

    @Test
    public void testGetRoutes() throws Exception {
        RouteData data = new RouteData(this.getClass().getClassLoader().getResource("routes.txt").getFile() );
        Collection<List<String>> routes = data.getRoutes();
        assertTrue(!routes.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotEnoughStations() throws Exception {
        RouteData data = new RouteData(this.getClass().getClassLoader().getResource("routes_not_enough_stations.txt").getFile());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotUniqueRouteId() throws Exception {
        RouteData data = new RouteData(this.getClass().getClassLoader().getResource("routes_not_unique_route_id.txt").getFile());
    }
}