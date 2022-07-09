package iducs.jsp.final201712070.repository;

import iducs.jsp.final201712070.model.Blog;
import iducs.jsp.final201712070.util.Pagination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOImpl extends DAOImplOracle implements BlogDAO{

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BlogDAOImpl(){
        conn = getConnection();
    }
    @Override
    public int create(Blog blog) {
        String query = "insert into blog201712070 values(seq_blog201712070.nextval, ?, ?, ?, ?)";
        int rows = 0; //질의처리결과를 저장(영향받은 행의 수)
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, blog.getName());
            pstmt.setString(2, blog.getEmail());
            pstmt.setString(3, blog.getTitle());
            pstmt.setString(4, blog.getContent());
            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public Blog read(Blog blog) {
        Blog retBlog = null;
        String sql = "select * from blog201712070 where id=?"; // 유일키(unique key)로 조회
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, blog.getId());
            rs = pstmt.executeQuery();
            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다름 요소로 접근
                retBlog = new Blog();
                retBlog.setId(rs.getLong("id")); //id값도 DTO에 저장
                retBlog.setName(rs.getString("name"));
                retBlog.setEmail(rs.getString("email"));
                retBlog.setTitle(rs.getString("title"));
                retBlog.setContent(rs.getString("content"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retBlog;
    }

    @Override
    public List<Blog> readList() {
        ArrayList<Blog> blogList  = null;
        String sql = "select * from blog201712070";
        try {
            stmt = conn.createStatement();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
            if((rs = stmt.executeQuery(sql)) != null) { // 질의 결과가 ResultSet 형태로 반환
                blogList = new ArrayList<Blog>();
                while (rs.next()) {
                    Blog blog = new Blog();
                    blog.setId(rs.getLong("id"));
                    blog.setName(rs.getString("name"));
                    blog.setEmail(rs.getString("email"));
                    blog.setTitle(rs.getString("title"));
                    blog.setContent(rs.getString("content"));
                    blogList.add(blog);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blogList;
    }

    public List<Blog> readListByPage(int start, int end) {
        ArrayList<Blog> blogList  = null;
        String sql = "select * from (select A.*, rownum as rnum from (select * from blog201712070 order by id desc) A) where rnum >= ? and rnum <=?;";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,start);
            pstmt.setInt(2,end);
            rs = pstmt.executeQuery();

            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
            blogList = new ArrayList<Blog>();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getLong("id"));
                blog.setName(rs.getString("name"));
                blog.setEmail(rs.getString("email"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blogList.add(blog);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blogList;
    }

    @Override
    public int update(Blog blog) {
        String sql ="update blog201712070 set name=?, email=?, title=?, content=? where id=?";
        int rows = 0; //질의처리결과를 저장(영향받은 행의 수)
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blog.getName());
            pstmt.setString(2, blog.getEmail());
            pstmt.setString(3, blog.getTitle());
            pstmt.setString(4, blog.getContent());
            pstmt.setLong(5, blog.getId());

            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(Blog blog) {
        String sql ="delete blog201712070 where id=?";
        int rows = 0; //질의처리결과를 저장(영향받은 행의 수)
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, blog.getId());

            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public int readTotalRows() {
        int rows = 0;
        String sql = "select count(*) as totalRows  from blog201712070";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
            if (rs.next()){
                rows=rs.getInt("totalRows");
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<Blog> readListPagination(Pagination pagination) {
        ArrayList<Blog> blogList  = null;
        String sql = "select * from (select A.*, rownum as rnum from (select *  from blog201712070 order by id desc) A) where rnum >= ? and rnum <= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,pagination.getFirstRow());
            pstmt.setInt(2,pagination.getEndRow());

            rs = pstmt.executeQuery();

            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능

            blogList = new ArrayList<Blog>();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getLong("id"));
                blog.setName(rs.getString("name"));
                blog.setEmail(rs.getString("email"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blogList.add(blog);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blogList;
    }
}
