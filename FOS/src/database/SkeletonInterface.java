package database;


/**
 * Yuecheng Rong
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * A Menus class that deals with the menus table in the database.
 * Including add, edit (update) and get restaurant id.
 */
public interface SkeletonInterface {

    /**
     * Set the message information.
     * @param input the text of the message should appear
     */
    void setMessage(String input);

    /**
     * The main skeleton of the functions in database
     */
    void theSkeleton(int which);

    /** The first thing to do in the skeleton, will be overridden */
    void addMenuInside();

    /** The first thing to do in the skeleton, will be overridden */
    void editMeusInside();

    /** The first thing to do in the skeleton, will be overridden */
    void getMenuIdInside();

    /**
     * This is an abstract method that set the statements
     *
     * @param inputQuery the Query will be executed
     * @param id the restaurant id
     * @param name the name of the restaurant
     * @param which whether get name or not
     * @return the statement after query is executed
     */
    PreparedStatement setStatement(String inputQuery, int id, String name, boolean which, int setInt, int setString);

    /**
     * Gathering the Result Set from the input PreparedStatement
     * @param inputStatement the input PreparedStatement
     * @return the Result Set
     */
    ResultSet gatherResultSet(PreparedStatement inputStatement);

    /**
     * Duplicated from the top one but execute "executeUpdate" and will return an integer
     * that judge whether the update is successed or not
     * @param inputStatement the input PreparedStatement
     * @return the Result Set
     */
    int gatherResult(PreparedStatement inputStatement);


    }
