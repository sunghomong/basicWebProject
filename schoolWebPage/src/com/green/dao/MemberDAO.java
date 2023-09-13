package com.green.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.green.db.DBManager;
import com.green.vo.GradeAvgVO;
import com.green.vo.GradeVO;
import com.green.vo.MemberVO;

public class MemberDAO {
	// 싱글턴패턴
	private MemberDAO() {
	};

	private static MemberDAO dao = new MemberDAO();

	public static MemberDAO getInstance() {
		return dao;
	}

	public int userCheck(String userid, String userpw) {
		int result = -1;

		String sql = "SELECT userpw FROM memberTbl WHERE userid=?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
//			System.out.println(userid);
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, userid);

			rs = psmt.executeQuery();

//			System.out.println("패스");
			if (rs.next()) {
				// 로그인 정보가 있을때
				if (rs.getString("userpw") != null && rs.getString("userpw").equals(userpw)) {
					// 비밀번호가 일치할때
					result = 1;
				} else {
					// 비밀번호가 일치하지 않을때
					result = 0;
				}

			} else {

				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}

		return result;
	}

	// ---------------------------------------------------------------------------------
	// userid를 통하여 정보 가져오기

	public MemberVO getMemberByUserid(String userid) {
		MemberVO mVo = null;

		String sql = "SELECT * FROM memberTbl WHERE userid=?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBManager.getConnection();

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, userid);

			rs = psmt.executeQuery();

			if (rs.next()) {
				mVo = new MemberVO();

				mVo.setCode(rs.getInt("code"));
				mVo.setUserid(rs.getString("userid"));
				mVo.setUsername(rs.getString("username"));
				mVo.setUserpw(rs.getString("userpw"));
				mVo.setBan(rs.getString("ban"));
				mVo.setUsergrade(rs.getString("usergrade"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}

		return mVo;
	}

	public int checkId(String userid) {

		int result = -1;

		String sql = "SELECT userid FROM memberTbl WHERE userid=?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, userid);

			rs = psmt.executeQuery();

			if (rs.next()) { // rs가 있다는건 일치하는 userid가 있다는 뜻
				result = 1;
			} else { // 일치하는 아이디가 없음 사용 가능한
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}

		return result;
	}

	public int insertMember(MemberVO mVo) {
		int result = -1;

		String sql = "INSERT INTO memberTbl VALUES(code_seq.nextval,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {

			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, mVo.getUserid());
			psmt.setString(2, mVo.getUsername());
			psmt.setString(3, mVo.getUserpw());
			psmt.setString(4, mVo.getBan());
			psmt.setString(5, mVo.getUsergrade());

			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt);
		}

		return result;
	}

	public String getTeacherNameByBan(String ban) {
		String teacherName = null;

		String sql = "SELECT username FROM memberTbl WHERE ban=? AND usergrade='선생'";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, ban);

			rs = psmt.executeQuery();
			if (rs.next()) {
				teacherName = rs.getString("username");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}

		return teacherName;
	}

	public GradeVO getGradeByUserName(String username) {
		GradeVO grades = null;

		String sql = "SELECT * FROM gradeTbl WHERE username=?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, username);

			rs = psmt.executeQuery();

			if (rs.next()) {
				grades = new GradeVO();

				grades.setUsername(username);
				grades.setKor_score(rs.getInt("kor_score"));
				grades.setEng_score(rs.getInt("eng_score"));
				grades.setMath_score(rs.getInt("math_score"));
				grades.setScience_score(rs.getInt("science_score"));
				grades.setSocial_score(rs.getInt("social_score"));

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}

		return grades;
	}

	public GradeAvgVO getMemberGradeByBan(String ban) {
		GradeAvgVO gAVo = null;

		// 조인 연산을 이용해 반에 대한 avg 가져오기
		// 실수이기에 객체 하나 더 생성
		String sql = "  SELECT AVG(kor_score) AS kor_avg , AVG (eng_score) AS eng_avg, AVG(math_score) AS math_avg, AVG(science_score) AS sci_avg,AVG(social_score) AS sc_avg\r\n  "
				+ "  FROM gradetbl JOIN memberTbl ON gradetbl.username = membertbl.username\r\n" + "  WHERE ban=?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, ban);

			rs = psmt.executeQuery();

			if (rs.next()) {
				gAVo = new GradeAvgVO();

				gAVo.setKor_avg(rs.getFloat("kor_avg"));
				gAVo.setEng_avg(rs.getFloat("eng_avg"));
				gAVo.setMath_avg(rs.getFloat("math_avg"));
				gAVo.setSci_avg(rs.getFloat("sci_avg"));
				gAVo.setSc_avg(rs.getFloat("sc_avg"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}

		return gAVo;
	}

	public List<MemberVO> getMemberListByBan(String ban) {
		List<MemberVO> list = new ArrayList<>();

		String sql = "SELECT ROWNUM AS num,userid,username FROM memberTbl WHERE ban=?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, ban);

			rs = psmt.executeQuery();

			while (rs.next()) {
				MemberVO mVo = new MemberVO();

				mVo.setNum(rs.getInt("num"));
				mVo.setUserid(rs.getString("userid"));
				mVo.setUsername(rs.getString("username"));

				list.add(mVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}

		return list;
	}

	public int getMemberCountByBan(String ban) {
		int memberCount = 0;

		String sql = "SELECT COUNT(*) AS memberCount FROM memberTbl WHERE ban=?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, ban);

			rs = psmt.executeQuery();

			if (rs.next()) {
				memberCount = rs.getInt("memberCount");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}

		return memberCount;
	}

	public int checkGrade(String username) {
		int result = -1;

		String sql = "SELECT * FROM gradeTbl WHERE username=?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, username);

			rs = psmt.executeQuery();

			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}

		return result;
	}

	public List<MemberVO> getNoGradeMemberListByBan(String ban) {

		List<MemberVO> list = new ArrayList<>();

		String sql = "  SELECT ROWNUM AS num, memberTbl.username, membertbl.usergrade " +
                "  FROM memberTbl " +
                "  LEFT OUTER JOIN gradeTbl ON memberTbl.username = gradeTbl.username " +
                "  WHERE ban=? AND usergrade = '학생' AND gradeTbl.username IS NULL   ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, ban);

			rs = psmt.executeQuery();
			
//			System.out.println("쿼리 출력 완료");

			while(rs.next()) {
				MemberVO mVo = new MemberVO();

				mVo.setNum(rs.getInt("num"));
				mVo.setUsername(rs.getString("username"));
				mVo.setUsergrade(rs.getString("usergrade"));

//				System.out.println("mVo");
				
				list.add(mVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt, rs);
		}

		return list;
	}

	public void insertGrade(GradeVO gVo) {
		
		String sql = " INSERT INTO gradeTbl VALUES(?,?,?,?,?,?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, gVo.getUsername());
			psmt.setInt(2, gVo.getKor_score());
			psmt.setInt(3, gVo.getEng_score());
			psmt.setInt(4, gVo.getMath_score());
			psmt.setInt(5, gVo.getScience_score());
			psmt.setInt(6, gVo.getSocial_score());
			
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, psmt);
		}
	}

}
