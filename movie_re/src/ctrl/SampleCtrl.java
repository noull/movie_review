package ctrl;


import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.BeanUtil;
import model2.ModelAndView;
import model2.UrlMapping;
import dao.Bang01Dao;
import dao.Bang01Dao_OracleImpl;
import dao.Bang01Vo;
import dao.Reple_List;

public class SampleCtrl {

	public SampleCtrl() {
	}
	

	@UrlMapping("/add2.do")
	public ModelAndView add21(HttpServletRequest request,HttpServletResponse response) throws Exception {
		

		Bang01Vo prm = new Bang01Vo();
		String write_Id = BeanUtil.us2kr(request.getParameter("id"));
		BeanUtil.add_populate(request, prm);

		if (BeanUtil.validate(prm, BeanUtil.ADD2)) {
		
			Bang01Dao bangDao = new Bang01Dao_OracleImpl();
			bangDao.add(prm);

		} else {
			throw new RuntimeException("data validation fail");
		}
		return new ModelAndView("redirect:/list.do");
	}
	@UrlMapping("/a.do")
	public ModelAndView add2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		Bang01Vo prm = new Bang01Vo();
		String write_Id = BeanUtil.us2kr(request.getParameter("id"));
		BeanUtil.add_populate(request, prm);

		if (BeanUtil.validate(prm, BeanUtil.ADD2)) {
		
			Bang01Dao bangDao = new Bang01Dao_OracleImpl();
			bangDao.add(prm);

		} else {
			throw new RuntimeException("data validation fail");
		}
		return new ModelAndView("redirect:/list.do");
	}

	@UrlMapping("/list.do")
	public ModelAndView list11(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Bang01Dao bangDao = new Bang01Dao_OracleImpl();
		List<Bang01Vo> rl = bangDao.findAll();

		ModelAndView mnv = new ModelAndView("main");
		mnv.addObject("rl", rl);
		return mnv;
	}
	
