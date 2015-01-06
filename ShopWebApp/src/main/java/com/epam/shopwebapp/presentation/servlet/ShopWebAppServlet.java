package com.epam.shopwebapp.presentation.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epam.shopwebapp.exception.UtilException;
import com.epam.shopwebapp.model.Category;
import com.epam.shopwebapp.util.constants.Constants;
import com.epam.shopwebapp.util.xml.parser.IXMLParser;
import com.epam.shopwebapp.util.xml.parser.IXMLParserFactory;

/**
 * The ShopWebAppServlet class is the Servlet 3.0 implementation.
 */
@WebServlet(name="shopWebAppServlet", urlPatterns={"/parse"})
public class ShopWebAppServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ShopWebAppServlet.class);	
	
	/**
	 * Contains the parser factory singleton object, managed by Spring.
	 */
	@Autowired
	private IXMLParserFactory<Category> parserFactory;
	
	/**
	 * Initializes the servlet to know about the Spring WebContext. 
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
        try {
        	String parserType = request.getParameter(Constants.PARSERS_PARAM);
            if(StringUtils.isNotBlank(parserType)){
            	IXMLParser<Category> parser = parserFactory.getParser(parserType);
            	ClassPathResource xmlFileResource = new ClassPathResource(Constants.XML_FILE_PATH);
            	List<Category> categories = parser.parse(xmlFileResource.getFile());
            	request.setAttribute(Constants.LIST_ATTRIBUTE, categories);
            	nextPage = Constants.LIST_PAGE_PATH;
            }
            else {
                request.setAttribute(Constants.ERROR_ATTRIBUTE, "Choose the parser, please."); 
                nextPage = Constants.MAIN_PAGE_PATH;
            }         
        } catch (UtilException e) {
            LOGGER.error(e.getMessage());
            nextPage = Constants.ERROR_PAGE_PATH;
        }
        request.getRequestDispatcher(nextPage).forward(request, response);
	}

	public IXMLParserFactory<Category> getParserFactory() {
		return parserFactory;
	}

	public void setParserFactory(IXMLParserFactory<Category> parserFactory) {
		this.parserFactory = parserFactory;
	}



}
