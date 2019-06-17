package Entities;

public class OrderLine implements Comparable {

    private int order_id;
    private int dish_id;
    private int quanity;
    private float price_per_one;
    private float price_total;
    private float discount_total;


    @Override
    public int compareTo(Object o) {
        return 0;
    }


    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public float getPrice_per_one() {
        return price_per_one;
    }

    public void setPrice_per_one(float price_per_one) {
        this.price_per_one = price_per_one;
    }

    public float getPrice_total() {
        return price_total;
    }

    public void setPrice_total(float price_total) {
        this.price_total = price_total;
    }

    public float getDiscount_total() {
        return discount_total;
    }

    public void setDiscount_total(float discount_total) {
        this.discount_total = discount_total;
    }

    public OrderLine(int order_id, int dish_id, int quanity, float discount_total)
    {
        this.order_id = order_id;
        this.dish_id = dish_id;
        this.quanity = quanity;
        this.discount_total = discount_total;
    }

    @Override
    public String toString() {
        return order_id+", "+ dish_id+", "+ quanity+", "+ price_per_one+ ", "+price_total+", "+discount_total;
    }
}
