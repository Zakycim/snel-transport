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
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

import nl.cimsolutions.snel_transport.models.Category;
import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Product;
import nl.cimsolutions.snel_transport.models.Status;
import nl.cimsolutions.snel_transport.services.CategoryFacade;
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
    CategoryFacade categoryFacade = new CategoryFacade();
    String orderURL = "http://localhost:9090/snel-transport/api/orders";
    Orders foundOrder = new Orders();
    
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
    
    public void insertCategories(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport-test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        
        Query q = em.createNativeQuery("INSERT INTO Category (Name, categoryId) " +
                " VALUES(?,?)");
        q.setParameter(1, "Games");
        q.setParameter(2, 1L);
        q.executeUpdate();
                
        tx.commit();
    }
    
    //hier was je: je moet products in test db inserten je weet wel
    public void insertProducts(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport-test");
//        EntityManager em = emf.createEntityManager();
        
//        Category category = categoryFacade.find(1L);
//        Product product = new Product();
//        product.setName("Snake");
//        product.setCode("001");
//        product.setPrice(10.00);
//        product.setId(1L);
//        product.setCategories(category);
//        productFacade.create(product);
        
        System.out.println("ben er voorbij");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        
        Query q = em.createNativeQuery("INSERT INTO Product(id, code) " +
                " VALUES(?,?)");
        q.setParameter(1, 1);
        q.setParameter(2, "00332");
        q.executeUpdate();
                
        tx.commit();

        
//        Ik kan ook geen catagory via de code aanmaken blijkbaar
//        Category category = new Category();
//        category.setCategoryId(2L);
//        category.setName("whatever");
//        categoryFacade.create(category);
        
        

    }
    
    public Response addOrderMethod(String url, long productId, int amount, long customerId, boolean orderline, boolean deleteOrder){
    	//We first insert an order in the database.
        Product product = new Product();
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
        
        //Preparing data to send with the POST request
        List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        OrderLine orderLine = new OrderLine();
        product.setId(productId);

        Customer customer = new Customer();
        
        Orders postOrder = new Orders();
        if(customerId == 0L){
        } else { 	
            customer.setId(customerId);

        }
        postOrder.setCustomer(customer);
        
        // Check if orderline needs to be added to the order
        if(orderline == true){
        	orderLine.setProduct(product);
            orderLine.setAmount(amount);
            
            orderLines.add(orderLine);
            
            // Add orderlines to the orderRequest
            postOrder.setOrderLines(orderLines);
        }
        
        //Making a POST request to receive a response from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(postOrder, MediaType.APPLICATION_JSON));
        
        if(deleteOrder == true){
        	
        	String output = response.readEntity(String.class);
            JsonReader jsonReader = Json.createReader(new StringReader(output));
            JsonObject object = jsonReader.readObject();
            jsonReader.close();

            long orderId = object.getInt("id");
            System.out.println("Orderid is "+orderId);
            
            //We expect that the found order has a customer id of 1
            foundOrder = orderFacade.find(orderId);
            
            //orderFacade.remove(foundOrder);
        }
        
    	return response;
    }
    
