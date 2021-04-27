package in.co.online.bike.service.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.bike.service.bean.BaseBean;
import in.co.online.bike.service.bean.FeedBackBean;
import in.co.online.bike.service.bean.ServiceBean;
import in.co.online.bike.service.exception.ApplicationException;
import in.co.online.bike.service.exception.DuplicateRecordException;
import in.co.online.bike.service.model.FeedBackModel;
import in.co.online.bike.service.model.ServiceModel;
import in.co.online.bike.service.util.DataUtility;
import in.co.online.bike.service.util.DataValidator;
import in.co.online.bike.service.util.PropertyReader;
import in.co.online.bike.service.util.ServletUtility;

/**
 * Servlet implementation class FeedBackCtl
 */
@WebServlet(name = "FeedBackCtl", urlPatterns = { "/FeedBackCtl" })
public class FeedBackCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FeedBackCtl.class);

	
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("FeedBackCtl  validate method start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("subject"))) {
			request.setAttribute("subject", PropertyReader.getValue("error.require", "Subject"));
			pass = false;
		} 

		if (DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getValue("error.require", "User Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("message"))) {
			request.setAttribute("message", PropertyReader.getValue("error.require", "Message"));
			pass = false;
		} 
		 
		log.debug("FeedBackCtl  validate method end");
		return pass;
	}
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("FeedBackCtl  populateBean method start");

		FeedBackBean bean = new FeedBackBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setSubject(DataUtility.getString(request.getParameter("subject")));

		bean.setUserName(DataUtility.getString(request.getParameter("userName")));

		bean.setMessage(DataUtility.getString(request.getParameter("message")));


		populateDTO(bean, request);

		log.debug("FeedBackCtl  populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FeedBackCtl  doGet method start");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		FeedBackModel model = new FeedBackModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		// ServletUtility.setOpration("Add", request);
		if (id > 0) {
			FeedBackBean bean;
			try {
				bean = model.findByPK(id);

				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		log.debug("FeedBackCtl  doGet method end");
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
	FeedBackModel model = new FeedBackModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			FeedBackBean bean = (FeedBackBean) populateBean(request);

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

			FeedBackBean bean = (FeedBackBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OBSView.FEEDBACK_LIST_CTL, request, response);

				return;

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);

				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(OBSView.FEEDBACK_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBSView.FEEDBACK_CTL, request, response);
			return;
		}
		log.debug("ServiceCtl  doPost method end");
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBSView.FEEDBACK_VIEW;
	}

}
