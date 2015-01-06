package com.epam.xsltwebapp.presentation;

import static com.epam.xsltwebapp.util.Constants.CATEGORY_PARAM;
import static com.epam.xsltwebapp.util.Constants.COLOR_PARAM;
import static com.epam.xsltwebapp.util.Constants.DATE_OF_ISSUE_PARAM;
import static com.epam.xsltwebapp.util.Constants.ERROR_LOCK_WAITING;
import static com.epam.xsltwebapp.util.Constants.LOCK_WAITING_TIME;
import static com.epam.xsltwebapp.util.Constants.MODEL_PARAM;
import static com.epam.xsltwebapp.util.Constants.NAME_PARAM;
import static com.epam.xsltwebapp.util.Constants.PRICE_PARAM;
import static com.epam.xsltwebapp.util.Constants.PROVIDER_PARAM;
import static com.epam.xsltwebapp.util.Constants.SUBCATEGORY_PARAM;
import static com.epam.xsltwebapp.util.Constants.URL_ADD_PRODUCT_PAGE;
import static com.epam.xsltwebapp.util.Constants.URL_CATEGORIES_PAGE;
import static com.epam.xsltwebapp.util.Constants.URL_ERROR;
import static com.epam.xsltwebapp.util.Constants.URL_PRODUCTS_PAGE;
import static com.epam.xsltwebapp.util.Constants.URL_SAVE_PRODUCT_PAGE;
import static com.epam.xsltwebapp.util.Constants.URL_SUBCATEGORIES_PAGE;
import static com.epam.xsltwebapp.util.Constants.VIEW_ERROR;
import static com.epam.xsltwebapp.util.Constants.XML_FILE_RESOURCE;
import static com.epam.xsltwebapp.util.Constants.XSL_ADD_PRODUCT_PAGE_RESOURCE;
import static com.epam.xsltwebapp.util.Constants.XSL_CATEGORIES_PAGE_RESOURCE;
import static com.epam.xsltwebapp.util.Constants.XSL_PRODUCTS_PAGE_RESOURCE;
import static com.epam.xsltwebapp.util.Constants.XSL_SAVE_PRODUCT_PAGE_RESOURCE;
import static com.epam.xsltwebapp.util.Constants.XSL_SUBCATEGORIES_PAGE_RESOURCE;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.xsltwebapp.exception.PresentationException;

/**
 * The XSLTWebAppController class is a Spring Web MVC controller, in methods of which we form response with
 * the help of the XSLT transformation.
 * @author Yuliya_Shydlouskaya
 */
@Controller
public class XSLTWebAppController {
	
	/** The logger for the class.*/
	private static final Logger LOGGER = LogManager.getLogger(XSLTWebAppController.class);
	
	/** The read/write lock for the xml file.*/
	private static final ReadWriteLock XML_FILE_LOCK = new ReentrantReadWriteLock(true);
	private static final Lock READ_XML_FILE_LOCK = XML_FILE_LOCK.readLock();
	private static final Lock WRITE_XML_FILE_LOCK = XML_FILE_LOCK.writeLock();
	
	/**
	 * Shows the categories page.
	 * @param response
	 * @throws PresentationException 
	 * @throws IOException
	 */
	@RequestMapping(value=URL_CATEGORIES_PAGE)
	public void showCategories(HttpServletResponse response) throws PresentationException{
		transformXmlIntoResponse(response, XSL_CATEGORIES_PAGE_RESOURCE, null);
	}
	
	/**
	 * Shows the subcategories page of the certain category.
	 * @param category
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value=URL_SUBCATEGORIES_PAGE)
	public void showSubcategories(HttpServletResponse response, @PathVariable(CATEGORY_PARAM) String category) throws PresentationException{	
		Map<String, String> params = new HashMap<String, String>();
		params.put(CATEGORY_PARAM, category);
		transformXmlIntoResponse(response, XSL_SUBCATEGORIES_PAGE_RESOURCE, params);	   
	}
	
	/**
	 * Shows the products page of the certain category and subcategory.
	 * @param response
	 * @param category
	 * @param subcategory
	 * @throws IOException
	 */
	@RequestMapping(value=URL_PRODUCTS_PAGE)
	public void showProducts(HttpServletResponse response, @PathVariable(CATEGORY_PARAM) String category, 
							 @PathVariable(SUBCATEGORY_PARAM) String subcategory ) throws PresentationException{	
		Map<String, String> params = new HashMap<String, String>();
		params.put(CATEGORY_PARAM, category);
		params.put(SUBCATEGORY_PARAM, subcategory);
		transformXmlIntoResponse(response, XSL_PRODUCTS_PAGE_RESOURCE, params);	              
	}
	
	/**
	 * Shows the page of the product addition.
	 * @param response
	 * @param category
	 * @param subcategory
	 * @throws IOException
	 */
	@RequestMapping(value=URL_ADD_PRODUCT_PAGE)
	public void addProduct(HttpServletResponse response, @PathVariable(CATEGORY_PARAM) String category, 
						@PathVariable(SUBCATEGORY_PARAM) String subcategory) throws PresentationException{	
		Map<String, String> params = new HashMap<String, String>();
		params.put(CATEGORY_PARAM, category);
		params.put(SUBCATEGORY_PARAM, subcategory);
		transformXmlIntoResponse(response, XSL_ADD_PRODUCT_PAGE_RESOURCE, params);		
	}
	
