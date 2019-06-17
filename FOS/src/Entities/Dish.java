package Entities;

public class Dish implements Comparable {
    private int menus_id;
    private String dish_name;
    private float dish_prices;


    public int getMenus_id() {
        return menus_id;
    }

    public void setMenus_id(int menus_id) {
        this.menus_id = menus_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public float getDish_prices() {
        return dish_prices;
    }

    public void setDish_prices(float dish_prices) {
        this.dish_prices = dish_prices;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public Dish(int menus_id, String dish_name, float dish_prices)
    {
        this.menus_id = menus_id;
        this.dish_name = dish_name;
        this.dish_prices = dish_prices;
    }

    @Override
    public String toString() {
        return this.menus_id+", "+this.dish_name+", "+this.dish_prices;
    }
}
