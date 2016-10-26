package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Product;
import nl.cimsolutions.snel_transport.models.Route;
import nl.cimsolutions.snel_transport.models.Status;
import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;
import nl.cimsolutions.snel_transport.services.RouteFacade;
import nl.cimsolutions.snel_transport.services.StatusFacade;

public class RouteControllerTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
    RouteFacade routeFacade = new RouteFacade();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    }

    public Response addRouteRequest() {        
        Route route = new Route();
        String url = "http://localhost:8080/snel-transport/api/routes";
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        
        //Preparing data to send with the POST request
        CustomerFacade customerFacade = new CustomerFacade();
        
        Customer customerA = customerFacade.find(1L);
        Customer customerB = customerFacade.find(7L);
        route.setCustomerA(customerA);
        route.setCustomerB(customerB);
        
        route.setDuration("25:10:40");
        route.setDistance(50);
        
        //Making a POST request to receive a response from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(route, MediaType.APPLICATION_JSON));
        
        return response;
    }
    
    @Test
    public void testAddRoute() {
        RouteFacade routerFacade = new RouteFacade();
        Response response = addRouteRequest();
        //We expect to receive a 201 as statuscode
        assertEquals(201, response.getStatus());
        
        String output = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(output));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        Route foundRoute = new Route();
        long routeId = object.getInt("id");
        
        //We expect that the found route has a customer id of 1
        foundRoute = routerFacade.find(routeId);
        assertEquals("1", foundRoute.getCustomerA().getId().toString());
       
        //We remove the test data from the database, because we don't want to have TEST data in the development database
        routerFacade.remove(foundRoute);
    }
    
    @Test
    public void testAddRouteWithoutCustomerA() {
        Route route = new Route();
        String url = "http://localhost:8080/snel-transport/api/routes";
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        
        //Preparing data to send with the POST request
        CustomerFacade customerFacade = new CustomerFacade();
        RouteFacade routerFacade = new RouteFacade();
        
        Customer customerA = customerFacade.find(1L);
        Customer customerB = customerFacade.find(7L);
        route.setCustomerB(customerB);
        
        route.setDuration("25:10:40");
        route.setDistance(50);
        
        //Making a POST request to receive a response from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(route, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 201 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("customerA and/or CustomerB object is required", output);
    }
    
    @Test
    public void testAddRouteWithoutCustomerAId() {
        Route route = new Route();
        String url = "http://localhost:8080/snel-transport/api/routes";
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        
        //Preparing data to send with the POST request
        CustomerFacade customerFacade = new CustomerFacade();
        
        Customer customerA = new Customer();
        customerA.setId(null);
        Customer customerB = customerFacade.find(7L);
        route.setCustomerA(customerA);
        route.setCustomerB(customerB);
        
        route.setDuration("25:10:40");
        route.setDistance(50);
        
        //Making a POST request to receive a response from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(route, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 201 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("customerA ID is required", output);
    }
    
    @Test
    public void testAddRouteWithInvalidCustomerA(){
        Route route = new Route();
        String url = "http://localhost:8080/snel-transport/api/routes";
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        
        //Preparing data to send with the POST request
        CustomerFacade customerFacade = new CustomerFacade();
        
        Customer customerA = new Customer();
        customerA.setId(80L);
        Customer customerB = customerFacade.find(7L);
        route.setCustomerA(customerA);
        route.setCustomerB(customerB);
        
        route.setDuration("25:10:40");
        route.setDistance(50);
        
        //Making a POST request to receive a response from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(route, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 201 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("customerA ID wasn't found", output);
    }
}
