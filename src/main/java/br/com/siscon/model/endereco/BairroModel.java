package br.com.siscon.model.endereco;

import br.com.siscon.model.laboratorio.LaboratorioModel;
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
@Table(name = "bairro")
public class BairroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nome;

    @OneToMany(mappedBy = "bairro_id")
    private Set<EnderecoModel> endereco_id;

    @ManyToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "id", nullable = false)
    private CidadeModel cidade_id;
}
