package iducs.jsp.final201712070.repository;

import iducs.jsp.final201712070.model.Blog;
import iducs.jsp.final201712070.util.Pagination;

import java.util.List;

public interface BlogDAO {
    int create(Blog blog);
    Blog read(Blog blog);
    List<Blog> readList();
    int update(Blog blog);
    int delete(Blog blog);
    int readTotalRows();
    List<Blog> readListPagination(Pagination pagination);
}
