package controller;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import nl.cimsolutions.snel_transport.controllers.OrderController;

public class OrderControllerTest {

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

    @Test
    public void testGetOrderLines() {
//        OrderController instance = new OrderController();
//        
//        Response result = instance.getOrderLines(41);
//        System.out.println("result "+ result);
//        
//        JsonReader jsonReader = Json.createReader(new StringReader(result.getEntity().toString()));
////        JsonObject object = jsonReader.readObject();
//        JsonArray array = jsonReader.readArray();
//        
//        jsonReader.close();
//        
//        System.out.println("array " + array);
        String url = "http://localhost:8080/snel-transport/api/orders/41/orderlines";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        
        System.out.println(target);
    }

}
