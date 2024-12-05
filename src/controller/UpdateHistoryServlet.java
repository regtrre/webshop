package controller;

import dao.HistoryDAO;
import dao.GoodsDAO;
import model.History; // 导入 History 类
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.annotation.WebServlet;


@WebServlet("/UpdateHistoryServlet")
public class UpdateHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int goodsId = Integer.parseInt(request.getParameter("id"));

        if (username != null) {
            HistoryDAO historyDAO = new HistoryDAO();
            GoodsDAO goodsDAO = new GoodsDAO();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            // 获取商品名称和封面
            String goodsName = goodsDAO.getGoodsNameById(goodsId);
            String goodsCover = goodsDAO.getGoodsCoverById(goodsId); // 假设您有这个方法来获取商品封面

            // 创建 History 对象
            History history = new History(username, goodsId, goodsName, goodsCover, currentTime);

            // 检查是否存在记录
            if (historyDAO.recordExists(username, goodsId)) {
                // 如果存在，更新 CurrentTime
                historyDAO.updateCurrentTime(username, goodsId, currentTime);
            } else {
                // 如果不存在，插入新记录
                historyDAO.insertHistory(history); // 使用 History 对象
            }
        }

        // 重定向到商品详情页面
        response.sendRedirect("goodsDetail?id=" + goodsId);
    }
}
