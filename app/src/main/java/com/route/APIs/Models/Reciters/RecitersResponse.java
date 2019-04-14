package com.route.APIs.Models.Reciters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecitersResponse{

	@SerializedName("reciters")
	private List<RecitersItem> reciters;

	public void setReciters(List<RecitersItem> reciters){
		this.reciters = reciters;
	}

	public List<RecitersItem> getReciters(){
		return reciters;
	}

	@Override
 	public String toString(){
		return 
			"RecitersResponse{" + 
			"reciters = '" + reciters + '\'' + 
			"}";
		}
}