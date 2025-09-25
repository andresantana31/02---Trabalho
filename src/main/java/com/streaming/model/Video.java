package com.streaming.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "VIDEO")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String titulo;

    private String descricao;

    private int duracao; // duração em minutos

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private List<Visualizacao> visualizacoes;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;

    public Video() {}

    public Video(Long id, String titulo, String descricao, int duracao, Categoria categoria) {
        this.id = id; this.titulo = titulo; this.descricao = descricao; this.duracao = duracao; this.categoria = categoria;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public java.util.List<Visualizacao> getVisualizacoes() { return visualizacoes; }
    public void setVisualizacoes(java.util.List<Visualizacao> visualizacoes) { this.visualizacoes = visualizacoes; }
    public java.util.List<Avaliacao> getAvaliacoes() { return avaliacoes; }
    public void setAvaliacoes(java.util.List<Avaliacao> avaliacoes) { this.avaliacoes = avaliacoes; }
}
