package BRG.BRG_ART_ID.dao;

import java.sql.SQLException;
import java.util.*;

import BRG.BRG_ART_ID.domain.User;

public interface UserDao {
    public User login(String email, String password) throws SQLException;
}