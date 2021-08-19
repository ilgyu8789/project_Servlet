package dao;

import java.util.List;
import vo.ListVo;

public interface ListDao { 
	//	리스트
	public List<ListVo> getList();
	//	등록
	public int insert(ListVo vo);
	//	검색
	public List<ListVo> getSearch(String find);
	//	삭제
	public int delete(Long no);
	//	정렬

}
