package dao;

import model.Order;
import util.DBConnection;
import model.SaleReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Order;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class OrderDAO {
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders (id, username, goods_id, goods_name,amount, total, phone, address, state) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getId());
            pstmt.setString(2, order.getUsername());
            pstmt.setInt(3, order.getGoodsId());
            pstmt.setString(4, order.getGoodsName());
            pstmt.setInt(5, order.getAmount());
            pstmt.setFloat(6, order.getTotal());
            pstmt.setString(7, order.getPhone());
            pstmt.setString(8, order.getAddress());
            pstmt.setInt(9, order.getState());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getNextOrderId() {
        String sql = "SELECT MAX(id) FROM orders";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) + 1; // 返回最大ID + 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // 如果没有订单，返回1
    }

    // 获取用户的订单列表
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("id");
                int goodsId = rs.getInt("goods_id");
                String goodsName = rs.getString("goods_name");
                int amount = rs.getInt("amount");
                float total = rs.getFloat("total");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int state = rs.getInt("state");

                Order order = new Order(orderId, username, goodsId, goodsName, amount, total, phone, address, state);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    // 获取所有订单
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                int goodsId = rs.getInt("goods_id");
                String goodsName = rs.getString("goods_name");
                int amount = rs.getInt("amount");
                float total = rs.getFloat("total");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int state = rs.getInt("state");

                Order order = new Order(id, username, goodsId, goodsName, amount, total, phone, address, state);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    // 更新订单
    public boolean updateOrder(Order order) {
        String sql = "UPDATE orders SET username = ?, goods_id = ?, goods_name = ?, amount = ?, total = ?, phone = ?, address = ?, state = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order.getUsername());
            pstmt.setInt(2, order.getGoodsId());
            pstmt.setString(3, order.getGoodsName());
            pstmt.setInt(4, order.getAmount());
            pstmt.setFloat(5, order.getTotal());
            pstmt.setString(6, order.getPhone());
            pstmt.setString(7, order.getAddress());
            pstmt.setInt(8, order.getState());
            pstmt.setInt(9, order.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 如果更新成功，返回 true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 如果出错，返回 false
        }
    }

    // 获取单个订单
    public Order getOrderById(int id) {
        Order order = null;
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                int goodsId = rs.getInt("goods_id");
                String goodsName = rs.getString("goods_name");
                int amount = rs.getInt("amount");
                float total = rs.getFloat("total");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int state = rs.getInt("state");

                order = new Order(id, username, goodsId, goodsName, amount, total, phone, address, state);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    // 获取销售统计报表
    public List<SaleReport> getSalesReport() {
        List<SaleReport> salesReports = new ArrayList<>();
        String sql = "SELECT goods_id, goods_name, SUM(amount) as total_amount " +
                "FROM orders GROUP BY goods_id, goods_name ORDER BY total_amount DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SaleReport report = new SaleReport();
                report.setGoodsId(rs.getInt("goods_id"));
                report.setGoodsName(rs.getString("goods_name"));
                report.setTotalAmount(rs.getInt("total_amount"));
                salesReports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesReports;
    }
}
