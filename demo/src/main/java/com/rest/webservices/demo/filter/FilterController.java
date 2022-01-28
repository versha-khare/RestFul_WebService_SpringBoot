package com.rest.webservices.demo.filter;

import org.hibernate.boot.jaxb.internal.MappingBinder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {
	
	@GetMapping("/Filter")
	public MappingJacksonValue retrieveFilter()
	{
		SomeBean bean = new SomeBean("C1", "C2", "C3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1" , "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean" , filter);
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filters);
		return mapping;
	}

}
