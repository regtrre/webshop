package controller;

import dao.GoodsDAO;
import model.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManageGoodsUpdateServlet")
public class ManageGoodsUpdateServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "picture"; // 文件上传目录

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        GoodsDAO goodsDAO = new GoodsDAO();
        Goods goods = goodsDAO.getGoodsById(id);
        request.setAttribute("goods", goods);
        request.getRequestDispatcher("ManageGoodsUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码方式，防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                Goods goods = new Goods();

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // 处理普通表单字段
                        switch (item.getFieldName()) {
                            case "id":
                                goods.setId(Integer.parseInt(item.getString("UTF-8"))); // 确保使用UTF-8编码
                                break;
                            case "name":
                                goods.setName(item.getString("UTF-8")); // 确保使用UTF-8编码
                                break;
                            case "price":
                                goods.setPrice(Float.parseFloat(item.getString("UTF-8"))); // 确保使用UTF-8编码
                                break;
                            case "intro":
                                goods.setIntro(item.getString("UTF-8")); // 确保使用UTF-8编码
                                break;
                            case "stock":
                                goods.setStock(Integer.parseInt(item.getString("UTF-8"))); // 确保使用UTF-8编码
                                break;
                            case "typeId":
                                goods.setTypeId(Integer.parseInt(item.getString("UTF-8"))); // 确保使用UTF-8编码
                                break;
                        }
                    } else {
                        // 处理文件上传
                        String fileName = new File(item.getName()).getName();
                        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator + fileName;
                        File uploadedFile = new File(uploadPath);
                        item.write(uploadedFile); // 保存文件

                        // 根据字段名设置图片路径
                        switch (item.getFieldName()) {
                            case "cover":
                                goods.setCover(fileName); // 只保存文件名
                                break;
                            case "image1":
                                goods.setImage1(fileName); // 只保存文件名
                                break;
                            case "image2":
                                goods.setImage2(fileName); // 只保存文件名
                                break;
                        }
                    }
                }

                // 更新商品信息
                GoodsDAO goodsDAO = new GoodsDAO();
                goodsDAO.updateGoods(goods);

                // 重定向到商品管理列表
                response.sendRedirect("ManageListGoodsServlet");

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "文件上传或更新商品信息失败，请重试。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "表单类型不正确，请确保使用 multipart/form-data。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
