package com.goeuro.busroute.rest;

public class RestHelper {
    /**
     * Build a Route Response.
     * @param depSid
     * @param arrSid
     * @param isDirect
     * @return
     */
    public static RouteResponse getRouteResponse(final int depSid, final int arrSid, boolean isDirect) {
        RouteResponse response = new RouteResponse();
        response.setDirect_bus_route(isDirect);
        response.setDep_sid(depSid);
        response.setArr_sid(arrSid);
        return response;
    }
}
