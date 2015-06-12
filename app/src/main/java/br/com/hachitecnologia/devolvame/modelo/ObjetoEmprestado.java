package br.com.hachitecnologia.devolvame.modelo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by iaufc26.costa on 26/05/2015.
 */
public class ObjetoEmprestado implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String objeto;
    private Calendar dataEmprestimo;
    private Contato contato = new Contato();
    private byte[] foto;
    private boolean lembreteAtivo;
    private Calendar dalaLembrete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Calendar getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Calendar dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean isLembreteAtivo() {
        return lembreteAtivo;
    }

    public void setLembreteAtivo(boolean lembreteAtivo) {
        this.lembreteAtivo = lembreteAtivo;
    }

    public Calendar getDalaLembrete() {
        return dalaLembrete;
    }

    public void setDalaLembrete(Calendar dalaLembrete) {
        this.dalaLembrete = dalaLembrete;
    }
}
