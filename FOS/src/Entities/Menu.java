package Entities;

/**
 * Created by Kocur on 2017-10-24.
 * Modified By Yinsheng Dong on 2017-11-28
 */
public class Menu
{
    private String menu_name;
    private int restaurants_id;

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public void setRestaurants_id(int restaurants_id)
    {
        this.restaurants_id = restaurants_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public int getRestaurants_id()
    {
        return restaurants_id;
    }

    public Menu(String menu_name, int restaurants_id)
    {
        this.restaurants_id = restaurants_id;
        this.menu_name = menu_name;
    }

    public String toString()
    {
        return this.restaurants_id+", "+ this.menu_name;
    }
}
