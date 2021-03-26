package br.com.devmedia.introspringbootweb.service;

import br.com.devmedia.introspringbootweb.dao.AluguelDao;
import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.domain.Usuario;
import br.com.devmedia.introspringbootweb.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional

public class AluguelServiceImpl implements AluguelService {


    @Autowired
    private AluguelDao aluguelDao;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AluguelRepository aluguelRepository;

    @Override
    public void salvar(Aluguel aluguel, long usuarioId) {
        aluguel.setUsuario(usuarioService.recuperarPorId(usuarioId));
        aluguelDao.salvar(aluguel);
    }

    @Override
    @Transactional
    public List<Aluguel> recuperarPorUsuario(long usuarioId) {
        return aluguelDao.recuperarPorUsuario(usuarioId);
    }

    @Override
    @Transactional
    public Aluguel recuperarPorUsuarioIdEAluguelId(long usuarioId, long aluguelId) {
        return aluguelDao.recuperarPorUsuarioEAluguelId(usuarioId, aluguelId);
    }

    @Override
    public Usuario recuperarPorIdUsuario(long usuarioId) {
        return aluguelDao.recuperarPorIdUsuario(usuarioId);
    }

    @Override
    public List<Livro> recuperarLivro() {
        return aluguelDao.recuperarLivro();
    }

    @Override
    public List<Livro> recuperarLivroList() {
        return aluguelDao.recuperarLivroList();
    }

    @Override
    public void atualizar(Aluguel aluguel, long usuarioId) {
        aluguel.setUsuario(usuarioService.recuperarPorId(usuarioId));
        aluguelDao.atualizar(aluguel);
    }

    @Override
    public void excluir(long usuarioId, long aluguelId, long livroId) {
        aluguelDao.excluir(recuperarPorUsuarioIdEAluguelId(usuarioId, aluguelId).getId(), recuperarPorUsuarioIdEAluguelId(usuarioId, aluguelId).getDataDevolucao(), livroId);
    }

    @Override
    public Page<Aluguel> findPaginated(long usuarioId, String pesquisa, int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.aluguelRepository.findById(usuarioId, pesquisa, pageable);
    }
}
