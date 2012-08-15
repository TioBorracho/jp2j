package com.jsonp.wrappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
* Extend servlet class to hide headers on jsonp
* all logic is inside the class
*/
public class AlwaysModifiedHttpServletRequest extends HttpServletRequestWrapper {
	static List<String> hiddenHeaders =  Arrays.asList("if-none-match", "if-modified-since");
 
	public  AlwaysModifiedHttpServletRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Enumeration getHeaderNames() {
		@SuppressWarnings("unchecked")
		ArrayList<String> originales = Collections.list(super.getHeaderNames());
		originales.removeAll(hiddenHeaders);
		return Collections.enumeration(originales);
	}
	
	public String getHeader(String headerName) {
		if (hiddenHeaders.contains(headerName.toLowerCase()))
			return null;
		else
			return super.getHeader(headerName);
	}

	public static HttpServletRequest getInstance(HttpServletRequest request) {
		if (isJsonp(request)) {
			return new AlwaysModifiedHttpServletRequest(request);
		} else {
			return request;
		}
	}
	
	public static boolean isJsonp(HttpServletRequest request) {
		return request.getMethod().equals("GET") && request.getParameter("callback") != null;
	}

}