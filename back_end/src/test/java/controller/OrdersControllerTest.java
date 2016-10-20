package controller;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.services.OrdersFacade;

public class OrdersControllerTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        Query q = em.createNativeQuery("DELETE FROM public.\"orderline\" ");
//        q.executeUpdate();
//        q = em.createNativeQuery("DELETE FROM public.\"Order\" ");
//        q.executeUpdate();
//        em.flush();
//        tx.commit();
//        em.close();
//        emf.close();

    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testAddOrder() {
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        
        OrderLine orderLine = new OrderLine();
        orderLine.setAmount(7);
        Long productId = (long) 3;
        Long customerId = (long) 1;
        orderLine.setProductId(productId);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
        postOrder.setStatus(1);
        postOrder.setCustomerId(customerId);
        postOrder.setOrderLines(orderLines);
        
        OrdersFacade orderFacade = new OrdersFacade();
        //Making a POST request to receive a response from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 201 as statuscode
        assertEquals(201, response.getStatus());
        
        String output = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(output));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        Orders foundOrder = new Orders();
        long orderId = object.getInt("id");
        
        //We expect that the first row will have ID 1
        foundOrder = orderFacade.find(orderId);
        assertEquals("1", foundOrder.getCustomerId().toString());
        
        orderFacade.remove(foundOrder);
    }
    
    @Test
    public void testAddOrderWithoutCustomer() {
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        
        OrderLine orderLine = new OrderLine();
        orderLine.setAmount(7);
        Long productId = (long) 3;
        orderLine.setProductId(productId);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
        postOrder.setStatus(1);
        postOrder.setOrderLines(orderLines);
        
        OrdersFacade orderFacade = new OrdersFacade();
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("customer ID is required", output);
    }
    
    @Test
    public void testAddOrderWithInvalidCustomer() {
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        
        OrderLine orderLine = new OrderLine();
        orderLine.setAmount(7);
        Long productId = (long) 3;
        //We set a customerId of 99 in the hopes that it doesn't exists in the DEV database..
        Long customerId = (long) 99;
        orderLine.setProductId(productId);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
        postOrder.setStatus(1);
        postOrder.setCustomerId(customerId);
        postOrder.setOrderLines(orderLines);
        
        
        OrdersFacade orderFacade = new OrdersFacade();
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("customer ID wasn't found", output);
    }
    
    //TO DO the test below..
    //@Test
    public void testGetOrderLines() {
     ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        
        OrderLine orderLine = new OrderLine();
        orderLine.setAmount(7);
        Long productId = (long) 3;
        Long customerId = (long) 1;
        orderLine.setProductId(productId);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
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
                .header("environment", "test")
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        System.out.println("status is "+response.getStatus());
        Orders result = response.readEntity(Orders.class);
        System.out.println(result.getCustomerId());
        
        
//        JsonReader jsonReader = Json.createReader(new StringReader(response.getEntity().toString()));

//        System.out.println("yeye "+response.getEntity());
        
//        JsonReader jsonReader = Json.createReader(new StringReader(response.getEntity().toString()));

//        System.out.println("yeye "+response.getEntity());
     
    }

}
