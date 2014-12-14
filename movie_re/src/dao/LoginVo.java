package dao;

public class LoginVo {

	public String getNickPK() {
		return nickPK;
	}

	public void setNickPK(String nickPK) {
		this.nickPK = nickPK;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public int getFavorite_Cate1() {
		return favorite_Cate1;
	}

	public void setFavorite_Cate1(int favorite_Cate1) {
		this.favorite_Cate1 = favorite_Cate1;
	}

	private String nickPK = null;
	private String password = null;
	private String e_mail = null;
	private int favorite_Cate1 = 0;
	private int user_Point = 0;

	public int getUser_Point() {
		return user_Point;
	}

	public void setUser_Point(int user_Point) {
		this.user_Point = user_Point;
	}

}
