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

    @Query(value = "SELECT a FROM Aluguel a WHERE a.usuario.id = ?1 and (to_char(a.dataAluguel, 'dd/mm/yyyy') like CONCAT('%' , ?2 , '%') or" +
            " to_char(a.dataDevolucao, 'dd/mm/yyyy') like CONCAT('%' , ?2 , '%') or" +
            " to_char(a.prevDataDevolucao, 'dd/mm/yyyy') like CONCAT('%' , ?2 , '%') or" +
            " lower(a.livro.nome) like lower(CONCAT('%' , ?2 , '%')) or lower(a.usuario.nome) like lower(CONCAT('%' , ?2 , '%')) or" +
            " cast(id as text) like %?2%)")
    Page<Aluguel> findById(long usuarioId, String pesquisa, Pageable pageable);

    @Query("select count(m) from Aluguel m where m.prevDataDevolucao < m.dataDevolucao")
    public long noA();

    @Query("select count(m) from Aluguel m where m.prevDataDevolucao >= m.dataDevolucao")
    public long noP();

    @Query("select count(m) from Aluguel m where m.dataDevolucao is null and m.prevDataDevolucao < CURRENT_DATE")
    public long emA();

    @Query("select count(m) from Aluguel m where m.dataDevolucao is null and m.prevDataDevolucao >= CURRENT_DATE")
    public long emP();

}
