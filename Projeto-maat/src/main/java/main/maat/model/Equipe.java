package main.maat.model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
    private int id;
    private String nome;
    private Usuario gerente; // MODIFICADO: De Pessoa para Usuario
    private List<Usuario> membros; // MODIFICADO: De Pessoa para Usuario

    public Equipe() {
        this.membros = new ArrayList<>();
    }

    // Getters e Setters atualizados
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Usuario getGerente() { return gerente; }
    public void setGerente(Usuario gerente) { this.gerente = gerente; }

    public List<Usuario> getMembros() { return membros; }
    public void setMembros(List<Usuario> membros) { this.membros = membros; }

    public void adicionarMembro(Usuario usuario) {
        if (this.membros == null) {
            this.membros = new ArrayList<>();
        }
        this.membros.add(usuario);
    }

    public void removerMembro(Usuario usuario){
        if (this.membros != null) {
            this.membros.remove(usuario);
        }
    }
}