package com.epam.newswebapp.presentation.action;

import static com.epam.newswebapp.util.Constants.CANCEL_PAGE_PARAM;
import static com.epam.newswebapp.util.Constants.FAILURE_PAGE;
import static com.epam.newswebapp.util.Constants.ID_PARAM;
import static com.epam.newswebapp.util.Constants.LANGUAGE_PARAM_ID;
import static com.epam.newswebapp.util.Constants.NEWSLIST_PAGE;
import static com.epam.newswebapp.util.Constants.NEWSVIEW_PAGE;
import static com.epam.newswebapp.util.Constants.PAGE_PARAM_ID;
import static com.epam.newswebapp.util.Constants.SUCCESS_PAGE;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.MappingDispatchAction;

import com.epam.newswebapp.exception.ServiceException;
import com.epam.newswebapp.model.News;
import com.epam.newswebapp.presentation.form.NewsActionForm;
import com.epam.newswebapp.service.INewsService;
import com.epam.newswebapp.util.LocaleHelper;

/**
 * The NewsWebAppController class is the front controller of the application. Extends MappingDispatchAction.
 */
public class NewsWebAppController extends MappingDispatchAction{
	
	private final static Logger LOGGER = LogManager.getLogger(NewsWebAppController.class);	
	
	/** The object, implementing the {@link INewsService} container. 
	 * Is injected by Spring using spring-module.xml.
	 * */
	private INewsService newsService;
	
	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	
	/**
	 * Localizes the application on the base of the incoming parameter 'lang'.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward localize(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		
		Locale locale = LocaleHelper.identifyLocale(request.getParameter(LANGUAGE_PARAM_ID));			
		if(!LocaleHelper.getCurrentLocale(request).equals(locale)){
			LocaleHelper.setCurrentLocale(request, locale);
		}
		String returnPath = request.getParameter(PAGE_PARAM_ID);
		if(StringUtils.isBlank(returnPath)){ 
			return mapping.findForward(FAILURE_PAGE); 
		}
		returnPath = returnPath.replace(request.getContextPath(), "");
		ActionRedirect redirect = new ActionRedirect(returnPath);
		String cancel = request.getParameter(CANCEL_PAGE_PARAM);
		if(StringUtils.isNotBlank(cancel)){
			redirect.addParameter(CANCEL_PAGE_PARAM, cancel);
		}		   
		return redirect;	
	}	
	
	/**
	 * Forwards us to the edit-news.jsp with empty newsMessage.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward to edit-news.jsp - if everything is fine, to error.jsp - otherwise. 
	 */
	public ActionForward add(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		
		NewsActionForm newsForm = (NewsActionForm) form;
		newsForm.setNewsMessage(new News("", new Date(), "", ""));
		return mapping.findForward(SUCCESS_PAGE);		
	}
	
	/**
	 * Saves the News object to the database.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward to view-news.jsp - if everything is fine, to edit-news.jsp - otherwise. 
	 */
	public ActionForward save(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		
		News news = ((NewsActionForm) form).getNewsMessage();
		try { 
			Integer id = getNewsService().save(news);
			news.setId(id);
			LOGGER.info(String.format("The news object has been saved: %s", news));
			return mapping.findForward(SUCCESS_PAGE);						
		} catch (ServiceException e) {	
			LOGGER.error(e.getMessage());
			populateRequestWithOperationFailedMessage(request);
			return mapping.findForward(FAILURE_PAGE);	
		}		
	}
	
	/**
	 * Forwards us to the edit-news.jsp with filled in newsMessage.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward to edit-news.jsp - if everything is fine, to /show-news.do - otherwise. 
	 */
	public ActionForward edit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		
		String cancel = request.getParameter(CANCEL_PAGE_PARAM);
		if(StringUtils.isNotBlank(cancel) ){
			request.setAttribute(CANCEL_PAGE_PARAM, cancel);
		}
		return commonViewAndEdit(mapping, form, request, response);				
	}
	
	/**
	 * Forwards us to the view-news.jsp with filled in newsMessage.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward to edit-news.jsp - if everything is fine, to /show-news.do - otherwise. 
	 */
	public ActionForward view(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		
		return commonViewAndEdit(mapping, form, request, response);			
	}
	
	/**
	 * Performs the same actions instead of edit and view methods.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward commonViewAndEdit(ActionMapping mapping,ActionForm form,
			 HttpServletRequest request,HttpServletResponse response) {
		
		NewsActionForm newsForm = (NewsActionForm) form;
		try {			
			String newsIdString = request.getParameter(ID_PARAM);
			Integer id = (StringUtils.isNotBlank(newsIdString)
							?  Integer.parseInt(newsIdString)
							: newsForm.getNewsMessage().getId());
			if((newsForm.getNewsMessage().getId() != id )){
				News news = getNewsService().fetchById(id);
				newsForm.setNewsMessage(news);
			}
			return mapping.findForward(SUCCESS_PAGE);	
		} catch (ServiceException|NumberFormatException e) {
			LOGGER.error(e.getMessage());
			populateRequestWithOperationFailedMessage(request);
			return mapping.findForward(FAILURE_PAGE);
		}				
	}
	
	/**
	 * Cancels the editing or adding of a piece of news and goes back to the appropriate page.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		
		String cancel = request.getParameter(CANCEL_PAGE_PARAM);
		if(StringUtils.isNotBlank(cancel) && (cancel.equals(NEWSVIEW_PAGE))){
			return mapping.findForward(NEWSVIEW_PAGE);
		}
		return mapping.findForward(NEWSLIST_PAGE);		
	}
	
	/**
	 * Deletes the selected news by ids.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delete(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		
		NewsActionForm newsForm = (NewsActionForm) form;
		try {
			List<Integer> list = Arrays.asList(newsForm.getSelectedNews());
			getNewsService().remove(list);
			LOGGER.info(String.format("The news objects have been deleted: %s", list));
			return mapping.findForward(SUCCESS_PAGE);
		} catch (ServiceException|NumberFormatException|NullPointerException e) {
			LOGGER.error(e.getMessage());
			populateRequestWithOperationFailedMessage(request);
			return mapping.findForward(FAILURE_PAGE);
		}	
	}
	
	/**
	 * Forwards us to the newslist.jsp with filled in newsList.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward list(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {	
		
		NewsActionForm newsForm = (NewsActionForm) form;
		newsForm.setNewsMessage(new News());
		try {
			List<News> list = getNewsService().getList();
			newsForm.setNewsList(list);
			return mapping.findForward(SUCCESS_PAGE);	
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
			populateRequestWithOperationFailedMessage(request);
			return mapping.findForward(FAILURE_PAGE);
		}	
	}
	
	/**
	 * Sets the ActionMessages error message "Operation failed" in the request.
	 * @param request
	 */
	private void populateRequestWithOperationFailedMessage(HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		errors.add("operation_failed", new ActionMessage("error.operation.failed"));
		saveErrors(request, errors);
	}	

}
