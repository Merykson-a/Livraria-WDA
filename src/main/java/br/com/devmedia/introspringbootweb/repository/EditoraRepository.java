package br.com.devmedia.introspringbootweb.repository;

import br.com.devmedia.introspringbootweb.domain.Editora;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EditoraRepository extends JpaRepository<Editora, Long> {

    @Query("select m from Editora m where cast(id as text) like %?1%")
    Page<Editora> findByTodos(String pesquisa, Pageable pageable);
}
