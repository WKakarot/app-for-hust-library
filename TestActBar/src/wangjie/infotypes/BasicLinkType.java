package wangjie.infotypes;

public class BasicLinkType {
    private String url, text;
    
    public BasicLinkType(String u, String t) {
    	url = u;
    	text = t;
    }
    
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
      
}