//    @Test
//    public void test(){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
//        EntityManager em = emf.createEntityManager();
//        System.out.println("test em");
//        System.out.println(em);
//    }
    @Test
    public void testGetOrderByInvalidId() {
    	
    	Client client = ClientBuilder.newClient();
    	orderURL = "http://localhost:8080/snel-transport/api/orders/234234";
        WebTarget target = client.target(orderURL);
        
        //Making a GET request to receive a response from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON)
                .get();
        
      //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
    }
    @Test
    public void testGetOrderById() {
    	Response response = addOrderMethod(orderURL, 3L, 4, 1L, true, false);
    	
    	Client client = ClientBuilder.newClient();
    	//Setting the url for the client
    	String output = response.readEntity(String.class);
    	JsonReader jsonReader = Json.createReader(new StringReader(output));
    	JsonObject object = jsonReader.readObject();
    	jsonReader.close();
    	
    	long orderId = object.getInt("id");
    	
    	orderURL = "http://localhost:8080/snel-transport/api/orders/"+orderId;
    	WebTarget target = client.target(orderURL);
    	
    	//Making a GET request to receive a response from the webserver
    	response = target.request(MediaType.APPLICATION_JSON)
    			.get();
    	
    	//We expect to receive a 200 as statuscode
    	assertEquals(200, response.getStatus());
    	
    	//We expect that the found order has a customer id of 1
        foundOrder = orderFacade.find(orderId);
    	
    	//We remove the test data from the database
        orderFacade.remove(foundOrder);
    }
    @Test
    public void testGetAllOrders() {
    	Response response = addOrderMethod(orderURL, 3L, 4, 1L, true, false);
    	
    	Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(orderURL);
        
      //We expect to receive a 201 as statuscode
        assertEquals(201, response.getStatus());
        
        String output = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(output));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        long orderId = object.getInt("id");
        
        //We expect that the found order has a customer id of 1
        foundOrder = orderFacade.find(orderId);
        
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
        
      //We remove the test data from the database, because we don't want to have TEST data in the development database
        orderFacade.remove(foundOrder);
    }
    
    @Test
    public void testEditOrder() {
    	Response response = addOrderMethod(orderURL, 3L, 4, 1L, true, false);
    	
    	Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(orderURL);
        
        //We expect to receive a 201 as statuscode
        assertEquals(201, response.getStatus());
        
        String output = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(output));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        long orderId = object.getInt("id");
        
        //We expect that the found order has a customer id of 1
        foundOrder = orderFacade.find(orderId);
    	
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
        orderFacade.remove(foundOrder);

    }
    @Test
    public void testAddOrder() {
//        insertCategories();
//        insertProducts();
        // Add order by method addOrderMethod
    	Response response = addOrderMethod(orderURL, 3L, 4, 1L, true, false);
    	
    	 //We expect to receive a 201 as statuscode
        assertEquals(201, response.getStatus());
        
        String output = response.readEntity(String.class);
        System.out.println("output yes");
        System.out.println(output);
        System.out.println("output klaar");
        JsonReader jsonReader = Json.createReader(new StringReader(output));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        Orders foundOrder = new Orders();
        long orderId = object.getInt("id");
        
        //We expect that the found order has a customer id of 1
        foundOrder = orderFacade.find(orderId);
    	
    	// Check if order with id 1 exists
        assertEquals("1", foundOrder.getCustomer().getId().toString());
       
    }
    @Test
    public void testAddOrderWithoutCustomer() {
    	// Add order by method addOrderMethod
    	Response response = addOrderMethod(orderURL, 3L, 4, 0L, true, false);
        
        //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("customer ID is required", output);
    }
    @Test
    public void testAddOrderWithInvalidCustomer() {        
    	// Add order by method addOrderMethod
    	Response response = addOrderMethod(orderURL, 3L, 4, 80L, true, false);
        
        //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        System.out.println("output response read entity "+output);
        assertEquals("customerID wasn't found", output);
    }
    @Test
    public void testAddOrderWithoutOrderLines() {
    	// Add order by method addOrderMethod
    	Response response = addOrderMethod(orderURL, 3L, 4, 1L, false, false);
        
        //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        assertEquals("Order lines are required", output);
    }
    @Test
    public void testAddOrderWithInvalidOrderLines() {
    	// Add order by method addOrderMethod
    	Response response = addOrderMethod(orderURL, 60L, 4, 1L, true, false);
        
        //We expect to receive a 400 as statuscode
        assertEquals(400, response.getStatus());
        
        String output = response.readEntity(String.class);
        System.out.println("output response read entity "+output);
        assertEquals("product ID wasn't found", output);
    }
    
    @Test
    public void testAddOrderList() {
        
        //Lets create three orders before we can divide them to the trucks
        //addOrderMethod(String url, long productId, int amount, long customerId, boolean orderline, boolean deleteOrder)
        Response response = addOrderMethod(orderURL, 3L, 4, 1L, true, false);
        
        
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        String output = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(output));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        long orderId = object.getInt("id");
        
        
        //Creating the second order
        response = addOrderMethod(orderURL, 4L, 4, 2L, true, false);
        client = ClientBuilder.newClient();
        //Setting the url for the client
        output = response.readEntity(String.class);
        jsonReader = Json.createReader(new StringReader(output));
        object = jsonReader.readObject();
        jsonReader.close();
        
        long secOrderId = object.getInt("id");
        
        //Creating the third order
        response = addOrderMethod(orderURL, 4L, 4, 4L, true, false);
        client = ClientBuilder.newClient();
        //Setting the url for the client
        output = response.readEntity(String.class);
        jsonReader = Json.createReader(new StringReader(output));
        object = jsonReader.readObject();
        jsonReader.close();
        
        long thirdOrderId = object.getInt("id");
        
        

        
        
        
        
        String url = "http://localhost:8080/snel-transport/api/orders/deliverylistz";
        client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(url);
                
        //Making a GET request to receive a response from the webserver
        response = target.request(MediaType.APPLICATION_JSON)
                .get();
        
        //We remove the test data from the database
//        Orders foundOrder = new Orders();
//        foundOrder = orderFacade.find(orderId);
//        orderFacade.remove( foundOrder );
//        foundOrder = orderFacade.find(secOrderId);
//        orderFacade.remove( foundOrder );
//        foundOrder = orderFacade.find(thirdOrderId);
//        orderFacade.remove( foundOrder );

        //We expect to receive a 201 as statuscode
        assertEquals(201, response.getStatus());
     
        output = response.readEntity(String.class);
        jsonReader = Json.createReader(new StringReader(output));
        object = jsonReader.readObject();
        jsonReader.close();
        
        
//        Orders foundOrder = new Orders();
//        orderId = object.getInt("id");
//        
//        //We expect that the found order has a customer id of 1
//        foundOrder = orderFacade.find(orderId);
//        assertEquals("1", foundOrder.getCustomer().getId().toString());
//       
//        //We remove the test data from the database, because we don't want to have TEST data in the development database
//        orderFacade.remove(foundOrder);
    }
}
