/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Ferramenta;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 1072319140
 */
public class FerramentaDAO {
    public static ArrayList<Ferramenta> MinhaLista = new ArrayList<Ferramenta>();
    
    private ConexaoDAO conexaoDAO;
    
    public FerramentaDAO() {
        conexaoDAO = new ConexaoDAO();
    }
    
    public int maiorID() throws SQLException {

        int maiorID = 0;
        try {
            Statement stmt = conexaoDAO.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_ferramenta");
            res.next();
            maiorID = res.getInt("id");

            stmt.close();

        } catch (SQLException ex) {
        }

        return maiorID;
    }

    public ArrayList getMinhaLista() {
        
        MinhaLista.clear();

        try {
            Statement stmt = conexaoDAO.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_ferramenta");
            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");
                String marca = res.getString("marca");
                double custo = res.getDouble("custo");
                int estoque = res.getInt("estoque");

                Ferramenta objeto = new Ferramenta(nome, marca, custo, estoque, id);

                MinhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return MinhaLista;
    }
    
    public boolean InsertFerramentaBD(Ferramenta objeto) {
        String sql = "INSERT INTO tb_ferramenta(id,nome,marca,custo,estoque) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement stmt = conexaoDAO.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getMarca());
            stmt.setDouble(4, objeto.getCusto());
            stmt.setInt(5, objeto.getEstoque());
            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public boolean DeleteFerramentaBD(int id) {
        try {
            Statement stmt = conexaoDAO.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_ferramenta WHERE id = " + id);
            stmt.close();            
            
        } catch (SQLException erro) {
        }
        
        return true;
    }

    public boolean UpdateFerramentaBD(Ferramenta objeto) {

        String sql = "UPDATE tb_ferramenta set nome = ? ,marca = ?, custo = ?, estoque = ? WHERE id = ?";

        try {
            PreparedStatement stmt = conexaoDAO.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getMarca());
            stmt.setDouble(3, objeto.getCusto());
            stmt.setInt(4, objeto.getEstoque());
            stmt.setInt(5, objeto.getId());
            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public Ferramenta carregaFerramenta(int id) {
        
        Ferramenta objeto = new Ferramenta();
        objeto.setId(id);
        
        try {
            Statement stmt = conexaoDAO.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_ferramenta WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setMarca(res.getString("marca"));
            objeto.setCusto(res.getDouble("custo"));
            objeto.setEstoque(res.getInt("estoque"));

            stmt.close();            
            
        } catch (SQLException erro) {
        }
        return objeto;
    }
}