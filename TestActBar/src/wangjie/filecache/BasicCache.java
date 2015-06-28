package wangjie.filecache;

import java.util.ArrayList;

import wangjie.infotypes.*;

public class BasicCache {
    static private boolean recmdValid = false;
    static private boolean newsValid = false;
    static private ArrayList<BasicLinkType> reader = null;
    static private BasicPageType recmdInfo = null;
    static private BasicPageType newsInfo = null;
    static private boolean readerValid = false;
    static private ArrayList<String> readerdates = null;
    static private String userName;
    static private boolean isLogon = false;
    static private LoginInfo logonInfo = null;
    
	public static String getUserName() {
		return userName;
	}
	public static void setUserName(String userName) {
		BasicCache.userName = userName;
	}
	public static boolean isLogon() {
		return isLogon;
	}
	public static void setLogon(boolean isLogon) {
		BasicCache.isLogon = isLogon;
	}
	public static boolean isRecmdValid() {
		return recmdValid;
	}
	public static void setRecmdValid(boolean recmdValid) {
		BasicCache.recmdValid = recmdValid;
	}
	public static boolean isNewsValid() {
		return newsValid;
	}
	public static void setNewsValid(boolean newsValid) {
		BasicCache.newsValid = newsValid;
	}
	public static ArrayList<BasicLinkType> getReader() {
		return reader;
	}
	public static void setReader(ArrayList<BasicLinkType> reader) {
		BasicCache.reader = reader;
	}
	public static BasicPageType getRecmdInfo() {
		return recmdInfo;
	}
	public static void setRecmdInfo(BasicPageType recmdInfo) {
		BasicCache.recmdInfo = recmdInfo;
	}
	public static BasicPageType getNewsInfo() {
		return newsInfo;
	}
	public static void setNewsInfo(BasicPageType newsInfo) {
		BasicCache.newsInfo = newsInfo;
	}
	public static boolean isReaderValid() {
		return readerValid;
	}
	public static void setReaderValid(boolean readerValid) {
		BasicCache.readerValid = readerValid;
	}
	
	public static ArrayList<String> getReaderDates() {
		if (readerdates == null) {
			readerdates = new ArrayList<String>();
			for (int i = reader.size()-1; i >= 0; --i)
				readerdates.add(reader.get(i).getText());
		}
		return readerdates;
	}
	
	public static LoginInfo getLogonInfo() {
		return logonInfo;
	}
	public static void setLogonInfo(LoginInfo logonInfo) {
		BasicCache.logonInfo = logonInfo;
	}

	
}
