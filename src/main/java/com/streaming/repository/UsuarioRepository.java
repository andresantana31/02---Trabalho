package com.streaming.repository;

import com.streaming.model.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u JOIN u.perfis p JOIN p.visualizacoes vis GROUP BY u ORDER BY COUNT(vis) DESC")
    List<Usuario> findUsuarioMaisAtivo(Pageable pageable);
}
