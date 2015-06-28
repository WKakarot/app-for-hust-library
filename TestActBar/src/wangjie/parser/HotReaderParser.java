package wangjie.parser;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import wangjie.infotypes.BasicPageType;
import wangjie.infotypes.BasicReaderType;
import wangjie.infotypes.HotReaderInfo;

public class HotReaderParser extends PageParser{

	public HotReaderParser(String content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BasicPageType parseContent() {
        HotReaderInfo pageInfo = new HotReaderInfo();
        ArrayList<BasicReaderType> readers = new ArrayList<BasicReaderType>();        
        Elements eles = doc.select("tr");
        
        for (Element e : eles) {
        	Elements tmp = e.select("span");
        	     	
        	BasicReaderType reader = new BasicReaderType(tmp.get(1).text(), tmp.get(2).text(), tmp.get(4).text());
        	readers.add(reader);
        	if (readers.size() >= 30)
        		break;
        }
        
        readers.remove(0);
        pageInfo.setReaders(readers);
        return pageInfo;		

	}

}
