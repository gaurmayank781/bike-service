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
import in.co.online.bike.service.bean.FeedBackBean;
import in.co.online.bike.service.bean.ServiceBean;
import in.co.online.bike.service.exception.ApplicationException;
import in.co.online.bike.service.model.FeedBackModel;
import in.co.online.bike.service.model.ServiceModel;
import in.co.online.bike.service.util.DataUtility;
import in.co.online.bike.service.util.PropertyReader;
import in.co.online.bike.service.util.ServletUtility;

/**
 * Servlet implementation class FeedBackListCtl
 */
@WebServlet(name = "FeedBackListCtl", urlPatterns = { "/ctl/FeedBackListCtl" })
public class FeedBackListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
private final Logger log = Logger.getLogger(FeedBackListCtl.class);
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("FeedBackListCtl  populateBean method start");
		FeedBackBean bean = new FeedBackBean();

		bean.setUserName(DataUtility.getString(request.getParameter("name")));
		log.debug("FeedBackListCtl  populateBean method end");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FeedBackListCtl  doGet method start");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		FeedBackBean bean = (FeedBackBean) populateBean(request);
		FeedBackModel model = new FeedBackModel();

		List list = null;

		try {
			list = model.search(bean, pageNo, pageSize);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record found ", request);
		}

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		
		log.debug("FeedBackListCtl  doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FeedBackListCtl  doPost method start");

		List list = null;
		String[] ids = request.getParameterValues("ids");

		System.out.println("=============================" + ids);

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		FeedBackBean bean = (FeedBackBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		FeedBackModel model = new FeedBackModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				} else if (OP_NEW.equalsIgnoreCase(op)) {
					ServletUtility.redirect(OBSView.SERVICE_CTL, request, response);
					return;
				}

			}else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					FeedBackBean deletebean = new FeedBackBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						System.out.println("===============vsafff==============vds" + deletebean.getId());
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(OBSView.FEEDBACK_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);
			/*
			 * CollegeBean be =(CollegeBean) list;
			 * System.out.println(be.getName());
			 */

			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("FeedBackListCtl  doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OBSView.FEEDBACK_LIST_VIEW;
	}

}
