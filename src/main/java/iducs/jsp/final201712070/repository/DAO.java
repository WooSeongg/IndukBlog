package iducs.jsp.final201712070.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public interface DAO {
    Connection getConnection();//연결 객체를 가져오는 메서드 선언
    void closeResources(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs);//자원회수 메서드 선언

}
