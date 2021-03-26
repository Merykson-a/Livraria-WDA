package br.com.devmedia.introspringbootweb.service;

import br.com.devmedia.introspringbootweb.domain.Editora;
import br.com.devmedia.introspringbootweb.domain.Livro;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EditoraService {

    void salvar(Editora editora);

    List<Editora> recuperar();

    Editora recuperarPorId(long id);

    List<Livro> recuperarLivro(long id);

    void atualizar(Editora editora);

    void excluir(long id);

    Page<Editora> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    Page<Editora> pesquisarEditora(int pageNo, int pageSize, String sortField, String sortDirection, String pesquisa);
}
