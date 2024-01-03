package chooeat.prod.dao;

import java.util.List;

import chooeat.prod.model.vo.Prod;
import chooeat.prod.model.vo.ProdCommentReport;

public interface ProdCommentReportDao {
	
	int updateByprodCommentReportId(ProdCommentReport orderDetailId);
	
	List<ProdCommentReport> selectAll();
	
	int insert(ProdCommentReport ProdCommentReport);
}
