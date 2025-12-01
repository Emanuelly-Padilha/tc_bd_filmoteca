package br.edu.ifpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
     private static Connection conexao;


    private ConnectionFactory(){}


    public static Connection getConnection(){
        try {
            if(conexao == null || conexao.isClosed()){
                String url = "jdbc:mysql://localhost:3306/filmoteca";
                String user = "aluno";
                String password = "aluno";
                conexao = DriverManager.getConnection(url, user, password);
                System.out.println("Conectado á Filmoteca com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println(" Erro ao conectar com o banco:");
            e.printStackTrace();
        }
        return conexao;
    }

}
