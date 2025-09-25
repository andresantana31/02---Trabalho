package com.streaming.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "PERFIL")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_perfil", unique = true, nullable = false)
    private String nomePerfil;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<Visualizacao> visualizacoes;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;

    public Perfil() {}

    public Perfil(Long id, String nomePerfil, Usuario usuario) {
        this.id = id; this.nomePerfil = nomePerfil; this.usuario = usuario;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomePerfil() { return nomePerfil; }
    public void setNomePerfil(String nomePerfil) { this.nomePerfil = nomePerfil; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public java.util.List<Visualizacao> getVisualizacoes() { return visualizacoes; }
    public void setVisualizacoes(java.util.List<Visualizacao> visualizacoes) { this.visualizacoes = visualizacoes; }
    public java.util.List<Avaliacao> getAvaliacoes() { return avaliacoes; }
    public void setAvaliacoes(java.util.List<Avaliacao> avaliacoes) { this.avaliacoes = avaliacoes; }
}
