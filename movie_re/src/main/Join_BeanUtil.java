package main;

import javax.servlet.http.HttpServletRequest;

import dao.JoinVo;

public class Join_BeanUtil {
	public static int ADD2 = 0;

	public static boolean validate(JoinVo user_Join, int icase) {
		if (icase == ADD2) {
			return (user_Join != null && isNotForbid(user_Join.getNickPK()));
		}
		return false;
	}

	public static boolean isVS(String l) {
		if (l == null || l.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isVN(int l) {
		return (l > 0);
	}

	private static boolean isNotForbid(String l) {
		return (l.indexOf("www.youtube.com") == -1 && l.indexOf("iframe") == -1);
	}

	public static void populate(HttpServletRequest request, JoinVo user_Join) {
		user_Join.setUser_Grade(parseInt(BeanUtil.us2kr(request.getParameter("user_Grade"))));
		user_Join.setNickPK(us2kr(request.getParameter("nickPK")));
		user_Join.setPassword(us2kr(request.getParameter("password")));
		user_Join.setE_mail(us2kr(request.getParameter("e_mail")));
		user_Join.setUser_Point(parseInt(BeanUtil.us2kr(request.getParameter("user_Point"))));

		user_Join.setFavorite_Cate1(us2kr(request
				.getParameter("favorite_Cate1")));
		user_Join.setFavorite_Cate2(us2kr(request
				.getParameter("favorite_Cate2")));
		user_Join.setFavorite_Cate3(us2kr(request
				.getParameter("favorite_Cate3")));
		user_Join.setFavorite_Cate4(us2kr(request
				.getParameter("favorite_Cate4")));

		user_Join.setSpare1(us2kr(request.getParameter("spare1")));
		user_Join.setSpare2(us2kr(request.getParameter("spare2")));
		user_Join.setSpare3(us2kr(request.getParameter("spare3")));
	}

	public static int parseInt(String l) {
		if (l == null || l.equals("")) {
			return 0;
		} else {
			try {
				return Integer.parseInt(l);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
	}

	public static String us2kr(String l) {
		if (l == null || l.equals("")) {
			return l;
		}

		try {
			l = new String(l.getBytes("8859_1"), "euc-kr");
		} catch (Exception e) {
		}
		return l;
	}
}
