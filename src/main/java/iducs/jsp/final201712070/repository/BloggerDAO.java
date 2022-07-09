package iducs.jsp.final201712070.repository;

import iducs.jsp.final201712070.model.Blogger;
import iducs.jsp.final201712070.util.Pagination;

import java.util.List;

public interface BloggerDAO {
    int create(Blogger blogger);
    Blogger read(Blogger blogger);
    List<Blogger> readList();
    int update(Blogger blogger);
    int delete(Blogger blogger);
    int readTotalRows();
    List<Blogger> readListPagination(Pagination pagination);
}
