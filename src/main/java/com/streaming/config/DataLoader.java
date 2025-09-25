package com.streaming.config;

import com.streaming.model.*;
import com.streaming.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(UsuarioRepository usuarioRepo,
                               PerfilRepository perfilRepo,
                               CategoriaRepository categoriaRepo,
                               VideoRepository videoRepo,
                               AvaliacaoRepository avaliacaoRepo,
                               VisualizacaoRepository visualizacaoRepo) {
        return args -> {
            // --- Insere usuários e perfis ---
            Usuario u1 = new Usuario();
            u1.setNome("André");
            u1.setEmail("andre@email.com");
            u1.setSenha("1234");
            u1.setDataCadastro(LocalDateTime.now());
            usuarioRepo.save(u1);

            Perfil p1 = new Perfil();
            p1.setNomePerfil("Andre-Perfil");
            p1.setUsuario(u1);
            perfilRepo.save(p1);

            Usuario u2 = new Usuario();
            u2.setNome("Maria");
            u2.setEmail("maria@email.com");
            u2.setSenha("abcd");
            u2.setDataCadastro(LocalDateTime.now());
            usuarioRepo.save(u2);

            Perfil p2 = new Perfil();
            p2.setNomePerfil("Maria-Perfil");
            p2.setUsuario(u2);
            perfilRepo.save(p2);

            // --- Categorias ---
            Categoria catAcao = new Categoria();
            catAcao.setNome("Ação");
            categoriaRepo.save(catAcao);

            Categoria catDrama = new Categoria();
            catDrama.setNome("Drama");
            categoriaRepo.save(catDrama);

            // --- Videos ---
            Video v1 = new Video();
            v1.setTitulo("Missão Impossível");
            v1.setDescricao("Filme de ação e espionagem.");
            v1.setDuracao(120);
            v1.setCategoria(catAcao);
            videoRepo.save(v1);

            Video v2 = new Video();
            v2.setTitulo("Missão Secreta");
            v2.setDescricao("Sequência de Missão.");
            v2.setDuracao(110);
            v2.setCategoria(catAcao);
            videoRepo.save(v2);

            Video v3 = new Video();
            v3.setTitulo("Drama da Vida");
            v3.setDescricao("Um drama emocionante.");
            v3.setDuracao(95);
            v3.setCategoria(catDrama);
            videoRepo.save(v3);

            // --- Avaliacoes ---
            Avaliacao av1 = new Avaliacao();
            av1.setPerfil(p1);
            av1.setVideo(v1);
            av1.setNota(9);
            av1.setComentario("Ótimo!");
            avaliacaoRepo.save(av1);

            Avaliacao av2 = new Avaliacao();
            av2.setPerfil(p2);
            av2.setVideo(v1);
            av2.setNota(8);
            avaliacaoRepo.save(av2);

            Avaliacao av3 = new Avaliacao();
            av3.setPerfil(p1);
            av3.setVideo(v2);
            av3.setNota(7);
            avaliacaoRepo.save(av3);

            // --- Visualizacoes ---
            Visualizacao vis1 = new Visualizacao();
            vis1.setPerfil(p1);
            vis1.setVideo(v1);
            vis1.setDataHora(LocalDateTime.now());
            vis1.setProgresso(100);
            visualizacaoRepo.save(vis1);

            Visualizacao vis2 = new Visualizacao();
            vis2.setPerfil(p2);
            vis2.setVideo(v1);
            vis2.setDataHora(LocalDateTime.now());
            vis2.setProgresso(100);
            visualizacaoRepo.save(vis2);

            Visualizacao vis3 = new Visualizacao();
            vis3.setPerfil(p1);
            vis3.setVideo(v2);
            vis3.setDataHora(LocalDateTime.now());
            vis3.setProgresso(80);
            visualizacaoRepo.save(vis3);

            // --- Consultas solicitadas ---
            System.out.println("\n--- Buscar vídeos pelo título contendo 'Missão' (ordenado) ---");
            List<Video> filmesMissao = videoRepo.findByTituloContainingIgnoreCaseOrderByTituloAsc("Missão");
            filmesMissao.forEach(v -> System.out.println(v.getTitulo()));

            System.out.println("\n--- Todos os vídeos da categoria 'Ação' ordenados por título ---");
            List<Video> acoes = videoRepo.findByCategoriaOrderByTituloAsc(catAcao);
            acoes.forEach(v -> System.out.println(v.getTitulo()));

            System.out.println("\n--- Top 10 vídeos mais bem avaliados ---");
            videoRepo.findTop10ByAvaliacao(PageRequest.of(0, 10)).forEach(v -> System.out.println(v.getTitulo()));

            System.out.println("\n--- Top 10 vídeos mais assistidos ---");
            videoRepo.findTop10ByVisualizacoes(PageRequest.of(0, 10)).forEach(v -> System.out.println(v.getTitulo()));

            System.out.println("\n--- Usuário que mais assistiu vídeos ---");
            usuarioRepo.findUsuarioMaisAtivo(PageRequest.of(0, 1)).forEach(u -> System.out.println(u.getNome()));
        };
    }
}
