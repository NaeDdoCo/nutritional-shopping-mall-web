package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.ProductDTO;
import model.dto.ReviewDTO;
import model.util.JDBCUtil;

public class ReviewDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	/*
	 * 기능 1) 해당제품 리뷰 출력, 마이페이지 내가 쓴 리뷰 출력 2) ?리뷰 선택? 3) 리뷰 작성 4) ?리뷰 변경? 5) 리뷰 삭제
	 */

	// 내 리뷰
	private static final String SELECTALL_MY_REVIEW = "SELECT R.R_ID, R.M_ID, R.B_ID, R.SCORE, R.CONTENTS, R.CREATE_TIME, B.P_ID, P.P_NAME "
			+ "FROM REVIEW R "
			+ "JOIN BUYINFO B ON R.B_ID = B.B_ID "
			+ "JOIN PRODUCT P ON B.P_ID = P.P_ID "
			+ "WHERE R.M_ID = ? "
			+ "ORDER BY R.CREATE_TIME DESC";

	// 상품 리뷰
	private static final String SELECTALL_P_RIVIEW = "SELECT R.R_ID, R.M_ID, R.B_ID, R.SCORE, R.CONTENTS, R.CREATE_TIME, B.P_ID, P.P_NAME "
			+ "FROM REVIEW R "
			+ "JOIN BUYINFO B ON B.B_ID = R.B_ID "
			+ "JOIN PRODUCT P ON B.P_ID = P.P_ID "
			+ "WHERE B.P_ID =? "
			+ "ORDER BY R.CREATE_TIME DESC";

	// XXXX
	private static final String SELECTONE = "";

	// 리뷰작성
	private static final String INSERT = "";

	//수정불가
	private static final String UPDATE = "";

	//리뷰삭제
	private static final String DELETE = "";

	public ArrayList<ReviewDTO> selectAll(ReviewDTO rDTO) {

		ArrayList<ReviewDTO> reviewList = null;
		ReviewDTO reviewDTO = null;

		conn = JDBCUtil.connect();

		if (rDTO.getSearchCondition().equals("내리뷰")) {

			reviewList = new ArrayList<ReviewDTO>();

			try {
				pstmt = conn.prepareStatement(SELECTALL_MY_REVIEW);
				pstmt.setString(1, rDTO.getMID());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					reviewDTO = new ReviewDTO();
					reviewDTO.setRID(rs.getInt("R_ID"));
					reviewDTO.setMID(rs.getString("M_ID"));
					reviewDTO.setBID(rs.getInt("B_ID"));
					reviewDTO.setScore(rs.getInt("SCORE"));
					reviewDTO.setContents(rs.getString("CONTENTS"));
					reviewDTO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
					reviewDTO.setPID(rs.getInt("P_ID"));
					reviewDTO.setpNAME(rs.getString("P_NAME"));
					reviewList.add(reviewDTO);
				}

				rs.close();

				if (reviewList.size() > 0) {
					return reviewList;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}

		} else if (rDTO.getSearchCondition().equals("상품리뷰")) {

			reviewList = new ArrayList<ReviewDTO>();

			try {
				pstmt = conn.prepareStatement(SELECTALL_P_RIVIEW);
				pstmt.setInt(1, rDTO.getPID());

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					reviewDTO = new ReviewDTO();
					reviewDTO.setRID(rs.getInt("R_ID"));
					reviewDTO.setMID(rs.getString("M_ID"));
					reviewDTO.setBID(rs.getInt("B_ID"));
					reviewDTO.setScore(rs.getInt("SCORE"));
					reviewDTO.setContents(rs.getString("CONTENTS"));
					reviewDTO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
					reviewDTO.setPID(rs.getInt("P_ID"));
					reviewDTO.setpNAME(rs.getString("P_NAME"));
					reviewList.add(reviewDTO);
				}

				rs.close();

				if (reviewList.size() > 0) {
					return reviewList;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		return null;
	}

	public ProductDTO ReviewDTO(ReviewDTO rDTO) {
		return null;
	}

	public boolean insert(ReviewDTO rDTO) {
		return false;
	}

	public boolean update(ReviewDTO rDTO) {
		return false;
	}

	public boolean delete(ReviewDTO rDTO) {
		return false;
	}
}
