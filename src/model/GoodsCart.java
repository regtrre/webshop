package model;

public class GoodsCart {
    private int goodId;       // 商品ID
    private String name;      // 商品名称
    private float price;      // 单品价格
    private int quantity;     // 数量
    private String cover;     // 商品封面

    // 构造方法
    public GoodsCart(int goodId, String name, float price, int quantity, String cover) {
        this.goodId = goodId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.cover = cover;
    }

    // Getter 和 Setter 方法
    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
