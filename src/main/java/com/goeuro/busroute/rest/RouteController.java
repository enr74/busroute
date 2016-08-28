package com.goeuro.busroute.rest;

import com.goeuro.busroute.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ComponentScan(basePackages = { "com.goeuro.busroute" })
public class RouteController {

    @Autowired
    private RouteService routeService;


    @RequestMapping(path = "/api/direct", produces = "application/json")
    @ResponseBody
    RouteResponse direct(@RequestParam(value="dep_sid") final int depSid, @RequestParam(value="arr_sid") final int arrSid  ) {
        boolean isDirect = routeService.isDirectBusRoute(depSid, arrSid);
        return getRouteResponse(depSid, arrSid, isDirect);
    }

    private RouteResponse getRouteResponse(final int depSid, final int arrSid, boolean isDirect) {
        RouteResponse response = new RouteResponse();
        response.setDirect_bus_route(isDirect);
        response.setDep_sid(depSid);
        response.setArr_sid(arrSid);
        return response;
    }


}