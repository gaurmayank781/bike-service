package in.co.online.bike.service.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.bike.service.bean.BaseBean;
import in.co.online.bike.service.bean.InvoiceBean;
import in.co.online.bike.service.bean.PartsBean;
import in.co.online.bike.service.exception.ApplicationException;
import in.co.online.bike.service.exception.DuplicateRecordException;
import in.co.online.bike.service.model.InvoiceModel;
import in.co.online.bike.service.model.PartsModel;
import in.co.online.bike.service.model.UserModel;
import in.co.online.bike.service.util.DataUtility;
import in.co.online.bike.service.util.DataValidator;
import in.co.online.bike.service.util.PropertyReader;
import in.co.online.bike.service.util.ServletUtility;

/**
 * Servlet implementation class InvoiceCtl
 */
@WebServlet(name = "InvoiceCtl", urlPatterns = { "/ctl/InvoiceCtl" })
public class InvoiceCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(PartsCtl.class);
	
	@Override
	protected void preload(HttpServletRequest request) {

		log.debug("Invoicectl preload start");
		

		try {
			UserModel model = new UserModel();
			List cl = model.list();
			System.out.println("Usr List ============"+cl);
			request.setAttribute("userlist", cl);

			PartsModel smodel = new PartsModel();
			List sl = smodel.list();
			System.out.println("Parts List ============"+sl);
			request.setAttribute("partslist", sl);

		} catch (ApplicationException e) {

			e.printStackTrace();

		}
		log.debug("Service preload end");
		
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("InvoiceCtl  validate method start");

		boolean pass = true;

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "User Name"));
			pass = false;
		} 

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("partName"))) {
			request.setAttribute("partName", PropertyReader.getValue("error.require", "Part Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("qua"))) {
			request.setAttribute("qua", PropertyReader.getValue("error.require", "Quaintity"));
			pass = false;
		} 
		log.debug("PartsCtl  validate method end");
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("InvoiceCtl  populateBean method start");

		InvoiceBean bean = new InvoiceBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setUserName(DataUtility.getString(request.getParameter("name")));

		bean.setPartName(DataUtility.getString(request.getParameter("partName")));

		bean.setQuantity(DataUtility.getLong(request.getParameter("qua")));

		bean.setPrice(DataUtility.getLong(request.getParameter("price")));

		populateDTO(bean, request);

		log.debug("InvoiceCtl  populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("InvoiceCtl  doGet method start");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		InvoiceModel model = new InvoiceModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		// ServletUtility.setOpration("Add", request);
		if (id > 0) {
			InvoiceBean bean;
			try {
				bean = model.findByPK(id);

				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		log.debug("InvoiceCtl  doGet method end");
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("InvoiceCtl  doPost method start");

		ServletUtility.setOpration("Add", request);
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		InvoiceModel model = new InvoiceModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			InvoiceBean bean = (InvoiceBean) populateBean(request);

			try {
				if (id > 0) {
					/*model.update(bean);*/
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setOpration("Edit", request);
				} else {
					bean.setAmount(bean.getPrice()*bean.getQuantity());
					
					long pk = model.add(bean);
					
					request.setAttribute("Iddd", pk);
					ServletUtility.forward(OBSView.INVOICE_DETAIL_VIEW, request, response);
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

			InvoiceBean bean = (InvoiceBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OBSView.INVOICE_LIST_CTL, request, response);

				return;

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);

				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(OBSView.INVOICE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBSView.INVOICE_CTL, request, response);
			return;
		}
		log.debug("PartsCtl  doPost method end");
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBSView.INVOICE_VIEW;
	}

}
