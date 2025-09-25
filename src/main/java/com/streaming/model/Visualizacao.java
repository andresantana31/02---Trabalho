package com.streaming.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "VISUALIZACAO")
public class Visualizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    private int progresso;

    public Visualizacao() {}

    public Visualizacao(Long id, Perfil perfil, Video video, LocalDateTime dataHora, int progresso) {
        this.id = id; this.perfil = perfil; this.video = video; this.dataHora = dataHora; this.progresso = progresso;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }
    public Video getVideo() { return video; }
    public void setVideo(Video video) { this.video = video; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public int getProgresso() { return progresso; }
    public void setProgresso(int progresso) { this.progresso = progresso; }
}
