package br.com.devmedia.introspringbootweb.dao;

import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.domain.Usuario;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AluguelDao{

    void salvar(Aluguel aluguel);
    List<Aluguel> recuperarPorUsuario(long usuarioId);
    Aluguel recuperarPorUsuarioEAluguelId(long usuarioId, long aluguelId);
    Usuario recuperarPorIdUsuario(long usuarioId);
    List<Livro> recuperarLivro();
    List<Livro> recuperarLivroList();
    void atualizar(Aluguel aluguel);
    void excluir(long aluguelId, Date dataDevolucao, long livroId );

}
