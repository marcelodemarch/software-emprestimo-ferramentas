/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.FerramentaDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PICHAU
 */
public class Ferramenta {

    private int id;
    private String nome;
    private String marca;
    private double custo;
    private int estoque;
    private final FerramentaDAO dao;

    public Ferramenta() {
        this.dao = new FerramentaDAO();
    }

    public Ferramenta(String nome, String marca, double custo, int estoque, int id) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.custo = custo;
        this.estoque = estoque;
        this.dao = new FerramentaDAO();
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    public ArrayList getMinhaLista() {
        return dao.MinhaLista;
    }

    public boolean InsertFerramentaBD(String nome, String marca, double custo, int estoque) throws SQLException {
        int id = this.maiorID() + 1;
        Ferramenta objeto = new Ferramenta(nome, marca, custo, estoque, id);
        dao.InsertFerramentaBD(objeto);
        return true;
    }

    public boolean DeleteFerramentaBD(int id) {
        dao.DeleteFerramentaBD(id);
        return true;
    }
    public boolean UpdateFerramentaBD(String nome, String marca, double custo, int estoque , int id) {
        Ferramenta objeto = new Ferramenta(nome, marca, custo, estoque, id);
        dao.UpdateFerramentaBD(objeto);
        return true;
    }

    public Ferramenta carregaFerramenta(int id) {
        dao.carregaFerramenta(id);
        return null;
    }

    public int maiorID() throws SQLException {  
        return dao.maiorID();
    }
}
