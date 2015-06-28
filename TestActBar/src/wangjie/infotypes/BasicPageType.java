package wangjie.infotypes;

import java.util.ArrayList;

public class BasicPageType {
    private String title;
    private ArrayList<BasicLinkType> linkList;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<BasicLinkType> getLinkList() {
		return linkList;
	}
	public void setLinkList(ArrayList<BasicLinkType> linkList) {
		this.linkList = linkList;
	}
    
    
}
