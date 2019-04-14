package com.route.APIs.Models.Radios;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RadioResponse{

	@SerializedName("Radios")
	private List<RadiosItem> radios;

	public void setRadios(List<RadiosItem> radios){
		this.radios = radios;
	}

	public List<RadiosItem> getRadios(){
		return radios;
	}

	@Override
 	public String toString(){
		return 
			"RadioResponse{" + 
			"radios = '" + radios + '\'' + 
			"}";
		}
}