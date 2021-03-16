package br.com.devmedia.introspringbootweb.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String nome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
