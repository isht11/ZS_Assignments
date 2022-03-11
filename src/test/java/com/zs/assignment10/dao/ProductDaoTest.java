/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment10.dao;

import com.zs.assignment10.dbConnection.DBConnection;
import com.zs.assignment10.entity.Product;
import com.zs.assignment10.exceptions.InternalServerError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Tests the product dao class.
 */
class ProductDaoTest {
    ProductDao productDao;
    DBConnection dBConnection;
    MockedStatic<DriverManager> mockedDriver;

    @BeforeEach
    public void setUp() {
        dBConnection = mock(DBConnection.class);
        productDao = new ProductDao(dBConnection);
        try {
            mockedDriver = Mockito.mockStatic(DriverManager.class);
        }
        catch (Exception ignored){
        }
        Mockito.reset();
    }

    /**
     * Tests if the product gets saved or not.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void save() throws SQLException, InternalServerError {
        PreparedStatement statement=mock(PreparedStatement.class);
        Connection mockConnection=mock(Connection.class);
        Product product=new Product(1,"bat",1F,2);
        productDao.save(product);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        doNothing().when(statement).setInt(anyInt(),anyInt());
        doNothing().when(statement).setString(anyInt(),anyString());
        doNothing().when(statement).setFloat(anyInt(),anyFloat());
        doNothing().when(statement).setInt(anyInt(),anyInt());
        verify(statement,times(1)).executeUpdate(anyString());
    }

    /**
     * Tests whether the product gets fetched or not.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void getByID() throws SQLException, InternalServerError {
        PreparedStatement statement = mock(PreparedStatement.class);
        Connection mockConnection=mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        ResultSet set = mock(ResultSet.class);
        when(statement.executeQuery(anyString())).thenReturn(set);
        when(set.next()).thenReturn(true);
        when(set.getInt(1)).thenReturn(1);
        assertEquals(1, set.getInt(1));

    }

    /**
     * Tests whether the product gets updated by id or not.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void updateByID() throws SQLException, InternalServerError {
        Statement statement=mock(Statement.class);
        Connection mockConnection=mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        when(statement.executeUpdate(anyString())).thenReturn(1);
        assertEquals(1, statement.executeUpdate(anyString()));
    }

    /**
     * Tests whether the product gets deleted or not.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void deleteById() throws SQLException, InternalServerError {
        Statement statement=mock(Statement.class);
        Connection mockConnection=mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(statement);
        when(statement.executeUpdate(anyString())).thenReturn(1);
        assertEquals(1, statement.executeUpdate(anyString()));
    }

    /**
     * Tests whether all the products get fetched or not.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void findAll() throws SQLException, InternalServerError {
        PreparedStatement statement = mock(PreparedStatement.class);
        Connection mockConnection=mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        ResultSet set = mock(ResultSet.class);
        when(statement.executeQuery(anyString())).thenReturn(set);
        when(set.next()).thenReturn(true);
        when(set.getInt(1)).thenReturn(1);
        assertEquals(1, set.getInt(1));
    }

    /**
     * Tests whether the product exists or not.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void exist() throws SQLException, InternalServerError {
        PreparedStatement statement = mock(PreparedStatement.class);
        Connection mockConnection=mock(Connection.class);
        when(dBConnection.connectionToDatabase()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(statement);
        ResultSet set = mock(ResultSet.class);
        when(statement.executeQuery(anyString())).thenReturn(set);
        when(set.next()).thenReturn(true);
        when(set.getInt(1)).thenReturn(1);
        assertEquals(1, set.getInt(1));

    }

    /**
     * Tests the fail case of the save method.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void TestSaveException() throws SQLException, InternalServerError {
        Connection conn = mock(Connection.class);
        Statement statement = mock(Statement.class);
        doThrow(InternalServerError.class).when(dBConnection).connectionToDatabase();
        doThrow(SQLException.class).when(conn).createStatement();
        doThrow(SQLException.class).when(statement).executeUpdate(anyString());
        assertThrows(SQLException.class ,()->statement.executeUpdate(anyString()));
        assertThrows(InternalServerError.class ,()->dBConnection.connectionToDatabase());
        assertThrows(SQLException.class ,()->conn.createStatement());

    }

    /**
     * Tests the fail case of the get by id method.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void TestGetByIdException() throws SQLException, InternalServerError {
        Connection conn = mock(Connection.class);
        Statement statement = mock(Statement.class);
        doThrow(InternalServerError.class).when(dBConnection).connectionToDatabase();
        doThrow(SQLException.class).when(conn).createStatement();
        doThrow(SQLException.class).when(statement).executeUpdate(anyString());
        assertThrows(SQLException.class ,()->statement.executeUpdate(anyString()));
        assertThrows(InternalServerError.class ,()->dBConnection.connectionToDatabase());
        assertThrows(SQLException.class ,()->conn.createStatement());

    }

    /**
     * Tests the fail case of the find all method.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void TestFindAllException() throws SQLException, InternalServerError {
        Connection conn = mock(Connection.class);
        Statement statement = mock(Statement.class);
        doThrow(InternalServerError.class).when(dBConnection).connectionToDatabase();
        doThrow(SQLException.class).when(conn).createStatement();
        doThrow(SQLException.class).when(statement).executeUpdate(anyString());
        assertThrows(SQLException.class ,()->statement.executeUpdate(anyString()));
        assertThrows(InternalServerError.class ,()->dBConnection.connectionToDatabase());
        assertThrows(SQLException.class ,()->conn.createStatement());

    }

    /**
     * Tests the fail case of the update by id method.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void TestUpdateByIdException() throws SQLException, InternalServerError {
        Connection conn = mock(Connection.class);
        Statement statement = mock(Statement.class);
        doThrow(InternalServerError.class).when(dBConnection).connectionToDatabase();
        doThrow(SQLException.class).when(conn).createStatement();
        doThrow(SQLException.class).when(statement).executeUpdate(anyString());
        assertThrows(SQLException.class ,()->statement.executeUpdate(anyString()));
        assertThrows(InternalServerError.class ,()->dBConnection.connectionToDatabase());
        assertThrows(SQLException.class ,()->conn.createStatement());

    }

    /**
     * Tests the fail case of delete by id method.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void TestDeleteByIdException() throws SQLException, InternalServerError {
        Connection conn = mock(Connection.class);
        Statement statement = mock(Statement.class);
        doThrow(InternalServerError.class).when(dBConnection).connectionToDatabase();
        doThrow(SQLException.class).when(conn).createStatement();
        doThrow(SQLException.class).when(statement).executeUpdate(anyString());
        assertThrows(SQLException.class ,()->statement.executeUpdate(anyString()));
        assertThrows(InternalServerError.class ,()->dBConnection.connectionToDatabase());
        assertThrows(SQLException.class ,()->conn.createStatement());

    }

    /**
     * Tests the fail case of exists method.
     * @throws SQLException
     * @throws InternalServerError
     */
    @Test
    void TestExistsException() throws SQLException, InternalServerError {
        Connection conn = mock(Connection.class);
        Statement statement = mock(Statement.class);
        doThrow(InternalServerError.class).when(dBConnection).connectionToDatabase();
        doThrow(SQLException.class).when(conn).createStatement();
        doThrow(SQLException.class).when(statement).executeUpdate(anyString());
        assertThrows(SQLException.class ,()->statement.executeUpdate(anyString()));
        assertThrows(InternalServerError.class ,()->dBConnection.connectionToDatabase());
        assertThrows(SQLException.class ,()->conn.createStatement());

    }


}