package model;

public class Order {
    private int id;            // 订单ID
    private String username;   // 用户名
    private int goodsId;       // 商品ID
    private String goodsName;       // 商品名称
    private int amount;        // 商品数量
    private float total;       // 总金额
    private String phone;      // 用户电话
    private String address;    // 收货地址
    private int state;         // 订单状态

    // 构造方法
    public Order(int id, String username, int goodsId, String goodsName,int amount, float total, String phone, String address, int state) {
        this.id = id;
        this.username = username;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.amount = amount;
        this.total = total;
        this.phone = phone;
        this.address = address;
        this.state = state;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
