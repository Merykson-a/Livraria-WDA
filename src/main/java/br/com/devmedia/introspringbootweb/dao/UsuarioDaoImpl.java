package br.com.devmedia.introspringbootweb.dao;

import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.domain.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public List<Usuario> recuperar() {
        return em.createQuery("select p from Usuario p", Usuario.class).getResultList();
    }

/*  @Override
    public List<Livro> recuperarLivro() {
        return em.createQuery("select p from Livro p", Livro.class).getResultList();
    }*/

    @Override
    public Usuario recuperarPorID(long id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Aluguel> recuperarAluguel(long id) {
        return em.createQuery("select m from Aluguel m where m.usuario.id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void atualizar(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public void excluir(long id) {
        em.remove(em.getReference(Usuario.class, id));
    }
}