	/**
	 * Saves the product to the xml file.
	 * @param category
	 * @param subcategory
	 * @param name
	 * @param provider
	 * @param model
	 * @param date_of_issue
	 * @param color
	 * @param price
	 * @return next page
	 * @throws PresentationException 
	 */
	@RequestMapping(value=URL_SAVE_PRODUCT_PAGE, method=RequestMethod.POST)
	public String saveProduct(
			@PathVariable(CATEGORY_PARAM) String category, @PathVariable(SUBCATEGORY_PARAM) String subcategory, 
			@RequestParam(NAME_PARAM) String name, @RequestParam(PROVIDER_PARAM) String provider, 
			@RequestParam(MODEL_PARAM) String model, @RequestParam(DATE_OF_ISSUE_PARAM) String date_of_issue, 
			@RequestParam(COLOR_PARAM) String color, @RequestParam(PRICE_PARAM) String price) throws PresentationException{		
		try {
			if(!WRITE_XML_FILE_LOCK.tryLock(LOCK_WAITING_TIME, TimeUnit.SECONDS)){	
				throw new PresentationException(ERROR_LOCK_WAITING);
			}
		}catch(InterruptedException e){
			LOGGER.error(e);
			throw new PresentationException();
		}

		try{
			byte[] fileBytesArray;
			try(FileInputStream inputStream = new FileInputStream(XML_FILE_RESOURCE.getFile())){
				fileBytesArray = new byte[(int) XML_FILE_RESOURCE.getFile().length()]; 
				inputStream.read(fileBytesArray);
			}				
			try (PrintWriter xmlFileWriter = new PrintWriter(XML_FILE_RESOURCE.getFile());
				 Reader xmlFilereader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(fileBytesArray)))){																 				  				
				Map<String, String> params = new HashMap<String, String>();
				params.put(CATEGORY_PARAM, category);
				params.put(SUBCATEGORY_PARAM, subcategory);
				params.put(NAME_PARAM, name);
				params.put(PROVIDER_PARAM, provider);
				params.put(MODEL_PARAM, model);
				params.put(DATE_OF_ISSUE_PARAM, date_of_issue);
				params.put(COLOR_PARAM, color);
				params.put(PRICE_PARAM, (price == null) ? "": price);
				Transformer transformer = createTransformer(XSL_SAVE_PRODUCT_PAGE_RESOURCE.getFile(), params);					
				Source xmlSource = new StreamSource(xmlFilereader);
				transformer.transform(xmlSource, new StreamResult(xmlFileWriter));							
				return String.format("redirect:%s", URL_PRODUCTS_PAGE);				
			}			
		}catch(IOException|TransformerException e){
			LOGGER.error(e);
			throw new PresentationException();
		}finally{
			WRITE_XML_FILE_LOCK.unlock();
		}
	}
	
	/**
	 * Transforms the xml right to the response output.
	 * @param response
	 * @param xsl
	 * @param params
	 * @throws PresentationException
	 */
	private void transformXmlIntoResponse(HttpServletResponse response, Resource xsl,
														Map<String, String> params) throws PresentationException{	
		try (PrintWriter writer = response.getWriter()) {
			if(READ_XML_FILE_LOCK.tryLock(LOCK_WAITING_TIME, TimeUnit.SECONDS)){
				try {
					Transformer transformer = createTransformer(xsl.getFile(), params);
					Source xml = new StreamSource(XML_FILE_RESOURCE.getFile());	
					transformer.transform(xml, new StreamResult(writer));	
				} catch (IOException|TransformerException e) {
					LOGGER.error(e);
					throw new PresentationException();
				}finally{
					READ_XML_FILE_LOCK.unlock();
				}
			} else {
				throw new PresentationException(ERROR_LOCK_WAITING);
			}
		} catch (IOException | InterruptedException e) {
			LOGGER.error(e);
			throw new PresentationException(e);
		}     
	}
	
	/**
	 * Creates Transformer object for the given xsl file.
	 * @param xslFile
	 * @return Transformer
	 * @throws TransformerConfigurationException
	 */
	private Transformer createTransformer(File xslFile, Map<String, String> params) throws TransformerConfigurationException{
		Source xsl = new StreamSource(xslFile);
		TransformerFactory factory = TransformerFactory.newInstance();	 
		Transformer transformer = factory.newTransformer(xsl);		
		if(params != null){
			for (Map.Entry<String, String> entry : params.entrySet()){
			    transformer.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return transformer;		
	}
	
	/**
	 * Handles http response and other errors.
	 * @return error view
	 */
	@RequestMapping(URL_ERROR)
	public ModelAndView handleErrors(){
		ModelAndView model = new ModelAndView(VIEW_ERROR);
		return model;
	}

}
