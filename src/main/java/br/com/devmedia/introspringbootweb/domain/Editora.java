package br.com.devmedia.introspringbootweb.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Column(nullable = false, length = 60)
    /*@NotBlank(message = "O campo nome não pode ser vazio")*/
    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú]+( [a-zA-ZÀ-Úà-ú]+)*$", message = "Verifique o nome inserido(Sem caracteres especiais, números e/ou espaços no início e fim).")
    private String nome;

    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Column(nullable = false)
    /*@NotBlank(message = "O campo cidade não pode ser vazio")*/
    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú-]+( [a-zA-ZÀ-Úà-ú-]+)*$", message = "Verifique a cidade inserida(Sem caracteres especiais, números e/ou espaços no início e fim).")
    private String cidade;

    @OneToMany
    private List<Livro> livros;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
