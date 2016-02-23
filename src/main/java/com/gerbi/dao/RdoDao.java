package com.gerbi.dao;


import com.gerbi.model.Rdo;
import com.gerbi.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;

public class RdoDao {

    private Connection connection = null;

    public RdoDao() {
        this.connection = connection = DbUtil.getConnection();;
    }


    public void addRdo(Rdo rdo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT  INTO rdo(id_rdo, name) VALUES (?, ?)");
            preparedStatement.setLong(1, rdo.getIdRdo());
            preparedStatement.setString(2, rdo.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void updateRdo(Rdo rdo){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rdo SET name = ? WHERE id_rdo  =?");
            preparedStatement.setString(1, rdo.getName());
            preparedStatement.setLong(2, rdo.getIdRdo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Rdo> getAllRdos(){
        ArrayList<Rdo> airports = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM rdo");
            while (rs.next()){
                Rdo rdo = new Rdo();
                rdo.setIdRdo(rs.getInt("id_rdo"));
                rdo.setName(rs.getString("name"));
                airports.add(rdo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return airports;
    }

    public Rdo getRdoById(long rdoId){
        Rdo rdo = new Rdo();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM  rdo WHERE id_rdo = ? ");
            preparedStatement.setLong(1, rdoId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                rdo.setIdRdo(rs.getInt("idRdo"));
                rdo.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rdo;
    }
}
