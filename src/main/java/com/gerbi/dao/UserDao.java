package com.gerbi.dao;


import com.gerbi.model.Rdo;
import com.gerbi.model.User;
import com.gerbi.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {

    private Connection connection = null;

    public UserDao() {
        this.connection = DbUtil.getConnection();
    }

    public void addUser(User user){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (id_user, firstname,"+
                    " lastname, email, password, id_rdo, user_role) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setLong(1, user.getIdUser());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setLong(6, user.getRdo().getIdRdo());
            preparedStatement.setString(7, user.getUserRole());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateUser(User user){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET firstname =?,"+
                    " password=?, id_rdo=?, user_role=? WHERE id_user = ?");
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getRdo().getIdRdo());
            preparedStatement.setString(4, user.getUserRole());
            preparedStatement.setLong(5, user.getIdUser());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()){
                User user = new User();
                RdoDao rdoDao = new RdoDao();
                user.setIdUser(resultSet.getLong("id_user"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRdo(rdoDao.getRdoById(resultSet.getLong("id_rdo")));
                user.setUserRole(resultSet.getString("user_role"));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(long userId){
        User user = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id_user = ?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = resultSet(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByEmail(String email){
        User user = new User();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = resultSet(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    private User resultSet(ResultSet resultSet){
        User user = new User();
        try{
        while (resultSet.next()) {
            RdoDao rdoDao = new RdoDao();
            user.setIdUser(resultSet.getLong("id_user"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRdo(rdoDao.getRdoById(resultSet.getLong("id_rdo")));
            user.setUserRole(resultSet.getString("user_role"));
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

}
