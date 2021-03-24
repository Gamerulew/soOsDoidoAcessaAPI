package br.alessio.dbf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 255)
    @Length(min = 5, message = "*Seu Nome deve ter pelo menos 5 characteres")
    @NotEmpty(message = "*Por favor digite um Nome")
    private String name;

    @Column(name = "date")
    private Instant date;

    @Column(name = "category", length = 255)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "*Por favor digite um valor")
    private Category category;

    @Column(name = "value", length = 255)
    @NotNull(message = "*Por favor digite um valor")
    private Float value;

    @Column(name = "type", length = 255)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "*Por favor digite um valor")
    private ContaType type;

    @Column(name = "active", length = 255)
    private boolean active;
}
