package com.goeuro.busroute.services;

import com.goeuro.busroute.data.RouteData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceTest {

    @Mock
    private RouteData routeData;

    @InjectMocks
    private RouteService routeService;
    private Collection<List<String>> routes = new ArrayList<>();

    @Before
    public void setUp() {
        routes.add(Arrays.asList("1", "2", "4"));
    }
    @Test
    public void testIsDirectBusRoute() throws Exception {
        given(routeData.getRoutes()).willReturn(routes);
        boolean isDirect = routeService.isDirectBusRoute(1, 2);
        assertTrue(isDirect);
    }

    @Test
    public void testIsNotDirectBusRoute() throws Exception {
        given(routeData.getRoutes()).willReturn(routes);
        boolean isDirect = routeService.isDirectBusRoute(1, 3);
        assertFalse(isDirect);
    }
}