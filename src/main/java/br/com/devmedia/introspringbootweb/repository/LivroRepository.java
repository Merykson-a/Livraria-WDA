package br.com.devmedia.introspringbootweb.repository;

import br.com.devmedia.introspringbootweb.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
