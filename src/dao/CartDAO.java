package dao;

import model.GoodsCart;
import util.DBConnection;
import model.Goods;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    // 添加商品到购物车
    public boolean addCartItem(String username, int goodId, int quantity) {
        String sql = "INSERT INTO goods_cart (username, good_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, goodId);
            pstmt.setInt(3, quantity);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 如果添加成功，返回 true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取购物车中商品信息
    public Goods getGoodsById(int goodsId) {
        Goods goods = null;
        String sql = "SELECT * FROM goods WHERE id = ?"; // 假设商品信息存储在 goods 表中
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, goodsId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    goods = new Goods();
                    goods.setId(rs.getInt("id"));
                    goods.setName(rs.getString("name"));
                    goods.setCover(rs.getString("cover"));
                    goods.setImage1(rs.getString("image1"));
                    goods.setImage2(rs.getString("image2"));
                    goods.setPrice(rs.getFloat("price"));
                    goods.setIntro(rs.getString("intro"));
                    goods.setStock(rs.getInt("stock"));
                    goods.setTypeId(rs.getInt("type_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    // 获取购物车商品
    public List<GoodsCart> getCartItemsByUsername(String username) {
        List<GoodsCart> cartItems = new ArrayList<>();
        String sql = "SELECT g.id, g.name, g.price, c.quantity, g.cover FROM goods g " +
                "JOIN goods_cart c ON g.id = c.good_id WHERE c.username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                GoodsCart goodsCart = new GoodsCart(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("cover")
                );
                cartItems.add(goodsCart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    // 删除购物车中的商品
    public boolean deleteCartItem(String username, int goodId) {
        String sql = "DELETE FROM goods_cart WHERE username = ? AND good_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, goodId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 如果删除成功，返回 true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
