package wangjie.infotypes;

public class BasicReaderType {
    private String name;
    private String school;
    private String num;
    
    public BasicReaderType(String n, String s, String no) {
    	name = n;
    	school = s;
    	num = no;
    }
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
    
    
}
