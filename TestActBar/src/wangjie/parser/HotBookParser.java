package wangjie.parser;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;

public class HotBookParser extends PageParser{

	public HotBookParser(String content) {
		super(content);

	}

	@Override
	public BasicPageType parseContent() {
		BasicPageType pageInfo = new BasicPageType();
    	ArrayList<BasicLinkType> ret = new ArrayList<BasicLinkType>();
    	
	    Elements eles = doc.select("a[href]");
	    
	    for (Element e : eles) {
	    	if (e.parent().tagName().equals("td")) {
                BasicLinkType link = new BasicLinkType(e.attr("href"), e.text());
                ret.add(link);
                if (ret.size() >= 50) {
                	break;
                }
	    	}
	    }
        ret.remove(0);
        pageInfo.setLinkList(ret);
		return pageInfo;
	}

	
}
