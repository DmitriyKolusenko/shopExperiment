package entities;

import java.util.ArrayList;
import java.util.List;

public class GoodsList {
    private List<Goods> goodsList = new ArrayList<>();

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
