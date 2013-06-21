package com.mweb.wifi.domain;

public class Device {
	
	private String mac;
	private String make;
	private String model;
	private String vendor;
	
	public Device(){
		this.mac = "";
		this.make = "";
		this.model = "";
		this.vendor = "";
	}
	
	public Device(String mac, String make, String model, String vendor){
		this.mac = mac;
		this.make = make;
		this.model = model;
		this.vendor = vendor;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
}
