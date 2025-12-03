package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://aluno@127.0.0.1:3306/tc_bd_filmoteca";
        String user = "aluno";
        String password = "aluno";

        return DriverManager.getConnection(url, user, password);

    }

}
