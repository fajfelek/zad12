package com.mycompany.zadanie11;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("exchangeRate")
public class MyResource {
    
    String currId = null;
    
    Parser parser = new Parser();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/{currency}")
    @Produces(MediaType.TEXT_PLAIN)
    public Double get(@PathParam("currency") String currency) {
        currency = currency.toUpperCase();
        
        if (currency.length() == 3){
            return parser.getResult(currency,"USD");
        }
        
        return 1.;
    }
}
