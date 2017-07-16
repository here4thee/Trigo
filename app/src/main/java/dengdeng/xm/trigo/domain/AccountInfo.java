package dengdeng.xm.trigo.domain;

public class AccountInfo {
	private int id;
	private String email;
	private String password;
	private String name;
	private String whatsup;
	private String profile;
	private int target;
	private int times;
	private int round;
	private int record;
	
	public AccountInfo(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.name = "Trigo";
		this.whatsup = "Monogamy.";
		this.profile = "@drawable/me";
		this.target = 511;
		this.times = 0;
		this.round = 0;
		this.record = 0;
	}
	
	public AccountInfo() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWhatsup() {
		return whatsup;
	}
	public void setWhatsup(String whatsup) {
		this.whatsup = whatsup;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getRecord() {
		return record;
	}
	public void setRecord(int record) {
		this.record = record;
	}

}
