package wangjie.infotypes;

import java.util.ArrayList;

public class RecmdPageInfo extends BasicPageType{
    private ArrayList<BasicLinkType> bookRank;
    private ArrayList<String> dates;
    
	public ArrayList<BasicLinkType> getBookRank() {
		return bookRank;
	}

	public void setBookRank(ArrayList<BasicLinkType> bookRank) {
		this.bookRank = bookRank;
	}
    
	public ArrayList<String> getDates() {
        if (dates == null) {
        	dates = new ArrayList<String>();
        	for (int i = bookRank.size()-1; i >= 0; --i)
        		dates.add(bookRank.get(i).getText());
        }
        return dates;
	}
    
}
