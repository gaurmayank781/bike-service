package in.co.online.bike.service.controller;

public interface OBSView {
	
	public String APP_CONTEXT = "/OnlineBikeService";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	public String PARTS_VIEW = PAGE_FOLDER + "/PartsView.jsp";	
	public String PARTS_LIST_VIEW = PAGE_FOLDER + "/PartsListView.jsp";
	
	public String SERVICE_DETAIL_VIEW = PAGE_FOLDER + "/ServiceDetailView.jsp";
	
	public String SERVICE_VIEW = PAGE_FOLDER + "/ServiceView.jsp";
	public String SERVICE_LIST_VIEW = PAGE_FOLDER + "/ServiceListView.jsp";
	
	public String INVOICE_VIEW = PAGE_FOLDER + "/InvoiceView.jsp";
	public String INVOICE_LIST_VIEW = PAGE_FOLDER + "/InvoiceListView.jsp";
	
	public String FEEDBACK_VIEW = PAGE_FOLDER + "/FeedBackView.jsp";
	public String FEEDBACK_LIST_VIEW = PAGE_FOLDER + "/FeedBackListView.jsp";
		
	public String INVOICE_DETAIL_VIEW = PAGE_FOLDER + "/InvoiceDetail.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	public String INVOICE_DETAIL_CTL = APP_CONTEXT + "/ctl/InvoiceDetailCtl";
	
	public String INVOICE_CTL = APP_CONTEXT + "/ctl/InvoiceCtl";
	public String INVOICE_LIST_CTL = APP_CONTEXT + "/ctl/InvoiceListCtl";
	
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	public String PARTS_CTL = APP_CONTEXT + "/ctl/PartsCtl";
	public String PARTS_LIST_CTL = APP_CONTEXT + "/ctl/PartsListCtl";
	
	public String SERVICE_DETAIL_CTL = APP_CONTEXT + "/ServiceDetailCtl";
	
	public String SERVICE_CTL = APP_CONTEXT + "/ServiceCtl";
	public String SERVICE_LIST_CTL = APP_CONTEXT + "/ctl/ServiceListCtl";
	
	public String FEEDBACK_CTL = APP_CONTEXT + "/FeedBackCtl";
	public String FEEDBACK_LIST_CTL = APP_CONTEXT + "/ctl/FeedBackListCtl";
	
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";



}
