package br.com.devmedia.introspringbootweb.dao;

import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.domain.Usuario;
import br.com.devmedia.introspringbootweb.service.AluguelService;
import br.com.devmedia.introspringbootweb.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository

public class AluguelDaoImpl implements AluguelDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private LivroService livroService;

    @Override
    public void salvar(Aluguel aluguel) {

        Livro livro = livroService.recuperarPorId(aluguel.getLivro().getId());
        livro.setAlugados(livro.getAlugados() + 1);
        livroService.atualizar(livro, livro.getEditora().getId());

        /*Livro livro = aluguel.getLivro();
        long id = livro.getId();
        em.createQuery("update Livro u set u.alugados = u.alugados + 1 where u.id = :id")
                .setParameter("id", id)
                .executeUpdate();*/
        em.merge(aluguel);
    }

    @Override
    public List<Aluguel> recuperarPorUsuario(long usuarioId) {
        return em.createQuery("select m from Aluguel m where m.usuario.id = :usuarioId", Aluguel.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

    @Override
    public Aluguel recuperarPorUsuarioEAluguelId(long usuarioId, long aluguelId) {
        return em.createQuery("select m from Aluguel m where m.usuario.id = :usuarioId and m.id = :aluguelId", Aluguel.class)
                .setParameter("usuarioId", usuarioId)
                .setParameter("aluguelId", aluguelId)
                .getSingleResult();
    }

    @Override
    public Usuario recuperarPorIdUsuario(long usuarioId) {
        return em.createQuery("select m from Usuario m where m.id = :usuarioId", Usuario.class)
                .setParameter("usuarioId", usuarioId)
                .getSingleResult();
    }

    @Override
    public List<Livro> recuperarLivro() {
        return em.createQuery("select p from Livro p where p.alugados < p.quantidade", Livro.class).getResultList();
    }

    @Override
    public List<Livro> recuperarLivroList() {
        return em.createQuery("select p from Livro p", Livro.class).getResultList();
    }

    @Override
    public void atualizar(Aluguel aluguel) {
        if(aluguel.getDataDevolucao() != null){
            Livro livro = livroService.recuperarPorId(aluguel.getLivro().getId());
            livro.setAlugados(livro.getAlugados() - 1);
            livroService.atualizar(livro,livro.getEditora().getId());
        }
        em.merge(aluguel);
    }

    @Override
    public void excluir(long aluguelId, Date dataDevolucao, long livroId) {

        if(dataDevolucao == null) {
            Livro livro = livroService.recuperarPorId(livroId);
            livro.setAlugados(livro.getAlugados() - 1);
            livroService.atualizar(livro, livro.getEditora().getId());
        }
        //Query Update
        /*em.createQuery("update Livro u set u.alugados = u.alugados - 1 where u.id = :livroId")
                .setParameter("livroId", livroId)
                .executeUpdate();*/
        em.remove(em.getReference(Aluguel.class, aluguelId));
    }
}
