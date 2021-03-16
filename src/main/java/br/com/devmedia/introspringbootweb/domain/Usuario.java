package br.com.devmedia.introspringbootweb.domain;


import br.com.devmedia.introspringbootweb.domain.Aluguel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 60)
    @NotBlank(message = "O campo nome não pode ser vazio")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "O campo endereço não pode ser vazio")
    private String endereco;

    @Column(nullable = false)
    @NotBlank(message = "O campo cidade não pode ser vazio")
    private String cidade;

    @NotBlank(message = "O campo email não pode ser vazio")
    @Column(nullable = false)
    private String email;

    @OneToMany
    private List<Aluguel> alugueis;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }
}
