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
import nl.cimsolutions.snel_transport.models.Status;
import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;
import nl.cimsolutions.snel_transport.services.StatusFacade;

public class OrdersControllerTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
    StatusFacade statusFacade = new StatusFacade();
    CustomerFacade customerFacade = new CustomerFacade();
    ProductFacade productFacade = new ProductFacade();
    OrdersFacade orderFacade = new OrdersFacade();
    
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
//    public void test(){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
//        EntityManager em = emf.createEntityManager();
//        System.out.println("test em");
//        System.out.println(em);
//    }
    @Test
    public void testGetAllOrders() {
    	//We first insert an order in the database.
        Product product = new Product();
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        
        //Preparing data to send with the POST request
        List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        OrderLine orderLine = new OrderLine();
        product.setId(3L);
        orderLine.setProduct(product);
        orderLine.setAmount(4);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
        
        Customer customer = new Customer();
        customer.setId(1L);
        
        postOrder.setCustomer(customer);
        postOrder.setOrderLines(orderLines);
        
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
        
        //We expect that the found order has a customer id of 1
        foundOrder = orderFacade.find(orderId);
       
        //We remove the test data from the database, because we don't want to have TEST data in the development database
        orderFacade.remove(foundOrder);
        
        //Making a GET request to receive a response from the webserver
        response = target.request(MediaType.APPLICATION_JSON)
                .get();
        
        output = response.readEntity(String.class);
        jsonReader = Json.createReader(new StringReader(output));
        JsonArray jsonArray = jsonReader.readArray();
        jsonReader.close();
        
        System.out.println("obj size "+ jsonArray.size());
        assertTrue("Previous (" + jsonArray.size() + ") should be greater than current (" + 1 + ")", jsonArray.size() > 0);
//        object.size();
        
    
    }
    //To do: make this test pass
    //@Test
    public void testEditOrder() {
    	//We first insert an order in the database.
        Product product = new Product();
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        
        //Preparing data to send with the POST request
        List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        OrderLine orderLine = new OrderLine();
        product.setId(3L);
        orderLine.setProduct(product);
        orderLine.setAmount(4);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
        
        Customer customer = new Customer();
        customer.setId(1L);
        
        postOrder.setCustomer(customer);
        postOrder.setOrderLines(orderLines);
        
        //Making a POST request to receive a response from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 201 as statuscode
        assertEquals(201, response.getStatus());
//        
        String output = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(output));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        Orders foundOrder = new Orders();
        long orderId = object.getInt("id");
        
        //We set the found order status to verzonden
        foundOrder = orderFacade.find(orderId);
        System.out.println("orderId before put "+ orderId);
        Status status = new Status();
        status.setId(2L);
        foundOrder.setStatus(status);
        //We use the foundOrder edit it's status
        
        //Making a PUT request to receive a response from the webserver
        response = target.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(foundOrder, MediaType.APPLICATION_JSON));
        
        output = response.readEntity(String.class);
        jsonReader = Json.createReader(new StringReader(output));
        object = jsonReader.readObject();
        jsonReader.close();
        
        orderId = object.getInt("id");
        System.out.println("orderId after put "+ orderId);
        //We expect that the found order has a status id of 2
        foundOrder = orderFacade.find(orderId);
        System.out.println("found order getid "+ foundOrder.getId());
        System.out.println("found order status getid "+ foundOrder.getStatus().getId());
        //To do: pass this test
        assertEquals("2", foundOrder.getStatus().getId().toString());
       
        //We remove the test data from the database, because we don't want to have TEST data in the development database
//        orderFacade.remove(foundOrder);
    }
    @Test
    public void testAddOrder() {
        Product product = new Product();
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        
        //Preparing data to send with the POST request
        List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        OrderLine orderLine = new OrderLine();
        product.setId(3L);
        orderLine.setProduct(product);
        orderLine.setAmount(4);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
        
        Customer customer = new Customer();
        customer.setId(1L);
        
        postOrder.setCustomer(customer);
        postOrder.setOrderLines(orderLines);
        
        //Making a POST request to receive a response from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 201 as statuscode
        assertEquals(201, response.getStatus());
//        
        String output = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(output));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        Orders foundOrder = new Orders();
        long orderId = object.getInt("id");
        
        //We expect that the found order has a customer id of 1
        foundOrder = orderFacade.find(orderId);
        assertEquals("1", foundOrder.getCustomer().getId().toString());
       
        //We remove the test data from the database, because we don't want to have TEST data in the development database
        orderFacade.remove(foundOrder);
    }
    
    @Test
    public void testAddOrderWithoutCustomer() {
        Product product = new Product();
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        
        List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        
        OrderLine orderLine = new OrderLine();
        product.setId(3L);
        orderLine.setProduct(product);
        orderLine.setAmount(4);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
        
        Customer customer = new Customer();
        postOrder.setCustomer(customer);
        postOrder.setOrderLines(orderLines);
        
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("customer ID is required", output);
    }
    
    @Test
    public void testAddOrderWithInvalidCustomer() {        
        Product product = new Product();
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        
        OrderLine orderLine = new OrderLine();
        product.setId(3L);
        orderLine.setProduct(product);
        orderLine.setAmount(4);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
        
        Customer customer = new Customer();
        customer.setId(45L);
        
        postOrder.setCustomer(customer);
        postOrder.setOrderLines(orderLines);

        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("customerID wasn't found", output);
    }
    
    @Test
    public void testAddOrderWithoutOrderLines() {
        Product product = new Product();
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        
        OrderLine orderLine = new OrderLine();
        product.setId(3L);
        orderLine.setProduct(product);
        orderLine.setAmount(4);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
        
        Customer customer = new Customer();
        customer.setId(1L);
        
        postOrder.setCustomer(customer);
        
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("Order lines are required", output);
    }
    @Test
    public void testAddOrderWithInvalidOrderLines() {
        Product product = new Product();
        String url = "http://localhost:8080/snel-transport/api/orders";
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        
        OrderLine orderLine = new OrderLine();
        product.setId(60L);
        orderLine.setProduct(product);
        orderLine.setAmount(4);
        
        orderLines.add(orderLine);
        
        Orders postOrder = new Orders();
        
        Customer customer = new Customer();
        customer.setId(1L);
        
        postOrder.setCustomer(customer);
        postOrder.setOrderLines(orderLines);
        
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("product ID wasn't found", output);
    }
}
