package wangjie.parser;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;

public class UniPageParser extends PageParser{

	public UniPageParser(String content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BasicPageType parseContent() {
		// TODO Auto-generated method stub
		return null;
	}

    public  BasicPageType parseUni(String content, String filename) {
    	BasicPageType ret = null;
    	if (filename.compareTo("/screens/readday.html") == 0)
    		ret = parseReadday(content);
    	else if (filename.compareTo("/cet4.html") == 0 || filename.compareTo("/cet6.html") == 0)
    		ret = parseCet(content);
    	else if (filename.compareTo("/renwen.html") == 0)
    		ret = parseRenwen(content);
    	else if (filename.compareTo("/screens/edurecommend.html") == 0)
    		ret = parseEdure(content);
    	else if (filename.compareTo("/screens/springerebook.html") == 0)
    		ret = parseSpring(content);
    	return ret;
    }	
    
    private  BasicPageType parseReadday(String content) {
    	BasicPageType ret = new BasicPageType();
    	ArrayList<BasicLinkType> links = new ArrayList<BasicLinkType>();
    	Elements eles = doc.select("tr[valign]");
    	
    	for (Element e : eles) {
    	    Elements u = e.select("a");
    	    String text = null;
    	    Elements t = e.select("span");
    	    if (t.isEmpty()) {
    	    	Elements bt = e.select("strong");
    	        text = bt.get(0).text();
    	    }
    	    else {
    	    	text = t.get(0).text();
    	    }  	    
    	    BasicLinkType link = new BasicLinkType(u.get(0).attr("href"), text);
    	    links.add(link);
    	}

    	ret.setLinkList(links);
    	return ret;
    } 
    
    private BasicPageType parseCet(String content) {
    	BasicPageType ret = new BasicPageType();
    	ArrayList<BasicLinkType> links = new ArrayList<BasicLinkType>();
    	Elements eles = doc.select("a[href]");
    	for (Element e : eles) {
    		if (e.parent().tagName().compareTo("span") == 0 && !e.text().isEmpty()) { 
    			BasicLinkType link = new BasicLinkType(e.attr("href"), e.text());
    	        links.add(link);
    		}
    	}
        ret.setLinkList(links);
    	return ret;
    }   
    
    
    private  BasicPageType parseRenwen(String content) {
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
    
    private BasicPageType parseEdure(String content) {
    	BasicPageType ret = new BasicPageType();
    	ArrayList<BasicLinkType> links = new ArrayList<BasicLinkType>();
    	Elements eles = doc.select("a[href]");
    	for (Element e : eles) {
    		Element p = e.parent();
    		if (p.tagName().compareTo("span") == 0) {
    			 BasicLinkType link = new BasicLinkType(e.attr("href"), e.text());
    	         links.add(link);    			
    		}
    	}
    	ret.setLinkList(links);
    	return ret;
    }
    
    private BasicPageType parseSpring(String content) {
    	BasicPageType ret = new BasicPageType();
    	ArrayList<BasicLinkType> links = new ArrayList<BasicLinkType>();
    	Elements eles = doc.select("a[href]");
    	int i = 0;
    	for (Element e : eles) {
    		Element p = e.parent();
    		if (p.tagName().compareTo("td") == 0) {
    		    i++;
    		    if (i > 25) {
       			    BasicLinkType link = new BasicLinkType(e.attr("href"), e.text());
    	            links.add(link);   		    	
    		    }
    		}
    	}
    	ret.setLinkList(links);  		
    	return ret;
    }
}
