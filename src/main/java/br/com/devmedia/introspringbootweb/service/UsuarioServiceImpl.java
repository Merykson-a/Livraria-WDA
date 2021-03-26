package br.com.devmedia.introspringbootweb.service;

import br.com.devmedia.introspringbootweb.dao.UsuarioDao;
import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public void salvar(Usuario usuario) {
        usuarioDao.salvar(usuario);
    }

    @Override
    @Transactional
    public List<Usuario> recuperar() {
        return usuarioDao.recuperar();
    }

    @Override
    @Transactional
    public Usuario recuperarPorId(long id) {
        return usuarioDao.recuperarPorID(id);
    }

    @Override
    public List<Aluguel> recuperarAluguel(long id) {
        return usuarioDao.recuperarAluguel(id);
    }

    @Override
    public void atualizar(Usuario usuario) {
        usuarioDao.atualizar(usuario);
    }

    @Override
    public void excluir(long id) {
        usuarioDao.excluir(id);
    }
}
