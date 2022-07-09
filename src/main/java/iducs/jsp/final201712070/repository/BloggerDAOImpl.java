package iducs.jsp.final201712070.repository;

import iducs.jsp.final201712070.model.Blogger;
import iducs.jsp.final201712070.util.Pagination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BloggerDAOImpl extends DAOImplOracle implements BloggerDAO{

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BloggerDAOImpl() {
        conn = getConnection();
    }
    @Override
    public int create(Blogger blogger) {
        String query = "insert into blogger201712070 values(seq_blogger201712070.nextval, ?, ?, ?, ?, ?)";
        int rows = 0; //질의처리결과를 저장(영향받은 행의 수)
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, blogger.getEmail());
            pstmt.setString(2, blogger.getPw());
            pstmt.setString(3, blogger.getName());
            pstmt.setString(4, blogger.getPhone());
            pstmt.setString(5, blogger.getAddress());
            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public Blogger read(Blogger blogger) {
        Blogger retBlogger = null; //리턴할 블로거객체
        String sql = "select * from blogger201712070 where email=? and pw=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blogger.getEmail());
            pstmt.setString(2, blogger.getPw());

            rs = pstmt.executeQuery();

            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다름 요소로 접근
                retBlogger = new Blogger();
                retBlogger.setId(rs.getLong("id")); //id값도 DTO에 저장
                retBlogger.setEmail(rs.getString("email"));
                retBlogger.setPw(rs.getString("pw"));
                retBlogger.setName(rs.getString("name"));
                retBlogger.setPhone(rs.getString("phone"));
                retBlogger.setAddress(rs.getString("address"));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retBlogger;
    }

    public Blogger readByEmail(Blogger blogger) {
        Blogger retBlogger = null;
        String sql = "select * from blogger201712070 where email=? "; // 유일키(unique key)로 조회
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blogger.getEmail());
            rs = pstmt.executeQuery();

            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다름 요소로 접근
                retBlogger = new Blogger();
                retBlogger.setId(rs.getLong("id")); //id값도 DTO에 저장
                retBlogger.setEmail(rs.getString("email"));
                retBlogger.setPw(rs.getString("pw"));
                retBlogger.setName(rs.getString("name"));
                retBlogger.setPhone(rs.getString("phone"));
                retBlogger.setAddress(rs.getString("address"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retBlogger;
    }

    @Override
    public List<Blogger> readList() {
        ArrayList<Blogger> bloggerList  = null;
        String sql = "select * from blogger201712070";
        try {
            stmt = conn.createStatement();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
            if((rs = stmt.executeQuery(sql)) != null) { // 질의 결과가 ResultSet 형태로 반환
                bloggerList = new ArrayList<Blogger>();
                while (rs.next()) {
                    Blogger blogger = new Blogger();
                    blogger.setId(rs.getLong("id"));
                    blogger.setEmail(rs.getString("email"));
                    blogger.setPw(rs.getString("pw"));
                    blogger.setName(rs.getString("name"));
                    blogger.setPhone(rs.getString("phone"));
                    blogger.setAddress(rs.getString("address"));
                    bloggerList.add(blogger);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bloggerList;
    }

    @Override
    public int update(Blogger blogger) {
        conn = this.getConnection();
        int rows = 0; //질의처리결과를 저장(영향받은 행의 수)
        String sql ="update blogger201712070 set  pw=?, name=?, phone=?, address=? where email=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blogger.getPw());
            pstmt.setString(2, blogger.getName());
            pstmt.setString(3, blogger.getPhone());
            pstmt.setString(4, blogger.getAddress());
            pstmt.setString(5, blogger.getEmail());

            rows = pstmt.executeUpdate(); // 1이상이면 정상, 0이하면 오류

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            this.closeResources(conn, stmt, pstmt, rs);
        }
        return rows;
    }

    @Override
    public int delete(Blogger blogger) {
        conn = this.getConnection();
        int rows = 0;
        String sql = "delete from blogger201712070 where email=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blogger.getEmail());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResources(conn, stmt, pstmt, rs);
        }
        return rows;
    }

    @Override
    public int readTotalRows() {
        int rows = 0;
        String sql = "select count(*) as totalRows  from blogger201712070";
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
    public List<Blogger> readListPagination(Pagination pagination) {
        ArrayList<Blogger> bloggerList  = null;
        String sql = "select * from (select A.*, rownum as rnum from (select *  from blogger201712070 order by id desc) A) where not email in('admin') and rnum >= ? and rnum <= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,pagination.getFirstRow());
            pstmt.setInt(2,pagination.getEndRow());

            rs = pstmt.executeQuery();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능

            bloggerList = new ArrayList<Blogger>();
            while (rs.next()) {
                Blogger blogger = new Blogger();
                blogger.setId(rs.getLong("id")); //id값도 DTO에 저장
                blogger.setEmail(rs.getString("email"));
                blogger.setPw(rs.getString("pw"));
                blogger.setName(rs.getString("name"));
                blogger.setPhone(rs.getString("phone"));
                blogger.setAddress(rs.getString("address"));
                bloggerList.add(blogger);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bloggerList;
    }
}
