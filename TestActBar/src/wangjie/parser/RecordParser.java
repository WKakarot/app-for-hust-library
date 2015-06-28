package wangjie.parser;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;

public class RecordParser extends PageParser {

	public RecordParser(String content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BasicPageType parseContent() {
        BasicPageType ret = new BasicPageType();
        ArrayList<BasicLinkType> links = new ArrayList<BasicLinkType>();
    	
    	Elements ths = doc.select("th");
    	for (Element t : ths) {
    		if (t.attr("class").compareTo("patFuncTitle") == 0)
                ret.setTitle(t.text());
    	}   	
        
    	Elements books = doc.select("a[href]");
    	for (Element b : books) {
    		if (b.parent().tagName().compareTo("td") == 0) {
    		    BasicLinkType link = new BasicLinkType(b.attr("href"), b.text());
    		    links.add(link);
    		}   			
    	}      

    	ret.setLinkList(links);
		return ret;
	}

}
