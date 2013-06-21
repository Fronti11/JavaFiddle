package com.mweb.wifi.services;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;



import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mweb.wifi.domain.Device;

@Path("/device")
public class DeviceResource implements IDeviceResource {

	@Override
	public Response getDevices() {
		Device d = new Device();
		ObjectMapper mapper = new ObjectMapper();
		try{
			String out = mapper.writeValueAsString(d);
			return Response.status(200).entity(out).build();
		}catch(JsonGenerationException e){
			return Response.status(500).entity("General Error: " + e.getMessage()).build();
		}catch (JsonMappingException e) {
			return Response.status(500).entity("Mapping Error:" + e.getMessage()).build();
		} catch (IOException e) {
			return Response.status(500).entity("IO Error: " + e.getMessage()).build();
		}
	}

	//Register one or more devices each uniquely identified by MAC address
	@Override
	public Response registerDevices(InputStream is) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
