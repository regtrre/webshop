package dao;

import model.History; // 导入 History 类
import util.DBConnection;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class HistoryDAO {
    // 检查记录是否存在
    public boolean recordExists(String username, int goodsId) {
        String sql = "SELECT COUNT(*) FROM history WHERE username = ? AND goods_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, goodsId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 更新 CurrentTime
    public void updateCurrentTime(String username, int goodsId, Timestamp currentTime) {
        String sql = "UPDATE history SET CurrentTime = ? WHERE username = ? AND goods_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, currentTime);
            pstmt.setString(2, username);
            pstmt.setInt(3, goodsId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 插入新记录
    public void insertHistory(History history) {
        String sql = "INSERT INTO history (username, goods_id, goods_name, goods_cover, CurrentTime) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, history.getUsername());
            pstmt.setInt(2, history.getGoodsId());
            pstmt.setString(3, history.getGoodsName());
            pstmt.setString(4, history.getGoodsCover());
            pstmt.setTimestamp(5, history.getCurrentTime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据用户名获取历史记录
    public List<History> getHistoryByUsername(String username) {
        List<History> historyList = new ArrayList<>();
        String query = "SELECT g.id AS goods_id, g.name AS goods_name, g.cover AS goods_cover, h.CurrentTime " +
                "FROM history h " +
                "JOIN goods g ON h.goods_id = g.id " +
                "WHERE h.username = ? " +
                "ORDER BY h.CurrentTime DESC"; // 按时间降序排序

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                History history = new History(
                        username,
                        rs.getInt("goods_id"),
                        rs.getString("goods_name"),
                        rs.getString("goods_cover"),
                        rs.getTimestamp("CurrentTime")
                );

                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }
}
