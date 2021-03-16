package br.com.devmedia.introspringbootweb.service;

import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.domain.Usuario;

import java.util.List;

public interface UsuarioService {

    void salvar(Usuario usuario);
    List<Usuario> recuperar();
/*    List<Livro> recuperarLivro();*/
    Usuario recuperarPorId(long id);
    List<Aluguel> recuperarAluguel(long id);
    void atualizar(Usuario usuario);
    void excluir(long id);

}
