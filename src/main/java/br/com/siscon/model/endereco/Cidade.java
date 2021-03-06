package br.com.siscon.model.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nome;

    @OneToMany(mappedBy = "cidade_id")
    private Set<Bairro> bairro_id;

    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "id", nullable = false)
    private Estado estado_id;

}
