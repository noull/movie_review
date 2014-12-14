package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Join_BeanUtil;
import model2.ModelAndView;
import model2.UrlMapping;
import dao.JoinDao;
import dao.JoinDao_OracleImpl;
import dao.JoinVo;

public class Join_Ctrl {
	public Join_Ctrl() {
		System.out.println("������ ����");
	}

	@UrlMapping("/login_add.do")
	public ModelAndView add2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JoinVo user_Join = new JoinVo();
		Join_BeanUtil.populate(request, user_Join);

		if (Join_BeanUtil.validate(user_Join, Join_BeanUtil.ADD2)) {

			JoinDao JoinDao = new JoinDao_OracleImpl();
			JoinDao.add(user_Join);
		} else {
			throw new RuntimeException("data validation fail");
		}
		ModelAndView mnv = new ModelAndView("join");
		return mnv;
	}

}
