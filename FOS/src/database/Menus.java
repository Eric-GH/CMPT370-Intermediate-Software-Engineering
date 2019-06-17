package database;

/**
 * Refactored by Yuecheng Rong
 */

import Entities.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Menus extends Skeleton {

    /** Store the information of the menu. */
    private Menu menu;

    /** The first input query */
    private String inputQuery1;

    /** The second input query */
    private String inputQuery2;

    /** The third input query */
    private String inputQuery3;

    /** The first Result set */
    private ResultSet result1;

    /** The second Result set */
    private ResultSet result2;

    /** The id of the menu */
    private int menu_id = -1;

    private int temp = -1;

    /**
     * Get the private element menu_id
     */
    public int getMenu_id(){
        return menu_id;
    }

    /**
     * Constructor
     */
    public Menus(){
        super();
    }

    /**
     * This is what will be executed when call "add menu"
     */
    @Override
    public void addMenuInside(){
        try {
            result1 = gatherResultSet(setStatement(inputQuery1,menu.getRestaurants_id(),menu.getMenu_name(), true,1,2));
            result2 = gatherResultSet(setStatement(inputQuery2,menu.getRestaurants_id(),null, false,1,2));
            if (result1.next()) {
                this.setMessage("This menu name has already used");
            } else if (!result2.next()) {
                setMessage("Did not find the restaurant id");
            } else {
                int affected = gatherResult(setStatement(inputQuery3, menu.getRestaurants_id(), menu.getMenu_name(), true,1,2));
                if (affected > 0) {
                    setMessage("You have successfully add the menu " + menu.getMenu_name());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is what will be executed when call "edit menu"
     */
    @Override
    public void editMeusInside() {
        int value = gatherResult(setStatement(inputQuery3,temp, menu.getMenu_name(), true,2,1));
        if(value > 0) {
           this.setMessage("the new menu has been updated");
        } else {
            this.setMessage("the new menu has not been updated");
        }
    }

    /**
     * This is what will be executed when call "get menu id"
     */
    @Override
    public void getMenuIdInside() {
        try{
            result1 = gatherResultSet(setStatement(inputQuery1,menu.getRestaurants_id(),menu.getMenu_name(), true,1,2));
            //System.out.println(result1);
            if(result1.next()) {
                menu_id = result1.getInt(1);
                this.setMessage("menu found");
            } else {
                this.setMessage("The menu id not found");
            }
        }catch (SQLException e)
        {
            message = e.fillInStackTrace().toString();
        }
    }





    /**
     * Add a new menu into the database
     * @param menu the menu will be added
     */
    public void addMenu(Menu menu){
        String st1 = "select menu_name from menus where restaurant_id = ? and menu_name = ?";
        String st2 = "select id from restaurants where id = ?";
        String st3 = "INSERT INTO menus (restaurant_id, menu_name) VALUES (?,?)";
        this.setValues(menu,st1);
        this.inputQuery2 = st2;
        this.inputQuery3 = st3;
        this.theSkeleton(1);
    }

    /**
     * Edit the name of an existed menu
     * @param oldMenu the existed menu that will be replaced
     * @param newMenu the new menu
     */
    public void editMeus(Menu oldMenu, Menu newMenu){
        String st3 = "update menus set menu_name = ? where menu_id = ?";
        temp = this.getMenuId(oldMenu);
        this.menu = newMenu;
        this.inputQuery3 = st3;
        this.theSkeleton(2);
    }

    /**
     * Get the menu id from the input menu
     * @param menu the input menu
     * @return the id of the input menu
     */
    public int getMenuId(Menu menu) {
        String st1 = "select menu_id from menus where restaurant_id = ? and menu_name = ?";
        this.setValues(menu,st1);
        this.theSkeleton(3);
        return this.getMenu_id();
    }

    /**
     * This function will be used when testing
     * @param restaurant_id the restaurant_id will be tested
     * @return
     */
    public ArrayList<Menu> displayAllMenuInOneRestaurant(int restaurant_id)
    {
        ArrayList<Menu> menus = new ArrayList<>();
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String query = "select menu_name from menus where restaurant_id = "+ restaurant_id;
                Statement statement =  connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next())
                {
                    Menu menu = new Menu(resultSet.getString(1),restaurant_id);
                    menus.add(menu);
                }
                connection.coon.close();
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        else
        {
            message = "lost connection";
        }
        return menus;
    }


    /**
     * Helper function to set the private values
     * @param menu this.menu
     * @param st1 the query will be executed
     */
    private void setValues(Menu menu, String st1){
        this.menu = menu;
        this.inputQuery1 = st1;
    }

}
