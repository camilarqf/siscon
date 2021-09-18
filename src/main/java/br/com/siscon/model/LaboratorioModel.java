package br.com.siscon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "laboratorio")
public class LaboratorioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nome;

    private String endereco;

    private Date data;

    private Date data_atualizacao;

    @OneToMany(mappedBy="laboratorio_id")
    private Set<ProdutoModel> produto_id;

}
