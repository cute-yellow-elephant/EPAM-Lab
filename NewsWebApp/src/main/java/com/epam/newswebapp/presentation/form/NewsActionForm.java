package com.epam.newswebapp.presentation.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.epam.newswebapp.model.News;

/**
 * The NewsActionForm class is a facade for java-bean {@link News} and the list of a its objects, read from the
 * database. Extends {@link ActionForm}.
 */
public class NewsActionForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	/** Java-bean {@link News} object */
	private News newsMessage;
	/** List of {@link News} objects from the database. */
	private List<News> newsList;
	/** List of selected news */
	private Integer[] selectedNews;
	
	/**
	 * Validates news form - checks the empty fields existence, date pattern matching the 
	 * current locale and fields length.
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		 
	    if( newsMessage.getTitle() == null || (newsMessage.getTitle().isEmpty())) {
	       errors.add("title.error", new ActionMessage("error.form.required"));
	    }
	    else{
	    	if(newsMessage.getTitle().length() > 100){
	    		errors.add("title.error", new ActionMessage("error.form.title.wrong"));
	    	}
	    }    
	    	  	    
	    if(newsMessage.getDate() == null){
	    	errors.add("date.error", new ActionMessage("error.form.required"));
	    }
	    
	    if( newsMessage.getBrief() == null || (newsMessage.getBrief().isEmpty())) {
	       errors.add("brief.error", new ActionMessage("error.form.required"));
		}
	    else{
	    	if(newsMessage.getBrief().length() > 500){
	    		errors.add("brief.error", new ActionMessage("error.form.brief.wrong"));
	    	}
	    } 
	    
	    if( newsMessage.getContent() == null || (newsMessage.getContent().isEmpty())) {
	    	errors.add("content.error", new ActionMessage("error.form.required"));
		}
	    else{
	    	if(newsMessage.getContent().length() > 2048){
	    		errors.add("content.error", new ActionMessage("error.form.content.wrong"));
	    	}
	    } 
	 
		return errors;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	
	public News getNewsMessage() {
		return newsMessage;
	}

	public void setNewsMessage(News news) {
		this.newsMessage = news;
	}

	public Integer[] getSelectedNews() {
		return selectedNews;
	}

	public void setSelectedNews(Integer[] selectedNews) {
		this.selectedNews = selectedNews;
	}
	

}
