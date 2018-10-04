package entities;

public class Goods {
    private String goodsName;
    private int price;
    private String category;
    private String parameters;
    private int weight;
    private int volume;
    private int inStock;

    public Goods(String goodsName, int price, String parameters, int weight, int volume, int inStock){
        this.goodsName = goodsName;
        this.price = price;
//        this.category = category;
        this.parameters = parameters;
        this.weight = weight;
        this.volume = volume;
        this.inStock = inStock;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
}
