package br.com.devmedia.introspringbootweb.dao;

import br.com.devmedia.introspringbootweb.domain.Editora;
import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.domain.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository

public class EditoraDaoImpl implements EditoraDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Editora editora) {
        em.persist(editora);
    }

    @Override
    public List<Editora> recuperar() {
        return em.createQuery("select p from Editora p", Editora.class).getResultList();
    }

    @Override
    public Editora recuperarPorID(long id) {
        return em.find(Editora.class, id);
    }

    @Override
    public List<Livro> recuperarLivro(long id) {
        return em.createQuery("select m from Livro m where m.editora.id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void atualizar(Editora editora) {
        em.merge(editora);
    }

    @Override
    public void excluir(long id) {
        em.remove(em.getReference(Editora.class, id));
    }

    @Override
    public List<Editora> pesquisarEditora(String pesquisa) {
        return em.createQuery("select m from Editora m where m.nome like '%" + pesquisa + "%' or m.cidade like '%" + pesquisa + "%'")
                .getResultList();
    }
}
