package com.streaming.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Video> videos;

    public Categoria() {}

    public Categoria(Long id, String nome) { this.id = id; this.nome = nome; }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public java.util.List<Video> getVideos() { return videos; }
    public void setVideos(java.util.List<Video> videos) { this.videos = videos; }
}
