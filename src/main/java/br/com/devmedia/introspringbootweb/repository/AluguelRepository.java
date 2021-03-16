package br.com.devmedia.introspringbootweb.repository;

import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Editora;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    /*@Query("select m from Aluguel m where m.usuario.id = ?1 and (m.livro.nome like %?2% or m.usuario.nome like %?2%)")
    Page<Aluguel> findById(long usuarioId, String pesquisa, Pageable pageable);*/

    @Query(value = "SELECT a FROM Aluguel a WHERE a.usuario.id = ?1 and (to_char(a.dataAluguel, 'dd/mm/yyyy') ilike CONCAT('%' , ?2 , '%') or" +
            " to_char(a.dataDevolucao, 'dd/mm/yyyy') ilike CONCAT('%' , ?2 , '%') or" +
            " to_char(a.prevDataDevolucao, 'dd/mm/yyyy') ilike CONCAT('%' , ?2 , '%') or" +
            " a.livro.nome like CONCAT('%' , ?2 , '%') or a.usuario.nome ilike CONCAT('%' , ?2 , '%'))")
    Page<Aluguel> findById(long usuarioId, String pesquisa, Pageable pageable);

}
