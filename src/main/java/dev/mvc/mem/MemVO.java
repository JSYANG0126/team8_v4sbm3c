package dev.mvc.mem;
//CREATE TABLE mem(
//		memno                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
//		ID                            		VARCHAR2(30)		 NOT NULL,
//		PASSWD                        		VARCHAR2(60)		 NOT NULL,
//		MNAME                         		VARCHAR2(30)		 NOT NULL,
//		TEL                           		VARCHAR2(14)		 NOT NULL,
//		ZIPCODE                       		VARCHAR2(5)		 NULL ,
//		ADDRESS1                      		VARCHAR2(80)		 NULL ,
//		ADDRESS2                      		VARCHAR2(50)		 NULL ,
//		MDATE                         		DATE		 NOT NULL,
//		GRADE                         		NUMBER(2)		 NOT NULL,
//		qnano                         		NUMBER(10)		 NULL,
//      PRIMARY KEY (memno)
//);
public class MemVO {
	private int memno;
	private String id = "";
    private String passwd = "";
    private String mname = "";
    private String tel = "";
    private String zipcode = "";
    private String address1 = "";
    private String address2 = "";
    private String mdate = "";
    private int grade = 0;
    
    /** 등록된 패스워드 */
    private String old_passwd = "";
    /** id 저장 여부 */
    private String id_save = "";
    /** passwd 저장 여부 */
    private String passwd_save = "";
    /** 이동할 주소 저장 */
    private String url_address = "";
	public int getMemno() {
		return memno;
	}
	public void setMemno(int memno) {
		this.memno = memno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getOld_passwd() {
		return old_passwd;
	}
	public void setOld_passwd(String old_passwd) {
		this.old_passwd = old_passwd;
	}
	public String getId_save() {
		return id_save;
	}
	public void setId_save(String id_save) {
		this.id_save = id_save;
	}
	public String getPasswd_save() {
		return passwd_save;
	}
	public void setPasswd_save(String passwd_save) {
		this.passwd_save = passwd_save;
	}
	public String getUrl_address() {
		return url_address;
	}
	public void setUrl_address(String url_address) {
		this.url_address = url_address;
	}
	
	@Override
	public String toString() {
		return "MemVO [memno=" + memno + ", id=" + id + ", passwd=" + passwd + ", mname=" + mname + ", tel=" + tel
				+ ", zipcode=" + zipcode + ", address1=" + address1 + ", address2=" + address2 + ", mdate=" + mdate
				+ ", grade=" + grade + ", old_passwd=" + old_passwd + ", id_save=" + id_save + ", passwd_save="
				+ passwd_save + ", url_address=" + url_address + "]";
	}
}
