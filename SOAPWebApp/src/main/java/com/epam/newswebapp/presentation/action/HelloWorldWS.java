package com.epam.newswebapp.presentation.action;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.epam.newswebapp.exception.ServiceException;
import com.epam.newswebapp.model.News;
import com.epam.newswebapp.service.INewsService;

@WebService(name="HelloService", targetNamespace="http://soapwebapp.com/hello")
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public class HelloWorldWS {
	
	private INewsService newsService;
	
	@WebMethod(operationName="sayHello")
	@WebResult(name="hello")
	public String sayHello(@WebParam(name="name") String name) {
		return "Hello!" + name;
	}
	
	@WebMethod(operationName="getNews")
	@WebResult(name="news")
	public News getNews(@WebParam(name="news") News news) {
		System.out.println(news);
		return news;
	}
	
	@WebMethod(operationName="getList")
	@WebResult(name="newslist")
	public List<News> getList() {
		System.out.println("in get list");
		try {
			return newsService.getList();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

}
