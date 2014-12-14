package model2;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CtrlAndMethod
{
	private Object target = null;
	private Method method = null;
	
	public Object getTarget() {
		return target;
	}
	public Method getMethod() {
		return method;
	}
	
	public CtrlAndMethod( Object c, Method m ){
		target = c;
		method = m;
	}
	
	
}
public class DispatcherServlet extends HttpServlet{

	private final static String prefix ="/";
	private final static String sufix =".jsp";
	private String ctxPath = null;
	
	
	public DispatcherServlet(){
		mapCtrlAndMethod = new Hashtable<String, CtrlAndMethod>();
	}
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String reqUri = request.getRequestURI();
		System.out.println(" a : "+reqUri);
		if( ctxPath == null ){
			ctxPath = request.getContextPath();
		}
		
		String uri = reqUri.substring( ctxPath.length() );
		
		CtrlAndMethod ctrlAndMethod = mapCtrlAndMethod.get( uri );
		if( ctrlAndMethod != null ){
			Object target = ctrlAndMethod.getTarget();
			Method method = ctrlAndMethod.getMethod();
			
			try{
				ModelAndView mnv = (ModelAndView)method.invoke(target, request, response);
			
				if( mnv == null ){
					System.out.println("ModelAndView IS null");
				}
				else{
					String path = mnv.getPath();
					
					if( path.startsWith("redirect:")){
						path = path.substring("redirect:".length());
						path = ctxPath + path;
						
						response.sendRedirect( path );
					}			
					else{
						System.out.println(path);
						
						Map<String,Object> m = mnv.getMap();
						if( m != null ){
							Set<String> ks = m.keySet();
							Iterator<String> it = ks.iterator();
							while( it.hasNext() ){
								String key = it.next();
								Object val = m.get(key);
								
								request.setAttribute(key, val);
							}
						}
						
						path = prefix + path + sufix;
						if(path.startsWith("/list.jsp"))
						{
							path="/list.do";
						}
						if(path.indexOf("up/")!=-1)
						{
							path= path.substring("up//".length());
							path="/show.do?tpk="+path;
						}
						RequestDispatcher rd = application.getRequestDispatcher( path );
						rd.forward( request, response );
						System.out.println(path);
					}
				}
			}
			catch( Exception e ){

				Throwable temp = e.getCause();
				if( temp != null && temp instanceof Exception ){
					e = (Exception)temp;
				}
				
				request.setAttribute("err", e);
				RequestDispatcher rd = application.getRequestDispatcher( "/onerror.jsp" );
				rd.forward( request , response );
			}
		}
		
	}
	
	private Map<String,CtrlAndMethod> mapCtrlAndMethod = null;
	private ServletContext application = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		application = config.getServletContext();
	
		String pc = config.getInitParameter("controllers");
		if( pc == null ){
			return;
		}
		
		String[] ctrls = pc.split(",");
		String ctrl = null;
		for( int i = 0; i < ctrls.length ; i++ )
		{
			try{
				ctrl = ctrls[i].trim();
				fillMap( mapCtrlAndMethod, ctrl );
			}
			catch( ClassNotFoundException e ){}
		}
		
	}


	
	private static void fillMap( Map<String,CtrlAndMethod> info, String ctrlName)
	throws ClassNotFoundException
	{
		try{
			Class<?> cls = Class.forName(ctrlName);
			Object ctrl = cls.newInstance();
			
			Method[] mtds = cls.getMethods();
			for( int i = 0; i < mtds.length; i++ ){
				Class<?>[] ptype = mtds[i].getParameterTypes();
				if( ptype.length == 2 && ptype[0] ==HttpServletRequest.class && ptype[1] == HttpServletResponse.class)
				{
					UrlMapping mapping =(UrlMapping)mtds[i].getAnnotation(UrlMapping.class);
					if( mapping != null )
					{
						String url = mapping.value();
						info.put(url, new CtrlAndMethod( ctrl , mtds[i] ));
					}
				}
			}
		}
		catch( ClassNotFoundException e ){ throw e; }
		catch( Exception e ){}
	}

}














