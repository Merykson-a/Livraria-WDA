package br.com.devmedia.introspringbootweb.service;

import br.com.devmedia.introspringbootweb.dao.AluguelDao;
import br.com.devmedia.introspringbootweb.dao.LivroDao;
import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Editora;
import br.com.devmedia.introspringbootweb.domain.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class LivroServiceImpl implements LivroService{

    @Autowired
    private LivroDao livroDao;

    @Autowired
    private EditoraService editoraService;

    @Override
    public void salvar(Livro livro, long editoraId) {
        livro.setEditora(editoraService.recuperarPorId(editoraId));
        livroDao.salvar(livro);
    }

    @Override
    public Livro recuperarPorId(long livroId) {
        return livroDao.recuperarPorId(livroId);
    }

    @Override
    public List<Livro> recuperarPorEditora(long editoraId) {
        return livroDao.recuperarPorEditora(editoraId);
    }

    @Override
    public Livro recuperarPorEditoraIdELivroId(long editoraId, long livroId) {
        return livroDao.recuperarPorEditoraELivroId(editoraId, livroId);
    }

    @Override
    public Editora recuperarPorIdEditora(long editoraId) {
        return livroDao.recuperarPorIdEditora(editoraId);
    }

    @Override
    public List<Aluguel> recuperarAluguel(long livroId) {
        return livroDao.recuperarAluguel(livroId);
    }

    @Override
    public void atualizar(Livro livro, long editoraId) {
        livro.setEditora(editoraService.recuperarPorId(editoraId));
        livroDao.atualizar(livro);
    }

    @Override
    public void excluir(long editoraId, long livroId) {
        livroDao.excluir(recuperarPorEditoraIdELivroId(editoraId, livroId).getId());
    }
}
