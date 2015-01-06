package com.epam.jsfnews.presentation;

import static com.epam.jsfnews.util.Constants.FLAG_MAKE_REDIRECT;
import static com.epam.jsfnews.util.Constants.FLAG_SAVE_VIEW_PARAMS;
import static com.epam.jsfnews.util.Constants.NEWS_SESSION_BEAN_NAME;
import static com.epam.jsfnews.util.Constants.PAGE_EDIT_NEWS;
import static com.epam.jsfnews.util.Constants.PAGE_ERROR;
import static com.epam.jsfnews.util.Constants.PAGE_NEWS_LIST;
import static com.epam.jsfnews.util.Constants.PAGE_VIEW_NEWS;
import static com.epam.jsfnews.util.Constants.SCOPE_SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.jsfnews.exception.ServiceException;
import com.epam.jsfnews.model.News;
import com.epam.jsfnews.service.INewsService;
import com.epam.jsfnews.util.SupportedLocales;

/**
 * The <b>NewsManager</b> class is a session-scoped JSF bean, containing the news data model and some
 * logic, connected with its displaying on the app's views and CRUD.
 * @author Yuliya_Shydlouskaya
 *
 */
@Component(NEWS_SESSION_BEAN_NAME)
@Scope(SCOPE_SESSION)
public class NewsManagerBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(NewsManagerBean.class);
	
	/** Current session locale. */
	private Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	/** Current session map for rendering news removal checkboxes. */
	private Map<Integer, Boolean> selectedNews = new HashMap<Integer, Boolean>();
	/** Current session news data model. */
	private News newsMessage = new News();
	/** Current session collection of news. */
	private List<News> newsList;

	/** Previous page holder for {@link #cancel()} action. */
	private String previousPage;
	/** Current page holder for {@link #localize()} action. */
	private String currentPage;
	
	/** Spring-managed bean for performing business logic with the news data model.*/
	@Autowired
	private INewsService newsService;

	/**
	 * Initializes the list of news page with data.
	 * @return String page - newslist
	 */
	public String list() {
		try {
			newsList = newsService.getList();
			selectedNews.clear();
			for (News news : getNewsList()) {
				selectedNews.put(news.getId(), false);
			}
			return PAGE_NEWS_LIST;
		} catch (ServiceException e) {
			LOGGER.error(e);
			return PAGE_ERROR;
		}
	}

	/**
	 * Initializes the news view page with data.
	 * @return String page - view_news
	 */
	public String view() {
		try {
			newsMessage = newsService.fetchById(newsMessage.getId());
			selectedNews.clear();
			selectedNews.put(newsMessage.getId(), true);
			return PAGE_VIEW_NEWS;
		} catch (ServiceException e) {
			LOGGER.error(e);		
		}
		return PAGE_ERROR;
	}

	/**
	 * Initializes the news edit page with new data.
	 * @return String page - edit_news
	 */
	public String add() {
		newsMessage = new News();
		newsMessage.setDate(new Date());
		return new StringBuilder(PAGE_EDIT_NEWS)
					.append(FLAG_MAKE_REDIRECT)
					.toString();
	}
	
	/**
	 * Initializes the news edit page with data by id.
	 * @return String page - edit_news
	 */
	public String edit() {
		try {
			newsMessage = newsService.fetchById(newsMessage.getId());
			return new StringBuilder(PAGE_EDIT_NEWS)
						.append(FLAG_MAKE_REDIRECT)
						.append(FLAG_SAVE_VIEW_PARAMS)
						.toString();
		} catch (ServiceException e) {
			LOGGER.error(e);
			return PAGE_ERROR;
		}
	}
	
	/**
	 * Saves the news data and redirects to the news view page .
	 * @return String page - view_news
	 */
	public String save() {
		try {
			newsMessage.setId(newsService.save(newsMessage));
			return new StringBuilder(PAGE_VIEW_NEWS)
						.append(FLAG_MAKE_REDIRECT)
						.append(FLAG_SAVE_VIEW_PARAMS)
						.toString();
		} catch (ServiceException e) {
			LOGGER.error(e);
			return PAGE_ERROR;
		}
	}
	
	/**
	 * Deletes the selected news data and redirects to the news list.
	 * @return String page - newslist
	 */
	public String delete() {
		try {
			LOGGER.info(selectedNews);
			List<Integer> deleteIdArray = new ArrayList<Integer>();
			for (Entry<Integer, Boolean> possibleSelectedEntry : selectedNews.entrySet()) {
				if (possibleSelectedEntry.getValue()) {
					deleteIdArray.add(possibleSelectedEntry.getKey());
				}
			}
			newsService.remove(deleteIdArray);	
			return new StringBuilder(PAGE_NEWS_LIST)
						.append(FLAG_MAKE_REDIRECT)
						.toString();
		} catch (Exception e) {
			LOGGER.error(e);
			return PAGE_ERROR;
		}
	}
	
	/**
	 * Localizes the current page and redirects back.
	 * @return String page - currentPage
	 */
	public String localize(String localeCode) {
		currentLocale = SupportedLocales.identifyLocale(localeCode);
		return new StringBuilder(currentPage)
					.append(FLAG_MAKE_REDIRECT)
					.append(FLAG_SAVE_VIEW_PARAMS)
					.toString();
	}	
	
	/**
	 * Cancels the news edition and redirects back to the previous page.
	 * @return String previousPage
	 */
	public String cancel(){
		return new StringBuilder(previousPage)
					.append(FLAG_MAKE_REDIRECT)
					.toString();
	}
	
	public News getNewsMessage() {
		return newsMessage;
	}
	
	public void setNewsMessage(News newsMessage) {
		this.newsMessage = newsMessage;
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	
	public Map<Integer, Boolean> getSelectedIds() {  
        return selectedNews;  
    }  
   
    public void setSelectedIds(Map<Integer, Boolean> selectedIds) {  
        this.selectedNews = selectedIds;  
    }

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public String getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
}
