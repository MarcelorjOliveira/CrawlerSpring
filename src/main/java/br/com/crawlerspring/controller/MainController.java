/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crawlerspring.controller;

import br.com.crawlerspring.dao.JdbcDocumentDao;
import br.com.crawlerspring.model.CrawlerSpring;
import br.com.crawlerspring.model.Document;
import br.com.crawlerspring.model.Searcher;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**     
 *
 * @author marcelo
 */
@Controller
public class MainController {
    
    private JdbcDocumentDao documentDao = new JdbcDocumentDao();
    private Document document;
    private List<Document> parametrizedDocuments;
    private Searcher searcher;
    
    private void initCrawler() throws Exception{
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
                config.setCrawlStorageFolder(crawlStorageFolder);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed("http://www.ics.uci.edu/~welling/");
        controller.addSeed("http://www.ics.uci.edu/~lopes/");
        controller.addSeed("http://www.ics.uci.edu/");

        controller.start(CrawlerSpring.class, numberOfCrawlers);  
        
    }
        
    @RequestMapping(Routes.main)
    public String main() throws Exception{
        documentDao.emptyDocuments();
        
        initCrawler();
        
        searcher = new Searcher();
        
        searcher.prepareSearch();
        
        return Routes.mainShow;
    }
    
    @RequestMapping(Routes.mainAct)
    public String actMain(HttpServletRequest request, Model model) throws Exception{
        String parameters = request.getParameter("parameters");
        parametrizedDocuments = searcher.parametrizeDocuments(parameters);
        model.addAttribute("documents", parametrizedDocuments);
        
        return Routes.mainView;
    }
    
}
