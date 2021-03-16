package br.com.devmedia.introspringbootweb.service;

import br.com.devmedia.introspringbootweb.dao.EditoraDao;
import br.com.devmedia.introspringbootweb.domain.Editora;
import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class EditoraServiceImpl implements EditoraService {



    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private EditoraDao editoraDao;


    @Override
    public void salvar(Editora editora) {
        editoraDao.salvar(editora);
    }

    @Override
    @Transactional
    public List<Editora> recuperar() {
        return editoraDao.recuperar();
    }

    @Override
    @Transactional
    public Editora recuperarPorId(long id) {
        return editoraDao.recuperarPorID(id);
    }

    @Override
    public List<Livro> recuperarLivro(long id) {
        return editoraDao.recuperarLivro(id);
    }

    @Override
    public void atualizar(Editora editora) {
        editoraDao.atualizar(editora);
    }

    @Override
    public void excluir(long id) {
        editoraDao.excluir(id);
    }

    @Override
    public Page<Editora> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
        return this.editoraRepository.findAll(pageable);
    }

    @Override
    public Page<Editora> pesquisarEditora(int pageNo, int pageSize, String sortField, String sortDirection, String pesquisa) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
        return this.editoraRepository.findByTodos(pesquisa, pageable);
    }
}
