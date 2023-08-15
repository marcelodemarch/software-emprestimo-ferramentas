/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.AmigoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PICHAU
 */
public class Amigo {

    private int id;
    private String nome;
    private String telefone;
    private final AmigoDAO dao; 

    public Amigo() {
        this.dao = new AmigoDAO();
    }

    public Amigo( String nome, String telefone, int id) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dao = new AmigoDAO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ArrayList getMinhaLista() {
        return dao.MinhaLista;
    }
    
    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n Telefone: " + this.getTelefone()
                + "\n -----------";
    }

    public boolean InsertAmigoBD(String nome, String telefone) throws SQLException {
        int id = this.maiorID() + 1;
        Amigo objeto = new Amigo(nome, telefone, id);
        dao.InsertAmigoBD(objeto);
        return true;
    }

    public boolean DeleteAmigoBD(int id) {
        dao.DeleteAmigoBD(id);
        return true;
    }

    public boolean UpdateAmigoBD(String nome, String telefone, int id) {
        Amigo objeto = new Amigo(nome, telefone, id);
        dao.UpdateAmigoBD(objeto);
        return true;
    }

    public Amigo carregaAmigo(int id) {
        dao.carregaAmigo(id);
        return null;
    }

    public int maiorID() throws SQLException {  
        return dao.maiorID();
    }
}
