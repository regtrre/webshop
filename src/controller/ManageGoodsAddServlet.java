package controller;

import dao.GoodsDAO;
import model.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet("/ManageGoodsAddServlet")
public class ManageGoodsAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码方式，防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 使用Apache Commons FileUpload库来解析文件上传
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 图片存放的路径
        String picturePath = getServletContext().getRealPath("/picture");

        // 确保 picture 文件夹存在
        File pictureDir = new File(picturePath);
        if (!pictureDir.exists()) {
            pictureDir.mkdirs();
        }

        // 获取表单中的数据
        String name = "";
        float price = 0;
        String intro = "";
        int stock = 0;
        int typeId = 0;
        String coverFileName = "";
        String image1FileName = "";
        String image2FileName = "";

        // 处理上传的文件和表单数据
        try {
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();

            while (iterator.hasNext()) {
                FileItem item = iterator.next();

                if (item.isFormField()) {
                    // 处理普通表单字段
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString("UTF-8");

                    switch (fieldName) {
                        case "name":
                            name = fieldValue;
                            break;
                        case "price":
                            price = Float.parseFloat(fieldValue);
                            break;
                        case "intro":
                            intro = fieldValue;
                            break;
                        case "stock":
                            stock = Integer.parseInt(fieldValue);
                            break;
                        case "typeId":
                            typeId = Integer.parseInt(fieldValue);
                            break;
                    }
                } else {
                    // 处理文件上传
                    String fieldName = item.getFieldName();
                    String fileName = UUID.randomUUID().toString() + getFileExtension(item.getName());

                    // 保存图片文件
                    File file = new File(picturePath, fileName);

                    if (fieldName.equals("cover")) {
                        coverFileName = fileName;
                    } else if (fieldName.equals("image1")) {
                        image1FileName = fileName;
                    } else if (fieldName.equals("image2")) {
                        image2FileName = fileName;
                    }

                    try (InputStream inputStream = item.getInputStream()) {
                        Files.copy(inputStream, Paths.get(file.getAbsolutePath()));
                    }

                    item.delete(); // 删除临时文件
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("商品添加失败，发生错误：" + e.getMessage());
            return;
        }

        // 创建 Goods 对象并设置其属性
        Goods goods = new Goods();
        goods.setName(name);
        goods.setPrice(price);
        goods.setIntro(intro);
        goods.setStock(stock);
        goods.setTypeId(typeId);
        goods.setCover(coverFileName);  // 只保存文件名
        goods.setImage1(image1FileName);  // 只保存文件名
        goods.setImage2(image2FileName);  // 只保存文件名

        // 将商品信息插入到数据库
        GoodsDAO goodsDAO = new GoodsDAO();
        goodsDAO.addGoods(goods);

        // 重定向到商品管理页面或其他页面
        response.sendRedirect("ManageListGoodsServlet");
    }

    // 获取文件扩展名
    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.lastIndexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }
}