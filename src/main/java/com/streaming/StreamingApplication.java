package com.streaming;

import com.streaming.model.*;
import com.streaming.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class StreamingApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamingApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UsuarioRepository usuarioRepo,
                           PerfilRepository perfilRepo,
                           CategoriaRepository categoriaRepo,
                           VideoRepository videoRepo,
                           AvaliacaoRepository avaliacaoRepo,
                           VisualizacaoRepository visualizacaoRepo) {
        return args -> {
            System.out.println("\n=== INICIANDO SISTEMA DE STREAMING ===");

            // === USUÁRIOS ===
            Usuario u1 = new Usuario(null, "André", "andre@email.com", "1234", LocalDateTime.now());
            Usuario u2 = new Usuario(null, "Maria", "maria@email.com", "abcd", LocalDateTime.now());
            Usuario u3 = new Usuario(null, "Denis", "denis@email.com", "5678", LocalDateTime.now());
            Usuario u4 = new Usuario(null, "Caio", "caio@email.com", "9012", LocalDateTime.now());
            usuarioRepo.save(u1);
            usuarioRepo.save(u2);
            usuarioRepo.save(u3);
            usuarioRepo.save(u4);

            // === PERFIS ===
            Perfil p1 = new Perfil(null, "Andre-Perfil", u1);
            Perfil p2 = new Perfil(null, "Maria-Perfil", u2);
            Perfil p3 = new Perfil(null, "Denis-Perfil", u3);
            Perfil p4 = new Perfil(null, "Caio-Perfil", u4);
            perfilRepo.save(p1);
            perfilRepo.save(p2);
            perfilRepo.save(p3);
            perfilRepo.save(p4);

            // === CATEGORIAS ===
            Categoria catAcao = new Categoria(null, "Ação");
            Categoria catDrama = new Categoria(null, "Drama");
            Categoria catAnimacao = new Categoria(null, "Animação");
            Categoria catTerror = new Categoria(null, "Terror");
            Categoria catFiccao = new Categoria(null, "Ficção Científica");
            Categoria catAnime = new Categoria(null, "Anime");
            categoriaRepo.save(catAcao);
            categoriaRepo.save(catDrama);
            categoriaRepo.save(catAnimacao);
            categoriaRepo.save(catTerror);
            categoriaRepo.save(catFiccao);
            categoriaRepo.save(catAnime);

            // === FILMES ===
            Video v1 = new Video(null, "Missão Impossível", "Filme de ação e espionagem com Tom Cruise.", 147, catAcao);
            Video v2 = new Video(null, "Missão Secreta", "Outro filme de ação cheio de adrenalina.", 132, catAcao);
            Video v3 = new Video(null, "Drama da Vida", "Um drama emocionante sobre família.", 95, catDrama);
            Video v4 = new Video(null, "Avatar", "Épico de ficção científica em Pandora.", 162, catFiccao);
            Video v5 = new Video(null, "Demon Slayer", "Anime sobre caçadores de demônios.", 117, catAnime);
            Video v6 = new Video(null, "Invocação do Mal", "Terror sobrenatural baseado em fatos reais.", 112, catTerror);
            Video v7 = new Video(null, "2012", "Catástrofe global e fim do mundo.", 158, catFiccao);
            Video v8 = new Video(null, "Velozes e Furiosos", "Corridas e ação com carros.", 106, catAcao);
            Video v9 = new Video(null, "Toy Story", "Aventura de brinquedos que ganham vida.", 81, catAnimacao);
            Video v10 = new Video(null, "O Exorcista", "Clássico do terror sobre possessão.", 122, catTerror);

            videoRepo.save(v1); videoRepo.save(v2); videoRepo.save(v3); videoRepo.save(v4); videoRepo.save(v5);
            videoRepo.save(v6); videoRepo.save(v7); videoRepo.save(v8); videoRepo.save(v9); videoRepo.save(v10);

            // === AVALIAÇÕES ===
            avaliacaoRepo.save(new Avaliacao(null, p1, v1, 9, "Excelente filme de ação!"));
            avaliacaoRepo.save(new Avaliacao(null, p2, v1, 8, "Muito bom"));
            avaliacaoRepo.save(new Avaliacao(null, p1, v2, 7, "Legal mas previsível"));
            avaliacaoRepo.save(new Avaliacao(null, p3, v4, 10, "Avatar é incrível!"));
            avaliacaoRepo.save(new Avaliacao(null, p4, v5, 9, "Demon Slayer é perfeito"));
            avaliacaoRepo.save(new Avaliacao(null, p2, v6, 6, "Muito assustador"));
            avaliacaoRepo.save(new Avaliacao(null, p3, v7, 8, "Efeitos especiais incríveis"));
            avaliacaoRepo.save(new Avaliacao(null, p4, v8, 7, "Ação pura"));

            // === VISUALIZAÇÕES ===
            visualizacaoRepo.save(new Visualizacao(null, p1, v1, LocalDateTime.now().minusDays(1), 100));
            visualizacaoRepo.save(new Visualizacao(null, p2, v1, LocalDateTime.now().minusDays(1), 100));
            visualizacaoRepo.save(new Visualizacao(null, p1, v2, LocalDateTime.now().minusDays(2), 80));
            visualizacaoRepo.save(new Visualizacao(null, p1, v3, LocalDateTime.now().minusDays(3), 50));
            visualizacaoRepo.save(new Visualizacao(null, p3, v4, LocalDateTime.now(), 100));
            visualizacaoRepo.save(new Visualizacao(null, p4, v5, LocalDateTime.now(), 100));
            visualizacaoRepo.save(new Visualizacao(null, p3, v1, LocalDateTime.now().minusHours(2), 75));
            visualizacaoRepo.save(new Visualizacao(null, p4, v7, LocalDateTime.now().minusHours(1), 90));
            visualizacaoRepo.save(new Visualizacao(null, p1, v8, LocalDateTime.now().minusHours(3), 60));
            visualizacaoRepo.save(new Visualizacao(null, p2, v9, LocalDateTime.now().minusHours(4), 100));

            // === CONSULTAS ===

            System.out.println("1) FILMES COM 'MISSÃO' NO TÍTULO:");
            List<Video> filmesMissao = videoRepo.findByTituloContainingIgnoreCaseOrderByTituloAsc("Missão");
            filmesMissao.forEach(v -> System.out.println("   • " + v.getTitulo()));

            System.out.println("\n2) FILMES DA CATEGORIA 'AÇÃO':");
            List<Video> videosAcao = videoRepo.findByCategoriaOrderByTituloAsc(catAcao);
            videosAcao.forEach(v -> System.out.println("   • " + v.getTitulo()));

            System.out.println("\n3) TOP 10 FILMES MAIS BEM AVALIADOS:");
            List<Video> topAvaliados = videoRepo.findTop10ByAvaliacao(PageRequest.of(0,10));
            for (int i = 0; i < topAvaliados.size(); i++) {
                System.out.println("   " + (i+1) + "º " + topAvaliados.get(i).getTitulo());
            }

            System.out.println("\n4) TOP 10 FILMES MAIS ASSISTIDOS:");
            List<Video> topAssistidos = videoRepo.findTop10ByVisualizacoes(PageRequest.of(0,10));
            for (int i = 0; i < topAssistidos.size(); i++) {
                System.out.println("   " + (i+1) + "º " + topAssistidos.get(i).getTitulo());
            }

            System.out.println("\n5) USUÁRIO MAIS ATIVO:");
            List<Usuario> topUser = usuarioRepo.findUsuarioMaisAtivo(PageRequest.of(0,1));
            topUser.forEach(u -> System.out.println(u.getNome()));
        };
    }
}