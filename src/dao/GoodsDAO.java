package dao;

import model.Goods;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {
    // 获取所有商品信息
    public List<Goods> getAllGoods() {
        List<Goods> goodsList = new ArrayList<>();
        String sql = "SELECT * FROM goods";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setName(rs.getString("name"));
                goods.setCover(rs.getString("cover"));
                goods.setImage1(rs.getString("image1"));
                goods.setImage2(rs.getString("image2"));
                goods.setPrice(rs.getFloat("price"));
                goods.setIntro(rs.getString("intro"));
                goods.setStock(rs.getInt("stock"));
                goods.setTypeId(rs.getInt("type_id"));
                goodsList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }
    public Goods getGoodsById(int id) {
        Goods goods = null;
        String sql = "SELECT * FROM goods WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
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

    public String getGoodsNameById(int goodsId) {
        String goodsName = null;
        String sql = "SELECT name FROM goods WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, goodsId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                goodsName = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsName;
    }

    // 根据商品 ID 获取商品封面
    public String getGoodsCoverById(int id) {
        String cover = null;
        String sql = "SELECT cover FROM goods WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cover = rs.getString("cover");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cover;
    }

    // 更新商品信息
    public void updateGoods(Goods goods) {
        String sql = "UPDATE goods SET name=?, cover=?, image1=?, image2=?, price=?, intro=?, stock=?, type_id=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, goods.getName());
            stmt.setString(2, goods.getCover());
            stmt.setString(3, goods.getImage1());
            stmt.setString(4, goods.getImage2());
            stmt.setFloat(5, goods.getPrice());
            stmt.setString(6, goods.getIntro());
            stmt.setInt(7, goods.getStock());
            stmt.setInt(8, goods.getTypeId());
            stmt.setInt(9, goods.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除商品
    public void deleteGoods(int id) {
        String sql = "DELETE FROM goods WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 添加商品
    public void addGoods(Goods goods) {
        String sql = "INSERT INTO goods (name, price, intro, stock, type_id, cover, image1, image2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, goods.getName());
            stmt.setFloat(2, goods.getPrice());
            stmt.setString(3, goods.getIntro());
            stmt.setInt(4, goods.getStock());
            stmt.setInt(5, goods.getTypeId());
            stmt.setString(6, goods.getCover());
            stmt.setString(7, goods.getImage1());
            stmt.setString(8, goods.getImage2());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 根据商品类型ID获取商品列表
    public List<Goods> getGoodsByTypeId(int typeId) {
        List<Goods> goodsList = new ArrayList<>();
        String sql = "SELECT * FROM goods WHERE type_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, typeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Goods goods = new Goods();
                    goods.setId(rs.getInt("id"));
                    goods.setName(rs.getString("name"));
                    goods.setCover(rs.getString("cover"));
                    goods.setImage1(rs.getString("image1"));
                    goods.setImage2(rs.getString("image2"));
                    goods.setPrice(rs.getFloat("price"));
                    goods.setIntro(rs.getString("intro"));
                    goods.setStock(rs.getInt("stock"));
                    goods.setTypeId(rs.getInt("type_id"));
                    goodsList.add(goods);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

}
