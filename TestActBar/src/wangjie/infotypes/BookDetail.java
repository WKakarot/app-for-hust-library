package wangjie.infotypes;

import java.util.ArrayList;

public class BookDetail extends BasicPageType {
	private String bibOrder;
    private String table2;
    private ArrayList<String> storeInfo;

	public String getTable2() {
		return table2;
	}

	public void setTable2(String table2) {
		this.table2 = table2;
	}

	public ArrayList<String> getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(ArrayList<String> storeInfo) {
		this.storeInfo = storeInfo;
	}

	public String getBibOrder() {
		return bibOrder;
	}

	public void setBibOrder(String bibOrder) {
		this.bibOrder = bibOrder;
	}
    
    
	
}
