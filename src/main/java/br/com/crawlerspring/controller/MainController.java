/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crawlerspring.controller;

import br.com.crawlerspring.dao.JdbcDocumentDao;
import br.com.crawlerspring.model.CrawlerSpring;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**     
 *
 * @author marcelo
 */
@Controller
public class MainController {
    
    private JdbcDocumentDao documentDao = new JdbcDocumentDao();
    
    @RequestMapping(Routes.main)
    public String actMain() throws Exception{
        documentDao.emptyDocuments();
        
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
                config.setCrawlStorageFolder(crawlStorageFolder);

                /*
                 * Instantiate the controller for this crawl.
                 */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

                /*
                 * For each crawl, you need to add some seed urls. These are the first
                 * URLs that are fetched and then the crawler starts following links
                 * which are found in these pages
                 */
        controller.addSeed("http://www.ics.uci.edu/~welling/");
        controller.addSeed("http://www.ics.uci.edu/~lopes/");
        controller.addSeed("http://www.ics.uci.edu/");

                /*
                 * Start the crawl. This is a blocking operation, meaning that your code
                 * will reach the line after this only when crawling is finished.
                 */
        controller.start(CrawlerSpring.class, numberOfCrawlers);  
        
        return Routes.mainShow;
    }
    
}
