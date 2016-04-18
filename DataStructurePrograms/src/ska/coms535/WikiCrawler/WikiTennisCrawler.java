package ska.coms535.WikiCrawler;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Shubham and Swatie
 *
 */
public class WikiTennisCrawler {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		long begintime = System.currentTimeMillis();

		//initialize and run crawler
		String[] topics = {"tennis", "grand slam"};
		WikiCrawler wikiCrawler = new WikiCrawler("/wiki/Tennis", topics, 100, "WikiTennisGraph.txt");
		wikiCrawler.crawl();
		
		//output total time used
		System.out.println("Total Time:"+(System.currentTimeMillis()-begintime)/1000 +" sec");
		
	}
}
