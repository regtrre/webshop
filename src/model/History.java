package model;

import java.sql.Timestamp;

public class History {
    private String username;   // 用户名
    private int goodsId;       // 商品ID
    private String goodsName;   // 商品名称
    private String goodsCover;  // 商品封面
    private Timestamp currentTime; // 当前时间

    // 构造方法
    public History(String username, int goodsId, String goodsName, String goodsCover, Timestamp currentTime) {
        this.username = username;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsCover = goodsCover;
        this.currentTime = currentTime;
    }

    // Getter 和 Setter 方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCover() {
        return goodsCover;
    }

    public void setGoodsCover(String goodsCover) {
        this.goodsCover = goodsCover;
    }

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }
}
