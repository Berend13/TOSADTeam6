package BRG.BRG_ART_ID.dao;

import java.sql.SQLException;
import java.util.*;

import BRG.BRG_ART_ID.domain.User;

public interface UserDao {

    public List<User> findAll() throws SQLException;
    public User findByID(int ID) throws SQLException;
    public User save(User User) throws SQLException;
    public User update(User User) throws SQLException;
    public boolean delete(User User) throws SQLException;
    public User login(String email, String password) throws SQLException;
    public void closeConnection();
}