//	@UrlMapping("/grid.do")
//	public ModelAndView list(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		System.out.println("ÇÕ");	
//		ModelAndView mnv = new ModelAndView("redirect:/main.jsp");
//
//		return mnv;
//	}
	
	@UrlMapping("/l.do")
	public ModelAndView list1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		ModelAndView mnv = new ModelAndView("main");
		
		
		return mnv;
	}

	@UrlMapping("/search2.do")
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int Switch = 0;
		
		String search_Con = BeanUtil.us2kr(request.getParameter("search_con"));
		String search_Word = BeanUtil.us2kr(request.getParameter("search_text"));
		
		System.out.println(search_Con+search_Word);													

		if (search_Con.equals("search_nick_pk"))
			Switch = 1;
		else if (search_Con.equals("search_title"))
			Switch = 2;
		else if (search_Con.equals("search_content"))
			Switch = 3;
		else {
			System.out.println("no contact");
			return new ModelAndView("redirect:/list.do");
		}

		Bang01Dao bangDao = new Bang01Dao_OracleImpl();
		List<Bang01Vo> rl = bangDao.search(search_Word, Switch);
		System.out.println(rl);
		
		ModelAndView mnv = new ModelAndView("main");
		mnv.addObject("rl", rl);

		return mnv;
	}

	@UrlMapping("/login.do")
	public ModelAndView login_confirm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(":login");

		ModelAndView mnv = new ModelAndView("list");

		return mnv;

	}
	
		
    @UrlMapping("/checkId.do")
	public ModelAndView CheckId(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
    	
		System.out.println(":checkId");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		ModelAndView mnv = new ModelAndView("list");
		return mnv;
	}

	@UrlMapping("/download.do")
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("Sample::download");
		String ofn = BeanUtil.us2kr(request.getParameter("ofn"));
		String fsn = BeanUtil.us2kr(request.getParameter("fsn"));

		response.setContentType("application/octet");
		response.setHeader("content-disposition", "attachment;filename=" + ofn);
		OutputStream out = response.getOutputStream();
		InputStream in = new FileInputStream("//upload//" + fsn);

		int l = 0;
		byte[] buf = new byte[1024 * 16];

		while ((l = in.read()) != -1) {
			out.write(buf, 0, l);
		}

		in.close();
		out.flush();
		out.close();

	}

	@UrlMapping("/del2.do")
	public ModelAndView del(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Bang01Vo prm = new Bang01Vo();

		String nick_Id = BeanUtil.us2kr(request.getParameter("nickpk_del")).trim();
		String update_Id = BeanUtil.us2kr(request.getParameter("userid_del").trim());
		String textPK_s = BeanUtil.us2kr(request.getParameter("textpk_del").trim());
		int textPK = Integer.parseInt(textPK_s);
	

		if (!(BeanUtil.Id_chech(nick_Id, update_Id))) {
			return new ModelAndView("redirect:/list.do");
		}
		prm.setTextPK(textPK);
		System.out.println(nick_Id + " " + update_Id);

		if (BeanUtil.validate(prm, BeanUtil.del2)) {
			Bang01Dao bangDao = new Bang01Dao_OracleImpl();
			bangDao.del(prm);

		} else {

			throw new RuntimeException("data validation fail");
		}
		return new ModelAndView("list");
	}

	@UrlMapping("/update2.do")
	public ModelAndView update(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		String categorys =(request.getParameter("categories").trim());
		int category = Integer.parseInt(categorys);
		String content = (request.getParameter("content")).trim();

		
		String title = (request.getParameter("title")).trim();
		String textPK_ss =(request.getParameter("textpk")).trim();
		System.out.println(3);
		int textPK_s =Integer.parseInt(textPK_ss);
		
		
		String update_Id = (request.getParameter("userid"));

		String fileup = (request.getParameter("fileup")).trim();
		String filedown =(request.getParameter("filedown")).trim();
		String nick_Id =(request.getParameter("nickpk")).trim();
		System.out.println(filedown);

		if(!(BeanUtil.Id_chech(nick_Id, update_Id))) {
			return new ModelAndView("redirect:/list.do"); 
		}

		System.out.println("Sample:update2");
		Bang01Vo prm = new Bang01Vo();
		
		prm.setCategories(category);
		prm.setContent(content);
		prm.setTitle(title);
		prm.setNickPK(update_Id);
		prm.setFileUp(fileup);
		prm.setFileDown(filedown);
		prm.setTextPK(textPK_s);
		
		//BeanUtil.up_populate(request, prm);
		
		if (BeanUtil.validate(prm, BeanUtil.update2)) {
			Bang01Dao bangDao = new Bang01Dao_OracleImpl();
			bangDao.update(prm);
			System.out.println(prm.getFileDown()+ "       "+prm.getFileUp());
		} else {
			throw new RuntimeException("data validation fail");
		}
		return new ModelAndView("list");
	}
