package br.com.devmedia.introspringbootweb.repository;

import br.com.devmedia.introspringbootweb.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
