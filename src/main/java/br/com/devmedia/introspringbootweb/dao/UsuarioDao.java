package br.com.devmedia.introspringbootweb.dao;

import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.domain.Usuario;

import java.util.List;

public interface UsuarioDao {

    void salvar(Usuario usuario);

    List<Usuario> recuperar();

    /*    List<Livro> recuperarLivro();*/
    Usuario recuperarPorID(long id);

    List<Aluguel> recuperarAluguel(long id);

    void atualizar(Usuario usuario);

    void excluir(long id);
}
