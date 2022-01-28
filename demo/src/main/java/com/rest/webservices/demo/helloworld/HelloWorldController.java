package com.rest.webservices.demo.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@Autowired
	private MessageSource source;
	
	@RequestMapping(method= RequestMethod.GET , path="/hello-world")
	public String helloWorld()
	{
		return "Hello-world";
	}
	
	@RequestMapping(method= RequestMethod.GET , path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello-world , %s" , name));
	}
	

	//Internalization
	@GetMapping("/hello-world-Int")
	public String helloWorldInt()
	{
		//return "Hello-world";
		
		return source.getMessage("good.morning.message", null, "default_message", LocaleContextHolder.getLocale());
	}
	
	
	
}
