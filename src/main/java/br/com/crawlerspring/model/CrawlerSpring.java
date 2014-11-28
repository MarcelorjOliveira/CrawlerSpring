/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crawlerspring.model;

import br.com.crawlerspring.dao.JdbcDocumentDao;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author marcelooliveira
 */
public class CrawlerSpring extends WebCrawler {
    private JdbcDocumentDao documentDao = new JdbcDocumentDao();
    private Document document;
    private int cont = 0;
 
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
                                                          + "|png|tiff?|mid|mp2|mp3|mp4"
                                                          + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
                                                          + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

   
    
        @Override
        public void visit(Page page) {
                document = new Document();
                
                document.setId("document_"+Integer.toString(cont));
                
                document.setTitle("document_"+Integer.toString(cont));

                document.setAuthor("crawler Spring");
                
                Calendar ca = new GregorianCalendar();
                
                document.setDate( new java.sql.Date(ca.getTimeInMillis()) );
                
                document.setLink(page.getWebURL().getURL());
                
                document.setSource("webPage");
                
                if (page.getParseData() instanceof HtmlParseData) {
                        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                        document.setContent(htmlParseData.getText());
        
                }
                documentDao.createDocument(document);
                cont++;
        }
}
