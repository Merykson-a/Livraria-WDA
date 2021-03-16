package br.com.devmedia.introspringbootweb.dao;

import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Editora;
import br.com.devmedia.introspringbootweb.domain.Livro;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository

public class LivroDaoImpl implements LivroDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void salvar(Livro livro) {
        em.persist(livro);
    }

    @Override
    public Livro recuperarPorId(long livroId) {
        return em.createQuery("select m from Livro m where m.id = :livroId", Livro.class)
                .setParameter("livroId", livroId)
                .getSingleResult();
    }

    @Override
    public List<Livro> recuperarPorEditora(long editoraId) {
        return em.createQuery("select m from Livro m where m.editora.id = :editoraId")
                .setParameter("editoraId", editoraId)
                .getResultList();
    }

    @Override
    public Livro recuperarPorEditoraELivroId(long editoraId, long livroId) {
        return em.createQuery("select m from Livro m where m.editora.id = :editoraId and m.id = :livroId", Livro.class)
                .setParameter("editoraId", editoraId)
                .setParameter("livroId", livroId)
                .getSingleResult();
    }

    @Override
    public Editora recuperarPorIdEditora(long editoraId) {
        return em.createQuery("select m from Editora m where m.id = :editoraId", Editora.class)
                .setParameter("editoraId", editoraId)
                .getSingleResult();
        }

    @Override
    public List<Aluguel> recuperarAluguel(long livroId) {
        return em.createQuery("select f from Aluguel f where f.livro.id = :livroId", Aluguel.class)
                .setParameter("livroId", livroId)
                .getResultList();
    }

    @Override
    public void atualizar(Livro livro) {
        em.merge(livro);
    }

    @Override
    public void excluir(long livroId) {
        em.remove(em.getReference(Livro.class, livroId));
    }
}
