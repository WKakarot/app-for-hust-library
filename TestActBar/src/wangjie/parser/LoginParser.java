package wangjie.parser;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;
import wangjie.infotypes.LoginInfo;

public class LoginParser extends PageParser {

	public LoginParser(String content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BasicPageType parseContent() {
        LoginInfo info = new LoginInfo();
        
    	Elements fail = doc.select("em");
    	if (fail.size() != 0) {
    	    for (Element f : fail) {
    		    info.setName(f.text());
    	    }
    	    info.setSuccess(false);
    	}
    	else  {
    	    info.setSuccess(true);
    	    Elements personInfo = doc.select("div[class]");
    	    for (Element p : personInfo) {
    	    	if (p.attr("class").compareTo("patNameAddress") == 0) {
    	    		info.setName(p.child(0).text());
    	    		info.setDateNo(p.text());
    	    	}
    	    }
    	    
    	    ArrayList<BasicLinkType> links = new ArrayList<BasicLinkType>();
        	Elements eles = doc.select("a[href]");
        	for (Element e : eles) {
        		Element p = e.parent();
        		if (p.tagName().compareTo("li") == 0) {
                    BasicLinkType link = new BasicLinkType(e.attr("href"), e.text());
                    links.add(link);
        		}
        		if (links.size() >= 2)
        			break;
        	}
        	System.out.println("WJ------>get " + links.size() + " links");
    	    info.setLinkList(links);  	    
    	}
    	
		return info;
	}

}
