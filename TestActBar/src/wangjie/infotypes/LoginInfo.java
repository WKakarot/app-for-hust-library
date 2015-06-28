package wangjie.infotypes;

public class LoginInfo extends BasicPageType {
    private boolean success;
    private String name;
    private String validDate;
    private String currBooks;
    private String recordUrl;
    
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String getCurrBooks() {
		return currBooks;
	}
	public void setCurrBooks(String currBooks) {
		this.currBooks = currBooks;
	}
    
    public void setDateNo(String str) {
        int idx1 = name.length();
        int idx2 = " 有效期限:2014-06-30".length();
        setValidDate(str.substring(idx1, idx1+idx2));
        setCurrBooks(str.substring(idx1+idx2));
    }
	public String getRecordUrl() {
		return recordUrl;
	}
	public void setRecordUrl(String recordUrl) {
		this.recordUrl = recordUrl;
	}
    
    
}
