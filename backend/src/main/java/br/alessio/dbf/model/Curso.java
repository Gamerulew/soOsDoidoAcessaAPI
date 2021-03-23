package br.alessio.dbf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 255)
    @Length(min = 5, message = "*Seu Nome deve ter pelo menos 5 characteres")
    @NotEmpty(message = "*Por favor digite um Nome")
    private String nome;

    @Column(name = "professor", length = 255)
    @NotEmpty(message = "*Por favor digite um nome para o professor")
    private String professor;


    @Column(name = "escola", length = 255)
    @NotEmpty(message = "*Por favor digite um nome para a escola")
    private String escola;


    @Column(name = "descricao", length = 255)
    @NotEmpty(message = "*Por favor digite uma descricao")
    private String descricao;
}
