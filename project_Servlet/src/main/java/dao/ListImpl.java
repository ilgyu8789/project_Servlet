package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.ListVo;

public class ListImpl implements ListDao {

	// 오라클 연결하기
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 가져오기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", // DB URL
					"C##BITUSER", // DB User
					"bituser"); // DB Pass
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
			e.printStackTrace();
		}

		return conn;
	}

	@Override
	public List<ListVo> getList() { // 전체 리스트

		List<ListVo> list = new ArrayList<ListVo>();

		Connection conn = null;		//	오라클 연결한 것을 불러오기
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();		//	불러온 오라클 연결 시도
			stmt = conn.createStatement();

			String sql = "SELECT id, name, hp, tel FROM phone_book" + " ORDER BY id";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);

				ListVo vo = new ListVo();
				vo.setlistId(id);
				vo.setlistName(name);
				vo.setlistHp(hp);
				vo.setlistTel(tel);

				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<ListVo> getSearch(String find) { // 검색
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ListVo> list = new ArrayList<>();

		try {
			conn = getConnection();
			String sql = "SELECT id, name, hp, tel" + " FROM phone_book " + " WHERE name LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + find + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);

				ListVo vo = new ListVo();
				vo.setlistId(id);
				vo.setlistHp(hp);
				vo.setlistName(name);
				vo.setlistTel(tel);

				list.add(vo);
			}
		} catch (SQLException e) {

		}
		return list;
	}

	@Override
	public int delete(Long no) { // 삭제
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book " + " WHERE phone_book.id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			deletedCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedCount;
	}

	@Override	
	public int insert(ListVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		try {
			conn = getConnection();
			String sql = "INSERT INTO phone_book" + " VALUES(seq_phone_book.NEXTVAL,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getlistName());
			pstmt.setString(2, vo.getlistHp());
			pstmt.setString(3, vo.getlistTel());

			insertedCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return insertedCount;
	}

	

}
