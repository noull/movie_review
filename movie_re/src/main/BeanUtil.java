package main;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Bang01Vo;

public class BeanUtil {
	public static int ADD2 = 0;
	public static int del2 = 1;
	public static int search2 = 2;
	public static int update2 = 3;

	public static boolean validate(Bang01Vo prm, int icase) {
		if (icase == ADD2) {
			return true;
		} else if (icase == del2) {
			return true;
		} else if (icase == search2) {
			return true;
		} else if (icase == update2) {
			return true;
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

	public static void add_populate(HttpServletRequest request, Bang01Vo prm)
			throws IOException {
		String uploadPath = request.getRealPath("upload");
		MultipartRequest mpr = new MultipartRequest(request, uploadPath,
				1024 * 1024 * 10, "euc-kr", new DefaultFileRenamePolicy());

		String fsn = mpr.getFilesystemName("list_file");
		String ofn = mpr.getOriginalFileName("list_file");

		prm.setNickPK(mpr.getParameter("id"));
		prm.setTitle(mpr.getParameter("title"));
		prm.setCategories(parseInt(mpr.getParameter("categories")));
		prm.setContent(mpr.getParameter("content"));

		prm.setFileUp(ofn);
		prm.setFileDown(fsn);

	}

	public static void up_populate(HttpServletRequest request, Bang01Vo prm)
			throws IOException {

		prm.setNo(parseInt(BeanUtil.us2kr(request.getParameter("no"))));
		prm.setText(us2kr(request.getParameter("text")));
		System.out.println(1);
		MultipartRequest mpr = new MultipartRequest(request, "upload\\",
				1024 * 1024 * 10, "euc-kr", new DefaultFileRenamePolicy());
		System.out.println(2);
		String fsn = mpr.getFilesystemName("list_file");
		String ofn = mpr.getOriginalFileName("list_file");

		prm.setTextPK(parseInt(mpr.getParameter("textPk")));
		prm.setNickPK(mpr.getParameter("id"));
		prm.setTitle(mpr.getParameter("title"));
		System.out.println(3);
		prm.setContent(mpr.getParameter("content"));

		prm.setFileUp(ofn);
		prm.setFileDown(fsn);

		prm.setCategories(parseInt(request.getParameter("categories").trim()));
	}



	public static void search_populate(HttpServletRequest request, Bang01Vo prm) {
		prm.setTextPK(parseInt(BeanUtil.us2kr(request.getParameter("add_text"))));
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

	public static boolean Id_chech(String nick_Pk, String check_Id){
		nick_Pk = nick_Pk.trim();
		check_Id = check_Id.trim();
		System.out.println("ababab");
		
		if(nick_Pk.equals(check_Id)||check_Id.equals("admin"))
			return true;
		else
			return false;
	}
}
