package com.goeuro.busroute.services;

import com.goeuro.busroute.data.RouteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Verify if a bus route provides a direct connection between two given stations.
 */
@Service
public class RouteService {

    @Autowired
    private RouteData routeData;

    /**
     * Check if two stations are connected by a direct route.
     * @param depSid
     * @param arrSid
     * @return
     */
    public boolean isDirectBusRoute(final int depSid, final int arrSid) {
        Collection<List<String>> routes = routeData.getRoutes();
        for (List<String> route : routes) {
            boolean isDirect = containsStations(route, depSid, arrSid);
            if (isDirect) {
                return true;
            }
        }
        return false;
    }

    private boolean containsStations(final List<String> sIds, final int depSid, final int arrSid) {
        return sIds.contains(String.valueOf(depSid)) && sIds.contains(String.valueOf(arrSid));
    }
}
