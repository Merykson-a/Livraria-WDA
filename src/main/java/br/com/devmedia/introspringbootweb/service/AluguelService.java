package br.com.devmedia.introspringbootweb.service;

import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Editora;
import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.domain.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AluguelService {

    void salvar(Aluguel aluguel, long usuarioId);

    List<Aluguel> recuperarPorUsuario(long usuarioId);

    Aluguel recuperarPorUsuarioIdEAluguelId(long usuarioId, long aluguelId);

    Usuario recuperarPorIdUsuario(long usuarioId);

    List<Livro> recuperarLivro();

    List<Livro> recuperarLivroList();

    void atualizar(Aluguel aluguel, long usuarioId);

    void excluir(long usuarioId, long aluguelId, long livroId);

    Page<Aluguel> findPaginated(long usuarioId, String pesquisa, int pageNo, int pageSize, String sortField, String sortDirection);
}
