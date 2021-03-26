package br.com.devmedia.introspringbootweb.domain;


import br.com.devmedia.introspringbootweb.domain.Aluguel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false, length = 60)
    @NotBlank(message = "O campo nome não pode ser vazio!")
    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    @Pattern(regexp="[a-zA-Z á-úÀ-Ú]{3,60}", message = "Insira um nome válido, sem caracteres especiais!")
    private String nome;


    @Column(nullable = false)
    @NotBlank(message = "O campo endereço não pode ser vazio!")
    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    private String endereco;


    @Column(nullable = false)
    @NotBlank(message = "O campo cidade não pode ser vazio!")
    @Size(min = 3, max = 60, message = "No mínimo 3 caracteres e no máximo 60!")
    private String cidade;


    @NotBlank(message = "O campo email não pode ser vazio!")
    @Column(nullable = false)
    @Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Insira um email válido, no formato: Email@email.com!")
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
