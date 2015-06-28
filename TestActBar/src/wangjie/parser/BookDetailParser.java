package wangjie.parser;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import wangjie.infotypes.BasicPageType;
import wangjie.infotypes.BookDetail;

public class BookDetailParser extends PageParser {

	public BookDetailParser(String content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BasicPageType parseContent() {
		// TODO Auto-generated method stub
		BookDetail ret = new BookDetail();
		ArrayList<String> store = null;
		Elements tables = doc.select("table[class]");
		
		String part1 = null;
		String part2 = null;
		for (int i = 0; i < tables.size(); ++i) {
			if (tables.get(i).attr("class").compareTo("bibDetail") == 0) {
				if (part1 == null) {
					part1 = getPart(tables.get(i));
				}
				else if (part2 == null) {
					part2 = getPart(tables.get(i));
				}
			}
			else if (tables.get(i).attr("class").compareTo("bibOrder") == 0) {
				ret.setBibOrder(getBibOrder(tables.get(i)));
			}
			else if (tables.get(i).attr("class").compareTo("bibItems") == 0) {
				store = getStore(tables.get(i));
			}
		}

		ret.setTitle(part1);
		ret.setTable2(part2);
		ret.setStoreInfo(store);
		return ret;
	}

	private ArrayList<String> getStore(Element element) {
		ArrayList<String> store = new ArrayList<String>();
		
		Elements tds = element.select("td");
		for (int i = 0 ; i < tds.size(); i += 3) {
			String item = tds.get(i).text() + " " + tds.get(i+1).text() + " " + tds.get(i+2).text();
			store.add(item);
		}
		return store;
	}

	private String getBibOrder(Element element) {
		String order = new String();
		order += element.text();
		return order.trim();
	}

	private String getPart(Element element) {
        String part = new String();
 
        Elements tds = element.select("td[class]");
        for (int i = 0; i < tds.size(); ++i) {
        	if (tds.get(i).attr("class").compareTo("bibInfoLabel") == 0) {
        		part += tds.get(i).text() + " :";
        	}
        	else {
        		part += " " + tds.get(i).text();
        		if ( (i+1 < tds.size()) && (tds.get(i+1).attr("class").compareTo("bibInfoLabel") == 0)) {
        			part += "\n";
        		}
        	}
        }		
        
		return part.trim();
	}

}
