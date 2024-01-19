package controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CrawlingListner implements ServletContextListener {

    public CrawlingListner() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	Crawling crawling = new Crawling();
    	crawling.crawling();
    }
	
}
