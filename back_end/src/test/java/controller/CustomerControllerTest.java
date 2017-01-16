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
import nl.cimsolutions.snel_transport.services.CustomerFacade;

public class CustomerControllerTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
    CustomerFacade customerFacade = new CustomerFacade();
    String URL = "http://localhost:8080/snel-transport/api/customers";

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
    public void testGetAllCustomers() {
        Client client = ClientBuilder.newClient();
        // Setting the url for the client
        WebTarget target = client.target(URL);

        // Making a GET request to receive the status from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        String output = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(output));
        JsonArray jsonArray = jsonReader.readArray();
        jsonReader.close();

        int customers = customerFacade.findAll().size();

        assertTrue("Previous (" + customers + ") should be equal to (" + 1 + ")", jsonArray.size() > 1);

    }
}
