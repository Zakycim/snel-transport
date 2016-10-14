package controller;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import nl.cimsolutions.snel_transport.controllers.OrderController;
import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.OrderLine;

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
    
//    @Test
    public void testAddOrder() {
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        
        OrderLine orderLine = new OrderLine();
        orderLine.setAmount(7);
        Long productId = (long) 3;
        Long customerId = (long) 1;
        orderLine.setProductId(productId);
        
        orderLines.add(orderLine);
        
        Order postOrder = new Order();
        postOrder.setStatus(1);
        postOrder.setCustomerId(customerId);
        postOrder.setOrderLines(orderLines);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateInString = "2013/10/15 16:16:39";
        Date date;
        try {
            date = sdf.parse(dateInString);
            postOrder.setDeliveryDate(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        


        
        System.out.println("tar "+target);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        System.out.println("res "+response);
//        System.out.println("entit "+response.readEntity(String.class));
        
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
        
     ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        
        OrderLine orderLine = new OrderLine();
        orderLine.setAmount(7);
        Long productId = (long) 3;
        Long customerId = (long) 1;
        orderLine.setProductId(productId);
        
        orderLines.add(orderLine);
        
        Order postOrder = new Order();
        postOrder.setStatus(1);
        postOrder.setCustomerId(customerId);
//        postOrder.setOrderLines(orderLines);
        
     //   String inputJson= "{'deliveryDate': '2016-10-25','customerId': 1,'status': 1,'orderLines':"+
       //                  " [{'productId':4, 'amount' : 7}]}";
        System.out.println("Before post order....");
       System.out.println(Entity.json(postOrder));
       
       
        String url = "http://localhost:8080/snel-transport/api/customers";
        String post_url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        WebTarget myResource = client.target(post_url);
        Response response = myResource.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        JsonReader jsonReader = Json.createReader(new StringReader(response.getEntity().toString()));

        System.out.println("yeye "+response.getEntity());
     
    }

}
