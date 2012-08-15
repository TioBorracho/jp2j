package com.jsonp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsonp.wrappers.AlwaysModifiedHttpServletRequest;

public class JsonpHeaderFilter implements Filter {
	private String headerSwitchName=null;
	private String headerSwitchValue=null;

	public void init(FilterConfig filterConfig) throws ServletException {
		setHeaderSwitch(filterConfig.getInitParameter("headerSwitch"));
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest originalRequest = (HttpServletRequest) request;
		HttpServletResponse originalResponse = (HttpServletResponse) response;
		HttpServletRequest finalRequest = AlwaysModifiedHttpServletRequest.getInstance(originalRequest);

		chain.doFilter(finalRequest, originalResponse);
	}

	public void setHeaderSwitch(String headerSwitch) {
		if (headerSwitch != null) {
			String[] headerSwitchPair = headerSwitch.split(":");
			headerSwitchName = headerSwitchPair.length >= 1 ? headerSwitchPair[0] : null;
			headerSwitchValue = headerSwitchPair.length >= 2 ? headerSwitchPair[1] : null;
		}		
	}
	
	public void destroy() {
		
	}

	public void setHeaderSwitchName(String headerSwitchName) {
		this.headerSwitchName = headerSwitchName;
	}

	public String getHeaderSwitchName() {
		return headerSwitchName;
	}

	public void setHeaderSwitchValue(String headerSwitchValue) {
		this.headerSwitchValue = headerSwitchValue;
	}

	public String getHeaderSwitchValue() {
		return headerSwitchValue;
	}
}
