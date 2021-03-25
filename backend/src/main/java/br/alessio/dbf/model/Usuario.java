package br.alessio.dbf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name", length = 255)
    @Length(min = 5, message = "*Seu Nome ter pelo menos 5 characteres")
    @NotEmpty(message = "*Por favor digite um Nome")
    private String fullName;

    @Column(name = "email", length = 255)
    @Length(min = 5, message = "*Seu Nome ter pelo menos 5 characteres")
    @NotEmpty(message = "*Por favor digite um email")
    private String email;

    @Column(name = "birth_date")
    private Instant birthDate;

    @Column(name = "cpf")
    @CPF(message = "*Por favor digite um cpf válido")
    @NotEmpty(message = "*Por favor digite um cpf")
    private String cpf;

    @Column(name = "login")
    @NotEmpty(message = "*Por favor digite um cpf")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "*Por favor digite uma senha")
    private String password;

    @Column(name = "active")
    private Boolean active;


    public void setId(Integer id) {
        this.id = id;
    }
}
