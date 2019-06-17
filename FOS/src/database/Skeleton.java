package database;

/**
 * Yuecheng Rong
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Skeleton implements SkeletonInterface {

    /** The system message that tells you whether the class is success or not */
    public String message;

    /** The connection to the database */
    private GoConnection connection = new GoConnection();

    /**
     * Set the message information.
     * @param input the text of the message should appear
     */
    public void setMessage(String input) {
        this.message = input;
    }

    /**
     * The main skeleton of the functions in database
     */
    public void theSkeleton(int which) {
        try {
            connection.connect();
            if (connection.coon != null) {
                if(which == 1) {
                    this.addMenuInside();
                } else if(which == 2){
                    this.editMeusInside();
                } else if (which == 3){
                    this.getMenuIdInside();
                }
            }
            connection.coon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /** The first thing to do in the skeleton, will be overridden */
    public void addMenuInside() { }

    /** The first thing to do in the skeleton, will be overridden */
    public void editMeusInside() { }

    /** The first thing to do in the skeleton, will be overridden */
    public void getMenuIdInside() { }



    /**
     * Following are the helper functions
     */


    /**
     * This is an abstract method that set the statements
     *
     * @param inputQuery the Query will be executed
     * @param id the restaurant id
     * @param name the name of the restaurant
     * @param which whether get name or not
     * @return the statement after query is executed
     */
    public PreparedStatement setStatement(String inputQuery, int id, String name, boolean which, int setInt, int setString){
        try {
            PreparedStatement statement = connection.coon.prepareStatement(inputQuery);
            statement.setInt(setInt, id);
            if(which) {
                statement.setString(setString, name);
            }
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gathering the Result Set from the input PreparedStatement
     * @param inputStatement the input PreparedStatement
     * @return the Result Set
     */
    public ResultSet gatherResultSet(PreparedStatement inputStatement){
        ResultSet resultSet = null;
        try {
            resultSet = inputStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * Duplicated from the top one but execute "executeUpdate" and will return an integer
     * that judge whether the update is successed or not
     * @param inputStatement the input PreparedStatement
     * @return the Result Set
     */
    public int gatherResult(PreparedStatement inputStatement){
        int result = -1;
        try {
            result = inputStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
