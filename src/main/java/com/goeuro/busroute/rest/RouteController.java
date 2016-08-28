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

    /**
     * Endpoint to check if two stations are connected by a direct route.
     * @param depSid departure station Id.
     * @param arrSid arrival station Id.
     * @return
     */
    @RequestMapping(path = "/api/direct", produces = "application/json")
    @ResponseBody
    RouteResponse direct(@RequestParam(value="dep_sid") final int depSid, @RequestParam(value="arr_sid") final int arrSid  ) {
        boolean isDirect = routeService.isDirectBusRoute(depSid, arrSid);
        return RestHelper.getRouteResponse(depSid, arrSid, isDirect);
    }

}