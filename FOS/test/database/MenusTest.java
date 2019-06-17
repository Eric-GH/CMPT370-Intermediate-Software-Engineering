package database;

/**
 * Yuecheng  Rong
 */

import Entities.Menu;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 * Add a new menu everytime doing the test and then delete it
 */
public class MenusTest {

    /** The class that will be tested. */
    private Menus menus = new Menus();
    /** The connection to the database. */
    private GoConnection connection = new GoConnection();
    /** The menu to be added. */
    private Menu oldMenu = new Menu("ABC",1);
    /** The new menu that will replace the old menu. */
    private Menu newMenu = new Menu("DEF",1);
    /** String to be executed */
    private String Query = "DELETE from menus where menu_name = 'ABC'";

    /**
     * Test add menu.
     * After test we need to delete the menu we added.
     * @throws Exception
     */
    @Test
    public void addMenu() throws Exception {

        menus.addMenu(oldMenu);
        assertEquals("You have successfully add the menu ABC", menus.message);
        this.delete();
    }

    /**
     * Test edit the menus
     * @throws Exception
     */
    @Test
    public void editMeus() throws Exception {
        menus.addMenu(oldMenu);
        menus.editMeus(oldMenu,newMenu);
        // System.out.println(menus.message);
        assertEquals("the new menu has been updated", menus.message);
        Query = "DELETE from menus where menu_name = 'DEF'";
        this.delete();
    }

    /**
     * Test get the menu_id
     * @throws Exception
     */
    @Test
    public void getMenuId() throws Exception {
        menus.addMenu(oldMenu);
        menus.getMenuId(oldMenu);
        assertEquals("menu found", menus.message);
        this.delete();
    }

    private void delete(){
        try {
            connection.connect();
            Statement statement = connection.coon.createStatement();
            statement.executeQuery(Query);
            connection.coon.close();
        } catch (SQLException e) {

        }
    }

}