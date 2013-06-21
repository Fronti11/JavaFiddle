package com.mweb.wifi.services;
import java.io.InputStream;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


public interface IDeviceResource {
	
	//Register one or more devices each uniquely identified by MAC address
	@POST
	@Consumes("application/json")
	public Response registerDevices(InputStream is);

	//Returns a list of devices registered by the authenticated user (system)
	@GET
	@Produces("application/json")
	public Response getDevices();
	
	//Delete one or more devices that were registered by the authenticated user. 
	//Query parameters can be provided to further refine the set of devices for deletion
	@DELETE
	public Response deleteDevices(@Context UriInfo info);
	

	//Query a registered device by MAC address - the MAC address must have been registered by the authenticated user
	@GET
	@Path("{mac}")
	public Response getDevice(@PathParam("mac") String mac);
	
	//Remove a registered device by MAC address - the MAC address must have been registered by the authenticated user
	@DELETE
	@Path("{mac}")
	public Response removeDevice(@PathParam("mac") String mac);
	
}
