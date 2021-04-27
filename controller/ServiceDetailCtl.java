package in.co.online.bike.service.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.online.bike.service.util.DataUtility;
import in.co.online.bike.service.util.ServletUtility;

/**
 * Servlet implementation class ServiceDetailCtl
 */
@WebServlet(name = "ServiceDetailCtl", urlPatterns = { "/ctl/ServiceDetailCtl" })
public class ServiceDetailCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceDetailCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = DataUtility.getLong(request.getParameter("id"));
		request.setAttribute("Iddd", id);
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBSView.SERVICE_DETAIL_VIEW;
	}

}
