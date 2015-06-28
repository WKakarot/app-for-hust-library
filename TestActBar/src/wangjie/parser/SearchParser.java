package wangjie.parser;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;

public class SearchParser extends PageParser {

	public SearchParser(String content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BasicPageType parseContent() {
		// TODO Auto-generated method stub
		BasicPageType ret = new BasicPageType();
		ArrayList<BasicLinkType> links = new ArrayList<BasicLinkType>();
		
		Elements eles = doc.select("a[href]");
		for (Element e : eles) {
			Element p = e.parent();
			if (p.tagName().compareTo("span") == 0 && p.attr("class").compareTo("briefcitTitle") == 0) {
				BasicLinkType link = new BasicLinkType(e.attr("href"), e.text());
				links.add(link);
			}
		}
		
		ret.setLinkList(links);
		return ret;
	}

}
