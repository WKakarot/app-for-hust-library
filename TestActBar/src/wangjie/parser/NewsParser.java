package wangjie.parser;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;

public class NewsParser extends PageParser {

	public NewsParser(String content) {
		super(content);
	}

	
	public BasicPageType parseContent() {
		BasicPageType pageInfo = new BasicPageType();
		Elements eles = doc.select("table[width]");
		Elements links = null;;
		for (Element t : eles) {
			if (t.attr("width").compareTo("456") == 0) {
				links = t.select("a[href]");
			}
		}
		
		ArrayList<BasicLinkType> linklist = new ArrayList<BasicLinkType>();
		if (links != null) {
			for (Element l : links) {
			    BasicLinkType link = new BasicLinkType(l.attr("href"), l.text());
			    linklist.add(link);
			}
		}
		pageInfo.setTitle("×îÐÂ¶¯Ì¬");
		pageInfo.setLinkList(linklist);
	    return pageInfo;
	}
}
