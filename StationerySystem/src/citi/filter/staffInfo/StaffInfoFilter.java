package citi.filter.staffInfo;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import citi.hibernate.entity.Staff;
/**
 * Servlet Filter implementation class StaffInfoFilter
 */
public class StaffInfoFilter implements Filter {

    /**
     * Default constructor. 
     */
    public StaffInfoFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpSession session =  request.getSession();
		session.setAttribute("soeId", "YD83768");
		session.setAttribute("staff", new Staff("YD83768", "董永辉", true, "LJZ", "VIII", "muni"));
		// pass the request along the filter chain
		chain.doFilter(request, servletResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
