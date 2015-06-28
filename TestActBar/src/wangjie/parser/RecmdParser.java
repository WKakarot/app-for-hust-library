package wangjie.parser;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;
import wangjie.infotypes.RecmdPageInfo;

public class RecmdParser extends PageParser {

	public RecmdParser(String content) {
		super(content);
		
	}

	public BasicPageType parseContent() {
		RecmdPageInfo pageInfo = new RecmdPageInfo();
    	ArrayList<BasicLinkType> ret = new ArrayList<BasicLinkType>();
    	
		Elements eles = doc.select("a[href]");
		
		for (Element link : eles) {
			Element pare = link.parent();
			if (pare.tagName().equals("div") && !pare.hasAttr("id") && !pare.hasAttr("class")) {
                 BasicLinkType lt = new BasicLinkType(link.attr("href"), link.text());
                 ret.add(lt);
			}
		}

		pageInfo.setLinkList(ret);
		pageInfo.setTitle("ÌØ±ðÍÆ¼ö");
		
		/* get the book rank info */
		ArrayList<BasicLinkType> times = new ArrayList<BasicLinkType>();
		Elements ts = doc.select("option");
		for (Element t : ts) {
			BasicLinkType time = new BasicLinkType(t.attr("value"), t.text());
			times.add(time);
		}
		pageInfo.setBookRank(times);
		
    	return pageInfo;   	
	}
	
	public ArrayList<BasicLinkType> parseReader() {
		
		ArrayList<BasicLinkType> times = new ArrayList<BasicLinkType>();
		Elements ts = doc.select("option");
		for (Element t : ts) {
			BasicLinkType time = new BasicLinkType(t.attr("value"), t.text());
			times.add(time);
		}
		
    	return times; 
	}
}
