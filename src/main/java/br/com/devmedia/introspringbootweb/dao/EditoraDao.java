package br.com.devmedia.introspringbootweb.dao;

import br.com.devmedia.introspringbootweb.domain.Editora;
import br.com.devmedia.introspringbootweb.domain.Livro;

import java.util.List;

public interface EditoraDao {

    void salvar(Editora editora);
    List<Editora> recuperar();
    Editora recuperarPorID(long id);
    List<Livro> recuperarLivro(long id);
    void atualizar(Editora editora);
    void excluir(long id);
    List<Editora> pesquisarEditora(String pesquisa);
}
