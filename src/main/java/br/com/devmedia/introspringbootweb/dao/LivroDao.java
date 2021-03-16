package br.com.devmedia.introspringbootweb.dao;



import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Editora;
import br.com.devmedia.introspringbootweb.domain.Livro;

import java.util.List;

public interface LivroDao {

    void salvar(Livro livro);
    Livro recuperarPorId(long livroId);
    List<Livro> recuperarPorEditora(long editoraId);
    Livro recuperarPorEditoraELivroId(long editoraId, long livroId);
    Editora recuperarPorIdEditora(long editoraId);
    List<Aluguel> recuperarAluguel(long livroId);
    void atualizar(Livro livro);
    void excluir(long livroId);
}
