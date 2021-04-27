package in.co.online.bike.service.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.bike.service.bean.BaseBean;
import in.co.online.bike.service.bean.PartsBean;
import in.co.online.bike.service.bean.ServiceBean;
import in.co.online.bike.service.exception.ApplicationException;
import in.co.online.bike.service.exception.DuplicateRecordException;
import in.co.online.bike.service.model.PartsModel;
import in.co.online.bike.service.model.ServiceModel;
import in.co.online.bike.service.util.DataUtility;
import in.co.online.bike.service.util.DataValidator;
import in.co.online.bike.service.util.PropertyReader;
import in.co.online.bike.service.util.ServletUtility;

/**
 * Servlet implementation class PartsCtl
 */
@WebServlet(name = "PartsCtl", urlPatterns = { "/ctl/PartsCtl" })
public class PartsCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(PartsCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("PartsCtl  validate method start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("company"))) {
			request.setAttribute("company", PropertyReader.getValue("error.require", "Company"));
			pass = false;
		} 

		if (DataValidator.isNull(request.getParameter("partName"))) {
			request.setAttribute("partName", PropertyReader.getValue("error.require", "Part Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("freq"))) {
			request.setAttribute("freq", PropertyReader.getValue("error.require", "Frequency"));
			pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		} 
		log.debug("PartsCtl  validate method end");
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("PartsCtl  populateBean method start");

		PartsBean bean = new PartsBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setCompany(DataUtility.getString(request.getParameter("company")));

		bean.setPartName(DataUtility.getString(request.getParameter("partName")));

		bean.setFrequency(DataUtility.getString(request.getParameter("freq")));

		bean.setPrice(DataUtility.getString(request.getParameter("price")));

		populateDTO(bean, request);

		log.debug("PartsCtl  populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PartsCtl  doGet method start");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		PartsModel model = new PartsModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		// ServletUtility.setOpration("Add", request);
		if (id > 0) {
			PartsBean bean;
			try {
				bean = model.findByPK(id);

				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		log.debug("PartsCtl  doGet method end");
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PartsCtl  doPost method start");

		ServletUtility.setOpration("Add", request);
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		PartsModel model = new PartsModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			PartsBean bean = (PartsBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
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

			PartsBean bean = (PartsBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OBSView.PARTS_LIST_CTL, request, response);

				return;

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);

				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(OBSView.PARTS_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBSView.PARTS_CTL, request, response);
			return;
		}
		log.debug("PartsCtl  doPost method end");
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBSView.PARTS_VIEW;
	}

}
