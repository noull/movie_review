package dao;

import java.util.List;

public interface Bang01Dao {
	public List<Bang01Vo> findAll() throws Exception;

	public void add(Bang01Vo prm) throws Exception;

	public void update(Bang01Vo prm) throws Exception;

	public List<Bang01Vo> search(String search_Word, int Switch)
			throws Exception;

	public void del(Bang01Vo prm) throws Exception;

	public Bang01Vo modify(int textPk) throws Exception;

	public boolean readContent(String read_Id, int read_textPk, String nick_Id)
			throws Exception;

	public boolean recomContent(String recom_Id, int repl_Pk, String nick_Id)
			throws Exception;

	public LoginVo login(String id) throws Exception;

	public List<Bang01Vo> show(String search_Word) throws Exception;
	
	public Bang01Vo detail_view(String search_Word) throws Exception;
	
	public void re_add(Reple_List re) throws Exception;
	
	public List<Reple_List> re_findAll(String search_Word) throws Exception;
	
	public void re_delete(String repl_pk) throws Exception;
	
	public void re_update(Reple_List re) throws Exception;
}
