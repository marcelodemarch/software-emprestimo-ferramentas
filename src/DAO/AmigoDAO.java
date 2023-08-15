package DAO;

import Model.Amigo;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AmigoDAO {
    
    private ConexaoDAO conexaoDAO;

    public static ArrayList<Amigo> MinhaLista = new ArrayList<Amigo>();

    public AmigoDAO() {
        conexaoDAO = new ConexaoDAO();
    }

    public int maiorID() throws SQLException {

        int maiorID = 0;
        try {
            Statement stmt = conexaoDAO.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_amigo");
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
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_amigo");
            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");
                String telefone = res.getString("telefone");

                Amigo objeto = new Amigo(nome, telefone, id);

                MinhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return MinhaLista;
    }

    public boolean InsertAmigoBD(Amigo objeto) {
        String sql = "INSERT INTO tb_amigo(id,nome,telefone) VALUES(?,?,?)";

        try {
            PreparedStatement stmt = conexaoDAO.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getTelefone());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public boolean DeleteAmigoBD(int id) {
        try {
            Statement stmt = conexaoDAO.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_amigo WHERE id = " + id);
            stmt.close();            
            
        } catch (SQLException erro) {
        }
        
        return true;
    }

    public boolean UpdateAmigoBD(Amigo objeto) {

        String sql = "UPDATE tb_amigo set nome = ? ,telefone = ? WHERE id = ?";

        try {
            PreparedStatement stmt = conexaoDAO.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTelefone());
            stmt.setInt(3, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public Amigo carregaAmigo(int id) {
        
        Amigo objeto = new Amigo();
        objeto.setId(id);
        
        try {
            Statement stmt = conexaoDAO.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_amigo WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setTelefone(res.getString("telefone"));

            stmt.close();            
            
        } catch (SQLException erro) {
        }
        return objeto;
    }
}