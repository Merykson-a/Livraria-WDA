package br.com.devmedia.introspringbootweb.domain;

import com.sun.istack.NotNull;
import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataAluguel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDevolucao;

    @Column(nullable = false, length = 50)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date prevDataDevolucao;


    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Livro livro;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setDataAluguel(Date dataAluguel) {

        this.dataAluguel = dataAluguel;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getDataAluguel() {

        return dataAluguel;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getPrevDataDevolucao() {
        return prevDataDevolucao;
    }

    public void setPrevDataDevolucao(Date prevDataDevolucao) {
        this.prevDataDevolucao = prevDataDevolucao;
    }
}
