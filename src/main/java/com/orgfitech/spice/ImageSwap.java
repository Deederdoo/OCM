package com.orgfitech.spice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("imageSwap")
@RequestScoped
public class ImageSwap {

	private List<String> images;
	
	@PostConstruct
	public void init() {
		
		images = new ArrayList<>();
		
		for(int i = 1; i <= 4; i++) {
			images.add("slideMenu_" + i + ".gif");
		}
	}
	
	public List<String> getImages(){
		
		return images;
	}
}
