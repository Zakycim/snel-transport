package nl.cimsolutions.snel_transport.services;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ReadOnlyBufferException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Route;

public class RouteFacade extends AbstractFacade<Route> {

    public RouteFacade() {
        // TODO Auto-generated constructor stub
        super(Route.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public void clearTable(){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        EntityManager em = emf.createEntityManager();
        
        try {

            em.getTransaction().begin();

            Query q1 = em.createNativeQuery("DELETE FROM Route");

            q1.executeUpdate();

            em.getTransaction().commit();
        } catch (ReadOnlyBufferException e) {
            e.printStackTrace();
        }


    }
    
    public Route shortestDistance(Orders order, List<Orders> orders){
        CustomerFacade customerFacade = new CustomerFacade();
        Customer shortestCustomer = new Customer();

        Route route = null;
        int shortestDistance = 0;
        String duration = "";
        Customer customerB = new Customer();
        
        String home = "Zeugstraat";
        
        for (int i = 0; i < orders.size(); i++) {
            route = new Route();
            System.out.println("i is " + i);
            
            Orders firstOrder = orders.get(0);
            Orders lastOrder = orders.get(orders.size() - 1);
                        
            if(order.getCustomer().getId().equals(lastOrder.getCustomer().getId())) {
                //the origin is the last customer so the destination of the driver will be the head office 
                System.out.println("i STAAT GELIJK AAN orders.size");
                String origins = "";
                String destinations = "";
                String currentDistance= "";
                try {
                    if(i == 0){
                        //The start of the route is always from the head office
                        origins =  URLEncoder.encode(home, "UTF-8");         
                    }  else {
                        origins = URLEncoder.encode(order.getCustomer().getAdres(), "UTF-8");                                
                    }
                    System.out.println("i = " + i +" en orders size = " +orders.size());
                  
                    System.out.println("end out yea");
                    //The end of the route always leads the driver back to the head office
                    destinations =URLEncoder.encode(home, "UTF-8");  
                    
                    System.out.println("adres van customer B "+ home);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                route = getDistance(origins, destinations);
                
                if(destinations.equals(home)){
                    System.out.println("destination value is hetzelfde als home");
                    //no need to search for the shortest distance
                    shortestDistance = route.getDistance();
                    route.setDistance(shortestDistance);
                    duration = route.getDuration();
                    route.setDuration(duration);
                    customerB = customerFacade.find(1L);
                }
            }
            else if(i+1 < orders.size()) {
                if (orders.get(i + 1).getCustomer() != null) {
                    if(!order.getCustomer().getAdres().equals(orders.get(i + 1).getCustomer().getAdres())){
                        
                        String origins = "";
                        String destinations = "";
                        String currentDistance= "";
                        try {
                            if(i == 0){
                                //The start of the route is always from the head office
                                origins =  URLEncoder.encode(home, "UTF-8");         
                            }  else {
                                origins = URLEncoder.encode(order.getCustomer().getAdres(), "UTF-8");                                
                            }
                            System.out.println("i = " + i +" en orders size = " +orders.size());
                       
                            destinations = URLEncoder.encode(orders.get(i + 1).getCustomer().getAdres(), "UTF-8");
                            
                            System.out.println("adres van customer B "+ orders.get(i + 1).getCustomer().getAdres());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        route = getDistance(origins, destinations);
                        
                        
                        if(shortestDistance == 0) {
                            shortestDistance = route.getDistance();
                            System.out.println("distanceValue IF " + route.getDistance());
                            System.out.println("shortestDistance IF " + shortestDistance);
                            route.setDistance(shortestDistance);
                            
                            duration = route.getDuration();
                            
                            route.setDuration(duration);
                            customerB = orders.get(i + 1).getCustomer();
                            
                        } else {
                            System.out.println("distanceValue ELSE " + route.getDistance());
                            System.out.println("shortestDistance ELSE " + shortestDistance);
                            if( route.getDistance() < shortestDistance) {
                                //a shorter distance has been found
                                shortestDistance = route.getDistance();
                                
                                duration = route.getDuration();
                                route.setDuration(duration);
                                customerB = orders.get(i + 1).getCustomer();
                            }
                            System.out.println("einde else statment...");
                        }
                        
                        System.out.println("---------------------------");
                    }
                    
                    
                }   
            }            
        }
        
        if (order.getCustomer() != null) {
            route.setCustomerA(order.getCustomer()); 
            System.out.println("CustomerA " + order.getCustomer().getAdres());
        }
        
        route.setDistance(shortestDistance);
        route.setDuration(duration);  
        System.out.println("customerbb before creat "+ customerB.getAdres());
        route.setCustomerB(customerB);   
        super.create(route);
        return route;
        
    }
    
    public Route getDistance(String origins, String destinations) {
        Route route = new Route();
        int distanceValue = 0;
        String duration = "";
        
        String googleApiURL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+origins+"&destinations="+destinations+"&key=AIzaSyBIYcblUGajTp-uRV4B-9MkDaPSJrguafc";
        System.out.println("gogoleApiURL "+ googleApiURL);
        Client client = ClientBuilder.newClient();
        //Setting the url for the client
        WebTarget target = client.target(googleApiURL);
        
        //Making a GET request to receive the status from the webserver
        Response response = target.request(MediaType.APPLICATION_JSON)
                .get();
        
        String output = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(output));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        
        JsonValue rowObj = jsonObject.getJsonArray("rows").get(0);
        jsonReader = Json.createReader(new StringReader(rowObj.toString()));
        jsonObject = jsonReader.readObject();
        jsonReader.close();
        
        JsonValue elementObj = jsonObject.getJsonArray("elements").get(0);
        jsonReader = Json.createReader(new StringReader(elementObj.toString()));
        jsonObject = jsonReader.readObject();
        jsonReader.close();
        
        
        JsonValue distanceObj = jsonObject.get("distance");
        JsonValue durationeObj = jsonObject.get("duration");
        
        jsonReader = Json.createReader(new StringReader(distanceObj.toString()));
        jsonObject = jsonReader.readObject();
        jsonReader.close();
        
        distanceValue = jsonObject.getInt("value");
        System.out.println("***distancevalue*** " + distanceValue  );
        
        jsonReader = Json.createReader(new StringReader(durationeObj.toString()));
        jsonObject = jsonReader.readObject();
        jsonReader.close();
        
        System.out.println("duration distance");
        
        duration = jsonObject.getString("text");
        
        
        System.out.println("getDistance DISTANCE : "+ distanceValue + " DURATION :  " + duration  );
        route.setDistance(distanceValue);
        route.setDuration(duration);
        
        return route;
    }
}