/*
	@UrlMapping("/modify.do")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String nick_Id = BeanUtil.us2kr(request.getParameter("NickPK"));
		String update_Id = BeanUtil.us2kr(request.getParameter("update_id"));
		String textPK_s = BeanUtil.us2kr(request.getParameter("up_text"));

		if (!(BeanUtil.Id_chech(nick_Id, update_Id))) {
			return new ModelAndView("redirect:/list.do");
		}
		int textPK = Integer.parseInt(textPK_s);

		Bang01Dao dao = new Bang01Dao_OracleImpl();
		Bang01Vo vo = dao.modify(textPK);

		ModelAndView mnv = new ModelAndView("modifyForm");
		mnv.addObject("vo", vo);

		return mnv;
	}
*/
	@UrlMapping("/readContent.do")
	public ModelAndView readContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("Sample::readContent");

		// Bang01Vo prm = new Bang01Vo();
		String read_Id = BeanUtil.us2kr(request.getParameter("userid"));
		String read_textPk_s = request.getParameter("textpk");
		int read_textPk = Integer.parseInt(read_textPk_s);
		String nick_Id = BeanUtil.us2kr(request.getParameter("nickpk"));
		
		Bang01Dao bangDao = new Bang01Dao_OracleImpl();
		boolean read_TF = bangDao.readContent(read_Id, read_textPk, nick_Id); //
																			//
		return new ModelAndView("redirect:/list.do");

	}

	@UrlMapping("/recomContent.do")
	public ModelAndView recomContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("Sample::recomContent");

		String nick_Id = BeanUtil.us2kr(request.getParameter("NickPK"));	
		String read_Id = BeanUtil.us2kr(request.getParameter("recom_id"));
		System.out.println(request.getParameter("recom_textpk"));
		String read_textPk_s = request.getParameter("recom_textpk");
		System.out.println(read_textPk_s);
		int read_textPk = Integer.parseInt(read_textPk_s);

		Bang01Dao bangDao = new Bang01Dao_OracleImpl();
		boolean read_TF = bangDao.recomContent(read_Id, read_textPk, nick_Id); // 
		
		return new ModelAndView("redirect:/list.do");

	}

	@UrlMapping("/show.do")
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String search_Word = request.getParameter("tpk");
		String userid = request.getParameter("userid");
		
		Bang01Dao bangDao = new Bang01Dao_OracleImpl();
		List<Bang01Vo> sr = bangDao.show(search_Word);

		List<Reple_List> re_list = bangDao.re_findAll(search_Word);
		for(Bang01Vo vo:sr){
			System.out.println(vo.getTheTime()+"½Ã°£");
			
		}

		ModelAndView mnv = new ModelAndView("liview");
		mnv.addObject("sr", sr);
		mnv.addObject("re_list", re_list);
		mnv.addObject("userid", userid);
	
		return mnv;
	}
	
	
	
	@UrlMapping("/relist.do")
	public ModelAndView relist( HttpServletRequest request,	HttpServletResponse response ) throws Exception {
		System.out.println("Sample:relist.do");
		
		String search_Word = request.getParameter("tpk");
		System.out.println(search_Word);

		Bang01Dao bangDao = new Bang01Dao_OracleImpl();
		List<Reple_List> re_list=bangDao.re_findAll(search_Word);
		
		ModelAndView mnv =new ModelAndView("liview");
		mnv.addObject("re_list", re_list);
		
		return mnv;
	}
	
	@UrlMapping("/reple_add.do")
	public ModelAndView reple_add( HttpServletRequest request, HttpServletResponse response ) throws Exception {
		

		Reple_List re=new Reple_List();
		String textpk=request.getParameter("textpk_re_add");
		int re_txtpk=Integer.parseInt(textpk);
		re.setTextPK(re_txtpk);
		re.setNickPK(request.getParameter("re_m_id"));
		re.setRepl_content(request.getParameter("re_content"));
		
		Bang01Dao dao=new Bang01Dao_OracleImpl();
		dao.re_add(re);
		List<Bang01Vo> lst=dao.findAll();
		
		
	
		ModelAndView mnv =new ModelAndView("main");
		mnv.addObject("rl", lst);
		return mnv;
		
	}
	
	@UrlMapping("/re_delete.do")
	public ModelAndView re_delete( HttpServletRequest request,	HttpServletResponse response ) throws Exception {

		
		String repl_pk= request.getParameter("repl_pk");

		Bang01Dao bangDao = new Bang01Dao_OracleImpl(); 
		bangDao.re_delete(repl_pk);
				
		ModelAndView mnv =new ModelAndView("list");
		return mnv;
	}
	
	@UrlMapping("/re_update.do")
	public ModelAndView re_update( HttpServletRequest request,	HttpServletResponse response ) throws Exception {
		Reple_List re_detail= new Reple_List();
		int reple_pk=Integer.parseInt(request.getParameter("repl_pk"));
		re_detail.setRepl_PK(reple_pk);
		re_detail.setRepl_content(request.getParameter("re_content"));
		String search_Word = request.getParameter("textpk");


		
		Bang01Dao bangDao = new Bang01Dao_OracleImpl();
		bangDao.re_update(re_detail);
		
		List<Reple_List> re_list=bangDao.re_findAll(search_Word);
		
		ModelAndView mnv =new ModelAndView("list");

		
		return mnv;
	}
}
