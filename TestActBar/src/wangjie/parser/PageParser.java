package wangjie.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import wangjie.infotypes.BasicPageType;

public abstract class PageParser {

	protected Document doc;
	
	public PageParser(String content) {
		doc = Jsoup.parse(content);
	}
	
	abstract public BasicPageType parseContent();
}
