package chooeat.admin.core.dao;

import java.util.List;

public interface CoreDao<V, I> {
	int insert(V VO);

	int deleteById(I id);

	int update(V VO);

	V selectById(I id);

	List<V> selectAll();
}
