package dev.mvc.mlogin;
//CREATE TABLE mlogin(
//mloginno                    NUMBER(10) NOT NULL PRIMARY KEY,
//memno                NUMBER(10) NOT NULL,
//ip                            VARCHAR2(15) NOT NULL,
//logindate                  DATE NOT NULL,
//FOREIGN KEY (memno) REFERENCES mem (memno)
//);
public class MloginVO {
	private int mloginno;
	private int memno;
	private String ip;
	private String logindate;
	
	public int getMloginno() {
		return mloginno;
	}
	public void setMloginno(int mloginno) {
		this.mloginno = mloginno;
	}
	public int getMemno() {
		return memno;
	}
	public void setMemno(int memno) {
		this.memno = memno;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLogindate() {
		return logindate;
	}
	public void setLogindate(String logindate) {
		this.logindate = logindate;
	}
	
	@Override
	public String toString() {
		return "MloginVO [mloginno=" + mloginno + ", memno=" + memno + ", ip=" + ip + ", logindate=" + logindate + "]";
	}
	
	
}
