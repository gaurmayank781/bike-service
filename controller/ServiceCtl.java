package in.co.online.bike.service.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.bike.service.bean.BaseBean;
import in.co.online.bike.service.bean.ServiceBean;
import in.co.online.bike.service.exception.ApplicationException;
import in.co.online.bike.service.exception.DuplicateRecordException;
import in.co.online.bike.service.model.ServiceModel;
import in.co.online.bike.service.util.DataUtility;
import in.co.online.bike.service.util.DataValidator;
import in.co.online.bike.service.util.PropertyReader;
import in.co.online.bike.service.util.ServletUtility;

/**
 * Servlet implementation class ServiceCtl
 */
@WebServlet(name = "ServiceCtl", urlPatterns = { "/ServiceCtl" })
public class ServiceCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(ServiceCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("ServiceCtl  validate method start");

		boolean pass = true;

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("company"))) {
			request.setAttribute("company", PropertyReader.getValue("error.require", "Company"));
			pass = false;
		} 

		if (DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getValue("error.require", "User Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("modelNo"))) {
			request.setAttribute("modelNo", PropertyReader.getValue("error.require", "Model No"));
			pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("vehicleNo"))) {
			request.setAttribute("vehicleNo", PropertyReader.getValue("error.require", "Vehicle No"));
			pass = false;
		} 
		log.debug("ServiceCtl  validate method end");
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("ServiceCtl  populateBean method start");

		ServiceBean bean = new ServiceBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setCompany(DataUtility.getString(request.getParameter("company")));

		bean.setUserName(DataUtility.getString(request.getParameter("userName")));

		bean.setModelNo(DataUtility.getString(request.getParameter("modelNo")));

		bean.setVehicleNo(DataUtility.getString(request.getParameter("vehicleNo")));

		populateDTO(bean, request);

		log.debug("ServiceCtl  populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ServiceCtl  doGet method start");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		ServiceModel model = new ServiceModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		// ServletUtility.setOpration("Add", request);
		if (id > 0) {
			ServiceBean bean;
			try {
				bean = model.findByPK(id);

				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		log.debug("ServiceCtl  doGet method end");
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("CollegeCtl  doPost method start");

		ServletUtility.setOpration("Add", request);
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
	ServiceModel model = new ServiceModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			ServiceBean bean = (ServiceBean) populateBean(request);

			try {
				if (id > 0) {
					/*model.update(bean);*/
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setOpration("Edit", request);
				} else {
					long pk = model.add(bean);
					// bean.setId(pk);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				ServletUtility.forward(OBSView.ERROR_VIEW, request, response);
				return;

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			ServiceBean bean = (ServiceBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OBSView.SERVICE_LIST_CTL, request, response);

				return;

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);

				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(OBSView.SERVICE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBSView.SERVICE_CTL, request, response);
			return;
		}
		log.debug("ServiceCtl  doPost method end");
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBSView.SERVICE_VIEW;
	}

}
