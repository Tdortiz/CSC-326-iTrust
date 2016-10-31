package edu.ncsu.csc.itrust.model.ndcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;

public class NDCodeMySQL {
    private DataSource ds;
    NDCodeValidator validator;
    
    /**
     * Standard constructor for use in deployment
     * @throws DBException
     */
    public NDCodeMySQL() throws DBException {
        validator = new NDCodeValidator();
        try {
            this.ds = getDataSource();
        } catch (NamingException e) {
            throw new DBException(new SQLException("Context Lookup Naming Exception: " + e.getMessage()));
        }
    }
    
    protected DataSource getDataSource() throws NamingException {
        Context ctx = new InitialContext();
        return ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
    }
    
    /**
     * Constructor for testing purposes
     * @param ds The DataSource to use
     */
    public NDCodeMySQL(DataSource ds) {
        validator = new NDCodeValidator();
        this.ds = ds;
    }
    
    /**
     * Adds an NDCode to the database
     * 
     * @param nd The NDCode to add
     * @return True if the NDCode was successfully added
     * @throws FormValidationException
     * @throws SQLException
     */
    public boolean add(NDCode nd) throws FormValidationException, SQLException{
        validator.validate(nd);
        try (Connection conn = ds.getConnection();
                PreparedStatement pstring = createAddPreparedStatement(conn, nd);){
            return pstring.executeUpdate() > 0;
        }
    }

    /**
     * A utility method used to create a PreparedStatement for the add() method
     * 
     * @param conn The Connection to use
     * @param nd The NDCode that we're adding
     * @return The new PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement createAddPreparedStatement(Connection conn, NDCode nd) throws SQLException {
        PreparedStatement pstring = conn.prepareStatement("INSERT INTO ndcodes(Code, Description) "
                + "VALUES(?, ?)");
        pstring.setString(1, nd.getCode());
        pstring.setString(2, nd.getDescription());
        return pstring;
    }
    
    /**
     * Deletes an NDCode from the database
     * @param nd The NDCode to delete
     * @return True if the record was successfulyl deleted
     * @throws SQLException
     */
    public boolean delete(NDCode nd) throws SQLException{
        try (Connection conn = ds.getConnection();
                PreparedStatement pstring = createDeletePreparedStatement(conn, nd);){
            return pstring.executeUpdate() > 0;
        }
    }
    
    /**
     * A utility method used to create a PreparedStatement for the delete() method
     * 
     * @param conn The Connection to use
     * @param nd The NDCode that we're deleting
     * @return The new PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement createDeletePreparedStatement(Connection conn, NDCode nd) throws SQLException {
        PreparedStatement pstring = conn.prepareStatement("DELETE FROM ndcodes WHERE Code=?");
        pstring.setString(1, nd.getCode());
        return pstring;
    }
    
    /**
     * Gets all NDCodes in the database
     * @return A List<NDCode> containing all NDCodes in the database
     * @throws SQLException
     */
    public List<NDCode> getAll() throws SQLException{
        try (Connection conn = ds.getConnection();
                PreparedStatement pstring = createGetAllPreparedStatement(conn);
                ResultSet rs = pstring.executeQuery()){
            return loadResults(rs);
        }
    }

    /**
     * A utility method used to create a PreparedStatement for the getAll() method
     * 
     * @param conn The Connection to use
     * @return The new PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement createGetAllPreparedStatement(Connection conn) throws SQLException {
        PreparedStatement pstring = conn.prepareStatement("SELECT * FROM ndcodes");
        return pstring;
    }

    /**
     * A utility method that loads all NDCodes in a ResultSet
     * 
     * @param rs The ResultSet to load from
     * @return A List<NDCode> of all NDCodes from the ResultSet
     * @throws SQLException
     */
    private List<NDCode> loadResults(ResultSet rs) throws SQLException {
        List<NDCode> codes = new ArrayList<>();
        while (rs.next()){
            NDCode newNDCode = new NDCode();
            newNDCode.setCode(rs.getString("Code"));
            newNDCode.setDescription(rs.getString("Description"));
            codes.add(newNDCode);
        }
        return codes;
    }
}
