package com.tackedev.seleniumdemo.dao;

import com.tackedev.seleniumdemo.Util.DatabaseUtil;
import com.tackedev.seleniumdemo.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User getUserByUsernameAndPassword(String username, String password)
            throws SQLException, ClassNotFoundException {
        try (Connection con = DatabaseUtil.getConnection()) {
            String sql = "SELECT fullname " +
                    "FROM [user] " +
                    "WHERE username = ? AND password = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);

            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                String fullname = resultSet.getNString(1);
                User user = new User(username, null, fullname);
                return user;
            }
        }
        return null;
    }

}
