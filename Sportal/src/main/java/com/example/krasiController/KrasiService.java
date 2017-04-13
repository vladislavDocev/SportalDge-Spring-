package com.example.krasiController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.krasiModel.Address;
import com.example.krasiModel.Papagal;

@RestController
public class KrasiService {

	@RequestMapping(value="/papagalAsString", method=RequestMethod.GET)
	@ResponseBody
	public String printStraightString(){
		return "{\"msg\": \"success\"}";
	}
	
	@RequestMapping(value="/papagalAsJson", method=RequestMethod.GET)
	@ResponseBody
	public Papagal papagalAsJSON(){
		return new Papagal("Goshko", 31, new Address("Sofia", "SanStefano", 29));
	}
}
