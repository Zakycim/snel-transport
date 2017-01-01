package nl.cimsolutions.snel_transport;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.management.ReflectionException;

public class Server {
    private String port;
    public Server() {}
    
    public String getPort() {
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


    public String getEndPoints() throws MalformedObjectNameException, NullPointerException, UnknownHostException,
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
