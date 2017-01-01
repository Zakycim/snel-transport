
package nl.cimsolutions.snel_transport.controllers;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.management.ReflectionException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.sessions.server.Server;

import java.util.Iterator;
import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Product;
import nl.cimsolutions.snel_transport.models.Route;
import nl.cimsolutions.snel_transport.models.Status;
import nl.cimsolutions.snel_transport.models.Truck;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;
import nl.cimsolutions.snel_transport.services.RouteFacade;
import nl.cimsolutions.snel_transport.services.StatusFacade;
import nl.cimsolutions.snel_transport.services.TruckFacade;
import nl.cimsolutions.snel_transport.services.OrderLineFacade;
import nl.cimsolutions.snel_transport.services.OrderListFacade;

/**
 * Root resource (exposed at "orders" path)
 */
@Path("orders")
public class OrdersController {
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */

	OrdersFacade orderFacade = new OrdersFacade();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Orders> getAllOrders() {
		System.out.println("getallorders");
		// OrdersFacade orderFacade = new OrdersFacade();
		List<Orders> orders = orderFacade.findAll();// findAll();

		System.out.println(orders);
		System.out.println("getallorders end");
		return orders;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderById(@PathParam("id") long id) {
		OrdersFacade orderFacade = new OrdersFacade();

		Orders order = orderFacade.find(id);

		// Check if order is empty
		if (order == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't find customer").build();
		} else {
			return Response.status(Response.Status.OK).entity(order).build();
		}

	}

	@GET
	@Path("/deliverylist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderList> getOrderList() {
		System.out.println("getallorders");
		OrderListFacade orderListFacade = new OrderListFacade();
		List<OrderList> orderlists = orderListFacade.findAll();// findAll();

		return orderlists;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addOrder(Orders data) {
	    
	    
	    System.out.println("karalz");
//	    String port = getPort();

	    
		if (data.getCustomer().getId() == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("customer ID is required").build();
		}

		CustomerFacade customerFacade = new CustomerFacade();
		Customer customer = customerFacade.find(data.getCustomer().getId());

		if (customer == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("customerID wasn't found").build();
		}

		Orders order = new Orders();
		StatusFacade statusFacade = new StatusFacade();

		Status status = statusFacade.find(1L);
		order.setCustomer(customer);

		Date orderDate = new Date();
		order.setOrderDate(orderDate);
		order.setStatus(status);

		if (data.getOrderLines() == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Order lines are required").build();
		}

		List<OrderLine> orderLines = new ArrayList<OrderLine>();
		Product product = new Product();
		ProductFacade productFacade = new ProductFacade();

		//JE HEBT NOG GEEN PRODUCTEN IN JE TEST DATABASE! DRM KAN JE OOK NOG GEEN ORDERS OPSLAAN IN DE DATABASE WAT HEEL LOGISCH IS...
		for (int i = 0; i < data.getOrderLines().size(); i++) {
			OrderLine orderLine = new OrderLine();
			product = productFacade.find(data.getOrderLines().get(i).getProduct().getId());
			if (product == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("product ID wasn't found").build();
			}

			orderLine.setProduct(product);
			orderLine.setAmount(data.getOrderLines().get(i).getAmount());
			orderLines.add(orderLine);
		}
		System.out.println("qwe");

		order.setOrderLines(orderLines);

		OrdersFacade orderFacade = new OrdersFacade();

		Orders newlyOrder = new Orders();
		newlyOrder = orderFacade.create(order);

		return Response.status(Response.Status.CREATED).entity(newlyOrder).build();
//		return Response.status(Response.Status.CREATED).entity("lol").build();
	}

	public String getPort() {
	    String port = "";
        try {
            port = getEndPoints();
           System.out.println(port);
       } catch (MalformedObjectNameException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (AttributeNotFoundException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (InstanceNotFoundException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (NullPointerException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (UnknownHostException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (MBeanException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (ReflectionException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
        return port;
    }

    @POST
	@Path("/orderlines")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addOrderLine(OrderLine[] data) {
		OrderLineFacade orderLineFacade = new OrderLineFacade();
		// Print all the array elements
		for (int i = 0; i < data.length; i++) {
			OrderLine orderLine = new OrderLine();
			orderLine.setOrderId(data[i].getOrderId());
			orderLine.setProduct(data[i].getProduct());
			orderLine.setAmount(data[i].getAmount());
			orderLineFacade.create(orderLine);
		}

		return Response.status(Response.Status.CREATED).entity(data).build();

	}

	@POST
	@Path("/deliverylist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderList> addOrderList() {
		OrderListFacade orderListFacade = new OrderListFacade();
		TruckFacade truckFacade = new TruckFacade();
		OrdersFacade ordersFacade = new OrdersFacade();
		List<OrderList> orderlists = new ArrayList<>();
		Long lastOrderListID = 1L;
		OrderList lastOrderList = new OrderList();
		int availableTime = 480;
		TruckFacade tf = new TruckFacade();
		 List<Truck> trucks = tf.getAllTrucks();
		
		// Print all the array elements

		// clear the table of orderlist
		orderListFacade.clearTable();
		
		// calculatie van pallavi aantal orders voor 10 uur
		List<Orders> orders = ordersFacade.getOrdersByDates();
		int j = 0;
		int truckSize = trucks.size();

		int pallavidatumshizzle = 5;

		for (int i = 0; i < orders.size(); i++) {
			
			OrderList orderList = new OrderList();
			Route route = new Route();
			//assign truck to orderlist
			if(availableTime <= 0){
				j++;
				availableTime = 480;
			}
			
			if (j == truckSize) {
				break;
			}
			else{
				orderList.setOrder(orders.get(i));
                orderList.setTruck(trucks.get(j).getId());
                
                orderList = orderListFacade.create(orderList);
    			orderlists.add(orderList);
    			System.out.println("hier :" + orders.get(i));
			}
			// bestellingtijd - 60
			availableTime -= 60;
		}

		return orderlists;
	}

	//TO DO: use this instead of addOrderList so that you can test this
    @GET
    @Path("/deliverylistz")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderList> divideOrderList() {
        System.out.println("divideOrderList");
        
        OrderListFacade orderListFacade = new OrderListFacade();
        TruckFacade truckFacade = new TruckFacade();
        OrdersFacade ordersFacade = new OrdersFacade();
        List<OrderList> orderlists = new ArrayList<>();
        Long lastOrderListID = 1L;
        OrderList lastOrderList = new OrderList();
        int availableTime = 480;
        TruckFacade tf = new TruckFacade();
        List<Truck> trucks = tf.getAllTrucks();
        RouteFacade routeFacade = new RouteFacade();

        // Print all the array elements

        // clear the table of orderlist
        orderListFacade.clearTable();
        routeFacade.clearTable();

        // calculatie van pallavi aantal orders voor 10 uur
        List<Orders> orders = ordersFacade.getOrdersByDates();
        int j = 0;
        int truckSize = trucks.size();

        int pallavidatumshizzle = 5;

        for (int i = 0; i < orders.size(); i++) {

            OrderList orderList = new OrderList();
            Route route = new Route();
            // assign truck to orderlist
            if (availableTime <= 0) {
                j++;
                availableTime = 480;
            }

            if (j == truckSize) {
                break;
            } else {
                orderList.setOrder(orders.get(i));
                orderList.setTruck(trucks.get(j).getId());
                
                routeFacade = new RouteFacade();
                route = routeFacade.shortestDistance(orders.get(i), orders);
              
                orderList.setRouteId(route.getId());
                
                orderList = orderListFacade.create(orderList);
                orderlists.add(orderList);
                System.out.println("hier :" + orders.get(i));
            }
            // bestellingtijd - 60
            availableTime -= 60;
        }

        return orderlists;
    }
	   
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editOrder(Orders data) {
		Orders order = new Orders();
		Orders editedOrder = new Orders();
		System.out.println("editOrder");
		System.out.println("data id " + data.getId());

		order = orderFacade.find(data.getId());
		order.setStatus(data.getStatus());

		// order = orderFacade.edit(order);
		// return
		// Response.status(Response.Status.CREATED).entity(order).build();
		try {
			editedOrder = orderFacade.edit(order);
			return Response.status(Response.Status.CREATED).entity(editedOrder).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't update order status").build();
		}
	}

	@GET
	@Path("/{id}/orderlines")
	@Produces(MediaType.APPLICATION_JSON)
	public Orders getOrderLines(@PathParam("id") long id) {
		// OrdersFacade orderFacade = new OrdersFacade();

		Orders order = orderFacade.find(id);

		return order;// Response.status(Response.Status.CREATED).entity(value).build();

	}
	
    String getEndPoints() throws MalformedObjectNameException, NullPointerException, UnknownHostException,
            AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"),
                Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
        String hostname = InetAddress.getLocalHost().getHostName();
        InetAddress[] addresses = InetAddress.getAllByName(hostname);
        ArrayList<String> endPoints = new ArrayList<String>();
        String port = "";
        for (Iterator<ObjectName> i = objs.iterator(); i.hasNext();) {
            ObjectName obj = i.next();
            String scheme = mbs.getAttribute(obj, "scheme").toString();
            port = obj.getKeyProperty("port");
            for (InetAddress addr : addresses) {
                String host = addr.getHostAddress();
                String ep = scheme + "://" + host + ":" + port;
                endPoints.add(ep);
            }
        }
        return port;
    }
}