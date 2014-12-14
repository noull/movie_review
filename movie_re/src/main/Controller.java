package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.ModelAndView;

public interface Controller {
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
