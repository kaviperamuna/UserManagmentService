package com;
import com.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet{
	
	private static final long serialVersionUID =1L;
	User userobj = new User();
	
	public UserAPI()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			 System.out.println("requets recieved");
		String output = userobj.insertUser(request.getParameter("Name"),
			 request.getParameter("Address"),
			request.getParameter("TelephoneNo"),
			request.getParameter("PremisesId"));
			 
			response.getWriter().write(output);
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			 Map paras = getParasMap(request);
			 
			 String output = userobj.updateUser(Integer.parseInt(paras.get("hidItemIDSave").toString()),
			paras.get("Name").toString(),paras.get("Address").toString(),paras.get("TelephoneNo").toString(),paras.get("PremisesId").toString());
			 
			response.getWriter().write(output);
	} 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			 Map paras = getParasMap(request);
			 
			 String output = userobj.deleteUser(paras.get("UserId").toString().trim());
			 System.out.println(paras.get("UserId").toString());
			 response.getWriter().write(output);
	}
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params)
			{ 
	
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}
}