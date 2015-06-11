package br.com.hachitecnologia.devolvame.modelo;

import java.io.Serializable;

/**
 * Created by iaufc26.costa on 26/05/2015.
 */
public class Contato implements Serializable{

    private static final long serialVersionUID = 1L;



    private Integer id;
    private String nome;
    private String telefone;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {

        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
