package br.com.devmedia.introspringbootweb.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Column(nullable = false, length = 50)
    /*@NotBlank(message = "O nome não pode ser vazio")*/
    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú:,-]+( [a-zA-ZÀ-Úà-ú:,-]+)*$", message = "Insira um nome válido, entre 3 e 60 caracteres, sem caracteres especiais, números e/ou espaços no início!!")
    private String nome;

    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Column(nullable = false, length = 50)
    /*@NotBlank(message = "O autor não pode ser vazio")*/
    @Pattern(regexp="^[a-zA-ZÀ-Úà-ú-´`']+( [a-zA-ZÀ-Úà-ú-´`']+)*$", message = "Insira um autor válido, entre 3 e 60 caracteres, sem caracteres especiais, números e/ou espaços no início!")
    private String autor;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "O lançamento não pode ser vazio")
    private String lancamento;

    @Column(nullable = false, length = 50)
    @Range(min = 1, message = "Deve possui pelo menos 1 livro")
    private int quantidade;

    private int alugados;

    @ManyToOne
    private Editora editora;

    @OneToMany
    private List<Aluguel> aluguel;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public void setAluguel(List<Aluguel> aluguel) {
        this.aluguel = aluguel;
    }

    public List<Aluguel> getAluguel() {
        return aluguel;
    }

    public void setAlugados(int alugados) {
        this.alugados = alugados;
    }

    public int getAlugados() {
        return alugados;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

}
