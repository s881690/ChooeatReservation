package chooeat.restaurant.daoimpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import chooeat.restaurant.dao.RestaurantDAO;
import chooeat.restaurant.model.vo.AccountVO;
import chooeat.restaurant.model.vo.AdVO;
import chooeat.restaurant.model.vo.ProdVO;
import chooeat.restaurant.model.vo.ResTypeVO;
import chooeat.restaurant.model.vo.ReservationVO;
import chooeat.restaurant.model.vo.RestaurantVO;
@Repository

public class RestaurantDaoImpl implements RestaurantDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int restaurantregister(List<String> values) {
		String sql = "INSERT INTO restaurant (res_acc, res_pass, res_state, res_name, res_add, res_tel, res_email, res_start_time, res_end_time, res_seat_number, single_meal, res_max_num) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			jdbcTemplate.update(sql, values.toArray());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public List<RestaurantVO> searchrestaurantbyname(String searchString) {
		String sql = "SELECT * FROM restaurant WHERE res_name LIKE ?";

		return jdbcTemplate.query(sql, new Object[]{"%" + searchString + "%"}, new RowMapper<RestaurantVO>() {
			@Override
			public RestaurantVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantVO restaurant = new RestaurantVO();
				restaurant.setRestaurantId(rs.getInt("restaurant_id"));
				restaurant.setResAcc(rs.getString("res_acc"));
				restaurant.setResPass(rs.getString("res_pass"));
				restaurant.setResState(rs.getInt("res_state"));
				restaurant.setResName(rs.getString("res_name"));
				restaurant.setResAdd(rs.getString("res_add"));
				restaurant.setResTel(rs.getString("res_tel"));
				restaurant.setResEmail(rs.getString("res_email"));
				restaurant.setResWeb(rs.getString("res_web"));
				restaurant.setResTimestamp(rs.getTimestamp("res_timestamp"));
				restaurant.setResStartTime(rs.getTime("res_start_time"));
				restaurant.setResEndTime(rs.getTime("res_end_time"));
				restaurant.setResTexId(rs.getString("res_tex_id"));
				restaurant.setResOwnerName(rs.getString("res_owner_name"));
				restaurant.setResSeatNumber(rs.getInt("res_seat_number"));
				restaurant.setResIntro(rs.getString("res_intro"));
				restaurant.setSingleMeal(rs.getBoolean("single_meal"));
				restaurant.setResTotalScore(rs.getInt("res_total_score"));
				restaurant.setResTotalNumber(rs.getInt("res_total_number"));
				restaurant.setResMaxNum(rs.getInt("res_max_num"));

				// 處理圖片資料
				byte[] photoBytes = rs.getBytes("res_photo");
				if (photoBytes != null && photoBytes.length > 0) {
					Byte[] photoWrapper = new Byte[photoBytes.length];
					for (int i = 0; i < photoBytes.length; i++) {
						photoWrapper[i] = photoBytes[i];
					}
					restaurant.setResPhoto(photoWrapper);
				} else {
					restaurant.setResPhoto(null);
				}
				return restaurant;
			}
		});
	}


	@Override
	public List<RestaurantVO> login(String account, String password) {
		String sql = "SELECT * FROM restaurant WHERE res_acc = ? AND res_pass = ?";

		return jdbcTemplate.query(sql, new Object[]{account, password}, new RowMapper<RestaurantVO>() {
			@Override
			public RestaurantVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantVO restaurant = new RestaurantVO();
				restaurant.setRestaurantId(rs.getInt("restaurant_id"));
				restaurant.setResAcc(rs.getString("res_acc"));
				restaurant.setResPass(rs.getString("res_pass"));
				restaurant.setResState(rs.getInt("res_state"));
				restaurant.setResName(rs.getString("res_name"));
				restaurant.setResAdd(rs.getString("res_add"));
				restaurant.setResTel(rs.getString("res_tel"));
				restaurant.setResEmail(rs.getString("res_email"));
				restaurant.setResWeb(rs.getString("res_web"));
				restaurant.setResTimestamp(rs.getTimestamp("res_timestamp"));
				restaurant.setResStartTime(rs.getTime("res_start_time"));
				restaurant.setResEndTime(rs.getTime("res_end_time"));
				restaurant.setResTexId(rs.getString("res_tex_id"));
				restaurant.setResOwnerName(rs.getString("res_owner_name"));
				restaurant.setResSeatNumber(rs.getInt("res_seat_number"));
				restaurant.setResIntro(rs.getString("res_intro"));
				restaurant.setSingleMeal(rs.getBoolean("single_meal"));
				restaurant.setResTotalScore(rs.getInt("res_total_score"));
				restaurant.setResTotalNumber(rs.getInt("res_total_number"));
				restaurant.setResMaxNum(rs.getInt("res_max_num"));

				// 處理圖片資料
				byte[] photoBytes = rs.getBytes("res_photo");
				if (photoBytes != null && photoBytes.length > 0) {
					Byte[] photoWrapper = new Byte[photoBytes.length];
					for (int i = 0; i < photoBytes.length; i++) {
						photoWrapper[i] = photoBytes[i];
					}
					restaurant.setResPhoto(photoWrapper);
				} else {
					restaurant.setResPhoto(null);
				}
				return restaurant;
			}
		});
	}

	@Override
	public List<Object> findfollow(String resAcc) {
		String sql = "SELECT DISTINCT account.acc_id, acc_name FROM restaurant JOIN " +
				"saved_res ON restaurant.restaurant_id = saved_res.restaurant_id JOIN account ON " +
				"saved_res.acc_id = account.acc_id WHERE res_acc = ?";

		return jdbcTemplate.query(sql, new Object[]{resAcc}, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				AccountVO account = new AccountVO();
				account.setAccId(rs.getInt("acc_id"));
				account.setAccName(rs.getString("acc_name"));
				return account;
			}
		});
	}

	@Override
	public List<ReservationVO> restaurantfindreservation(String resAcc) {
		String sql = "SELECT reservation_id, reservation_number, reservation_date_starttime, " +
				"reservation_date_endtime, COALESCE(reservation_note, '') AS reservation_note, reservation_state " +
				"FROM restaurant " +
				"JOIN reservation ON restaurant.restaurant_id = reservation.restaurant_id " +
				"JOIN account ON reservation.acc_id = account.acc_id " +
				"WHERE res_acc = ?";

		return jdbcTemplate.query(sql, new Object[]{resAcc}, new RowMapper<ReservationVO>() {
			@Override
			public ReservationVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReservationVO reservation = new ReservationVO();
				reservation.setReservationId(rs.getInt("reservation_id"));
				reservation.setReservationNumber(rs.getInt("reservation_number"));
				reservation.setReservationStartTime(rs.getTimestamp("reservation_date_starttime"));
				reservation.setReservationEndTime(rs.getTimestamp("reservation_date_endtime"));
				reservation.setReservationNote(rs.getString("reservation_note"));
				reservation.setReservationState(rs.getInt("reservation_state"));
				return reservation;
			}
		});
	}

	@Override
	public List<Object> findrestype(String resAcc) {
		String sql = "SELECT res_acc, res_type_name FROM restaurant " +
				"JOIN res_type_detail ON restaurant.restaurant_id = res_type_detail.restaurant_id " +
				"JOIN res_type ON res_type_detail.res_type_id = res_type.res_type_id " +
				"WHERE res_acc = ?";

		return jdbcTemplate.query(sql, new Object[]{resAcc}, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				ResTypeVO restypeVO = new ResTypeVO();
				restypeVO.setResTypeName(rs.getString("res_type_name"));
				return restypeVO;
			}
		});
	}

	@Override
	public List<Object> findprod(String resAcc) {
		String sql = "SELECT DISTINCT prod_name, prod_price FROM restaurant " +
				"JOIN prod ON restaurant.restaurant_id = prod.restaurant_id " +
				"WHERE res_acc = ?";

		return jdbcTemplate.query(sql, new Object[]{resAcc}, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProdVO prodVO = new ProdVO();
				prodVO.setProdName(rs.getString("prod_name"));
				prodVO.setProdPrice(rs.getInt("prod_price"));
				return prodVO;
			}
		});
	}

	@Override
	public List<Object> findcomment(String resAcc) {
		String sql = "SELECT DISTINCT restaurant_comment_score, restaurant_comment_text, " +
				"COALESCE(restaurant_comment_reply_text, '') AS restaurant_comment_reply_text, " +
				"restaurant_comment_datetime, restaurant_comment_reply_datetime, acc_name " +
				"FROM restaurant " +
				"JOIN reservation ON restaurant.restaurant_id = reservation.restaurant_id " +
				"JOIN account ON reservation.acc_id = account.acc_id " +
				"WHERE res_acc = ? AND restaurant_comment_text IS NOT NULL";

		return jdbcTemplate.query(sql, new Object[]{resAcc}, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReservationVO reservationVO = new ReservationVO();
				reservationVO.setRestaurantCommentScore(rs.getInt("restaurant_comment_score"));
				reservationVO.setRestaurantCommentText(rs.getString("restaurant_comment_text"));
				reservationVO.setRestaurantCommentReplyText(rs.getString("restaurant_comment_reply_text"));
				reservationVO.setRestaurantCommentDatetime(rs.getTimestamp("restaurant_comment_datetime"));
				Timestamp replyDatetime = rs.getTimestamp("restaurant_comment_reply_datetime");
				reservationVO.setRestaurantCommentReplyDatetime(replyDatetime != null ? replyDatetime : Timestamp.valueOf(LocalDateTime.now()));
				return reservationVO;
			}
		});
	}

	@Override
	public List<Object> findmyself(String resAcc) {
		String sql = "SELECT DISTINCT restaurant_id, res_photo, res_acc, res_name, res_add, " +
				"res_start_time, res_end_time, res_total_score, res_intro FROM restaurant " +
				"WHERE res_acc = ?";

		return jdbcTemplate.query(sql, new Object[]{resAcc}, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantVO restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantId(rs.getInt("restaurant_id"));
				restaurantVO.setResAcc(rs.getString("res_acc"));
				restaurantVO.setResName(rs.getString("res_name"));
				restaurantVO.setResAdd(rs.getString("res_add"));
				restaurantVO.setResStartTime(rs.getTime("res_start_time"));
				restaurantVO.setResEndTime(rs.getTime("res_end_time"));
				restaurantVO.setResTotalScore(rs.getInt("res_total_score"));
				restaurantVO.setResIntro(rs.getString("res_intro"));

				// 處理圖片資料
				byte[] photoBytes = rs.getBytes("res_photo");
				if (photoBytes != null && photoBytes.length > 0) {
					Byte[] photoWrapper = new Byte[photoBytes.length];
					for (int i = 0; i < photoBytes.length; i++) {
						photoWrapper[i] = photoBytes[i];
					}
					restaurantVO.setResPhoto(photoWrapper);
				} else {
					restaurantVO.setResPhoto(null);
				}
				return restaurantVO;
			}
		});
	}

	@Override
	public List<ProdVO> findpprod(String resAcc) {
		String sql = "SELECT DISTINCT prod_id, prod_name, prod_text, prod_userguide, prod_price, prod_qty, prod_state " +
				"FROM restaurant " +
				"JOIN prod ON restaurant.restaurant_id = prod.restaurant_id WHERE res_acc = ?";

		return jdbcTemplate.query(sql, new Object[]{resAcc}, new RowMapper<ProdVO>() {
			@Override
			public ProdVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProdVO prodVO = new ProdVO();
				prodVO.setProdId(rs.getInt("prod_id"));
				prodVO.setProdName(rs.getString("prod_name"));
				prodVO.setProdText(rs.getString("prod_text"));
				prodVO.setProdUserGuide(rs.getString("prod_userguide"));
				prodVO.setProdPrice(rs.getInt("prod_price"));
				prodVO.setProdQty(rs.getInt("prod_qty"));
				prodVO.setProdState(rs.getInt("prod_state"));
				return prodVO;
			}
		});
	}

	@Override
	public List<Object> findaccount(String resAcc) {
		String sql = "SELECT DISTINCT acc_name, acc_pic FROM restaurant " +
				"JOIN reservation ON restaurant.restaurant_id = reservation.restaurant_id " +
				"JOIN account ON reservation.acc_id = account.acc_id WHERE res_acc = ?";

		return jdbcTemplate.query(sql, new Object[]{resAcc}, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				AccountVO accountVO = new AccountVO();
				accountVO.setAccName(rs.getString("acc_name"));

				// 處理圖片資料
				byte[] accPicBytes = rs.getBytes("acc_pic");
				Byte[] accPic = null;
				if (accPicBytes != null) {
					accPic = new Byte[accPicBytes.length];
					for (int i = 0; i < accPicBytes.length; i++) {
						accPic[i] = accPicBytes[i];
					}
				}
				accountVO.setAccPic(accPic);
				return accountVO;
			}
		});
	}

	@Override
	public int restaurantuploadprod(String key, String prodname, String prodprice, String prodnumber, String prodruler,
									String proddiscribe, String prodstatus, InputStream prodimageStream) {
		String sql = "INSERT INTO prod(restaurant_id, prod_name, prod_text, prod_userguide, prod_price, prod_qty, prod_state, prod_pic) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			if (prodimageStream != null && prodimageStream.available() > 0) {
				jdbcTemplate.update(connection -> {
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setString(1, key);
					pstmt.setString(2, prodname);
					pstmt.setString(3, proddiscribe);
					pstmt.setString(4, prodruler);
					pstmt.setInt(5, Integer.parseInt(prodprice));
					pstmt.setInt(6, Integer.parseInt(prodnumber));
					pstmt.setInt(7, Integer.parseInt(prodstatus));
					pstmt.setBlob(8, prodimageStream);
					return pstmt;
				});
			} else {
				return 2;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return 2;
		}

		return 1;
	}

	@Override
	public int restaurantdeleteprod(String prodName, String restaurantId) {
		String sql = "DELETE FROM prod WHERE restaurant_id = ? AND prod_name = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, restaurantId, prodName);
			return rowsAffected > 0 ? 1 : 2; // 如果影響的行數大於0，則返回1，否則返回2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public int restaurantdeletefollow(String accId, String restaurantId) {
		String sql = "DELETE FROM saved_res WHERE acc_id = ? AND restaurant_id = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, accId, restaurantId);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public int restaurantforgetpassword(String account, String mail) {
		String sql = "SELECT restaurant_id FROM restaurant WHERE res_acc = ? AND res_email = ?";
		try {
			Integer result = jdbcTemplate.query(sql, new Object[]{account, mail}, new ResultSetExtractor<Integer>() {
				@Override
				public Integer extractData(ResultSet rs) throws SQLException {
					return rs.next() ? 1 : 0; // Return 1 if a row is found, otherwise return 0
				}
			});
			return result != null ? result : 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int restaurantforgetpasswordupdatepassword(int b, String account) {
		String sql = "UPDATE restaurant SET res_pass = ? WHERE res_acc = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, b, account);
			return rowsAffected > 0 ? 1 : 0; // Return 1 if rows are affected, otherwise return 0
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}



	@Override
	public int restaurantdeletereservation(String reservationId, String restaurantId) {
		String sql = "DELETE FROM reservation WHERE reservation_id = ? AND restaurant_id = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, reservationId, restaurantId);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public List<Object> restauranthomepagemyself(String resAcc) {
		String sql = "SELECT DISTINCT * FROM restaurant WHERE res_acc = ?";

		return jdbcTemplate.query(sql, new Object[]{resAcc}, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantVO restaurantVO = new RestaurantVO();
				// 從 ResultSet 中提取資料並設定給 RestaurantVO 的各個屬性
				restaurantVO.setRestaurantId(rs.getInt("restaurant_id"));
				restaurantVO.setResAcc(rs.getString("res_acc"));
				restaurantVO.setResPass(rs.getString("res_pass"));
				restaurantVO.setResState(rs.getInt("res_state"));
				restaurantVO.setResName(rs.getString("res_name"));
				restaurantVO.setResAdd(rs.getString("res_add"));
				restaurantVO.setResTel(rs.getString("res_tel"));
				restaurantVO.setResEmail(rs.getString("res_email"));
				restaurantVO.setResWeb(rs.getString("res_web"));
				restaurantVO.setResTimestamp(rs.getTimestamp("res_timestamp"));
				restaurantVO.setResStartTime(rs.getTime("res_start_time"));
				restaurantVO.setResEndTime(rs.getTime("res_end_time"));
				restaurantVO.setResTexId(rs.getString("res_tex_id"));
				restaurantVO.setResOwnerName(rs.getString("res_owner_name"));
				restaurantVO.setResSeatNumber(rs.getInt("res_seat_number"));
				restaurantVO.setResIntro(rs.getString("res_intro"));
				restaurantVO.setSingleMeal(rs.getBoolean("single_meal"));
				restaurantVO.setResTotalScore(rs.getInt("res_total_score"));
				restaurantVO.setResTotalNumber(rs.getInt("res_total_number"));
				restaurantVO.setResMaxNum(rs.getInt("res_max_num"));

				// 處理圖片資料
				byte[] photoBytes = rs.getBytes("res_photo");
				if (photoBytes != null && photoBytes.length > 0) {
					Byte[] photoWrapper = new Byte[photoBytes.length];
					for (int i = 0; i < photoBytes.length; i++) {
						photoWrapper[i] = photoBytes[i];
					}
					restaurantVO.setResPhoto(photoWrapper);
				} else {
					restaurantVO.setResPhoto(null);
				}
				return restaurantVO;
			}
		});
	}

	@Override
	public int restauranthomepageupdatebasic(String resAcc, String resPass, String resName, String resAdd,
											 String resTel, String resEmail, String resSeatNumber, String resStartTime, String resEndTime) {
		String sql = "UPDATE restaurant SET res_pass = ?, res_name = ?, res_add = ?, res_tel = ?, res_email = ?, res_seat_number = ?, res_start_time = ?, res_end_time = ? WHERE res_acc = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, resPass, resName, resAdd, resTel, resEmail, resSeatNumber, resStartTime, resEndTime, resAcc);
			return rowsAffected > 0 ? 1 : 0; // 返回1表示更新成功，否則返回0
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int restaurantuploaddayoff(String restaurantId, String date) {
		String sql = "INSERT INTO restaurant_dayoff (restaurant_id, dayoff) VALUES (?, ?)";
		try {
			int rowsAffected = jdbcTemplate.update(sql, restaurantId, date);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public int restaurantdeletedayoff(String restaurantId) {
		String sql = "DELETE FROM restaurant_dayoff WHERE restaurant_id = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, restaurantId);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public int restaurantddeletetype(String restaurantId) {
		String sql = "DELETE FROM res_type_detail WHERE restaurant_id = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, restaurantId);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public int restaurantuploadtype(String restaurantId, String typeId) {
		String sql = "INSERT INTO res_type_detail (restaurant_id, res_type_id) VALUES (?, ?)";
		try {
			int rowsAffected = jdbcTemplate.update(sql, restaurantId, typeId);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public int restaurantuploadintro(String restaurantId, String intro) {
		String sql = "UPDATE restaurant SET res_intro = ? WHERE restaurant_id = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, intro, restaurantId);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public int restaurantuploadimage(String restaurantId, byte[] image) {
		String sql = "UPDATE restaurant SET res_photo = ? WHERE restaurant_id = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setBytes(1, image);
					pstmt.setString(2, restaurantId);
				}
			});
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public int restaurantuploadad(String restaurantId, String adplan, String adprice, String adstarttime,
								  String adendtime, String strTimestamp, String adcheck) {
		String sql = "INSERT INTO ad (restaurant_id, ad_plan, ad_amount, ad_start_date, ad_end_date, ad_apply_timestamp, ad_check) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			int rowsAffected = jdbcTemplate.update(sql, restaurantId, adplan, adprice, adstarttime, adendtime, strTimestamp, adcheck);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public List<AdVO> restaurantfindad(String restaurantId) {
		String sql = "SELECT ad_id, ad_apply_timestamp, ad_start_date, ad_end_date, ad_amount, ad_plan, ad_check FROM ad WHERE restaurant_id = ?";

		return jdbcTemplate.query(sql, new Object[]{restaurantId}, new RowMapper<AdVO>() {
			@Override
			public AdVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdVO adVO = new AdVO();
				adVO.setAdId(rs.getInt("ad_id"));
				adVO.setAdApplyTimestamp(rs.getTimestamp("ad_apply_timestamp"));
				adVO.setAdStartDate(rs.getDate("ad_start_date"));
				adVO.setAdEndDate(rs.getDate("ad_end_date"));
				adVO.setAdAmount(rs.getInt("ad_amount"));
				adVO.setAdPlan(rs.getInt("ad_plan"));
				adVO.setAdCheck(rs.getInt("ad_check"));
				return adVO;
			}
		});
	}

	@Override
	public int restaurantdeletead(String adId, String restaurantId) {
		String sql = "DELETE FROM ad WHERE ad_id = ? AND restaurant_id = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, adId, restaurantId);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}
	@Override
	public int restaurantupdatecomment(String accName, String restaurantId, String commentInput) {
		String sql = "UPDATE reservation " +
				"JOIN account ON reservation.acc_id = account.acc_id " +
				"SET reservation.restaurant_comment_reply_datetime = CURRENT_TIMESTAMP, " +
				"    reservation.restaurant_comment_reply_text = ? " +
				"WHERE reservation.restaurant_id = ? AND account.acc_name = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, commentInput, restaurantId, accName);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public int restaurantupdateprod(String restaurantId, String prodName, String prodPrice, String prodQty,
									String prodUserGuide, String prodText, String prodState, String prodId) {
		String sql = "UPDATE prod " +
				"SET prod_name = ?, prod_text = ?, prod_userguide = ?, prod_price = ?, prod_qty = ? " +
				"WHERE restaurant_id = ? AND prod_id = ?";
		try {
			int rowsAffected = jdbcTemplate.update(sql, prodName, prodText, prodUserGuide, prodPrice, prodQty, restaurantId, prodId);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public List<RestaurantVO> getcarousel(int var1, int var2, int var3, int var4, int var5) {
		String sql = "SELECT restaurant_id, res_photo, res_acc FROM restaurant WHERE " +
				"restaurant_id = ? OR restaurant_id = ? OR restaurant_id = ? OR restaurant_id = ? OR restaurant_id = ?";
		return jdbcTemplate.query(sql, new Object[]{var1, var2, var3, var4, var5}, new RowMapper<RestaurantVO>() {
			@Override
			public RestaurantVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantVO restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantId(rs.getInt("restaurant_id"));
				restaurantVO.setResAcc(rs.getString("res_acc"));
				byte[] photoBytes = rs.getBytes("res_photo");
				if (photoBytes != null && photoBytes.length > 0) {
					Byte[] photoWrapper = new Byte[photoBytes.length];
					for (int i = 0; i < photoBytes.length; i++) {
						photoWrapper[i] = photoBytes[i];
					}
					restaurantVO.setResPhoto(photoWrapper);
				} else {
					restaurantVO.setResPhoto(null);
				}
				return restaurantVO;
			}
		});
	}

	@Override
	public int restaurantaddmylove(String restaurantId, String accId) {
		String sql = "INSERT INTO saved_res (restaurant_id, acc_id) VALUES (?, ?)";
		try {
			int rowsAffected = jdbcTemplate.update(sql, restaurantId, accId);
			return rowsAffected > 0 ? 1 : 2; // Return 1 if rows are affected, otherwise return 2
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}
}
