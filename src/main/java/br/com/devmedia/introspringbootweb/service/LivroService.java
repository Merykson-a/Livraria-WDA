package br.com.devmedia.introspringbootweb.service;

import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Editora;
import br.com.devmedia.introspringbootweb.domain.Livro;

import java.util.List;

public interface LivroService {

    void salvar(Livro livro, long editoraId);
    Livro recuperarPorId(long livroId);
    List<Livro> recuperarPorEditora(long editoraId);
    Livro recuperarPorEditoraIdELivroId(long editoraId, long livroId);
    Editora recuperarPorIdEditora(long editoraId);
    List<Aluguel> recuperarAluguel(long livroId);
    void atualizar(Livro livro, long editoraId);
    void excluir(long editoraId, long livroId);

}
