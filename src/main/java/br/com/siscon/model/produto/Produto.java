package br.com.siscon.model.produto;

import br.com.siscon.model.categoria.Categoria;
import br.com.siscon.model.estoque.Estoque;
import br.com.siscon.model.laboratorio.Laboratorio;
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
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nome;

    @NotNull
    private String validade;

    @NotNull
    private Float valor_venda;

    @NotNull
    private Float valor_compra;

    @NotNull
    @Column(unique = true)
    private String cod_barras;

    private Date data;

    private Date data_atualizacao;

    private Boolean disponivel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estoque_id", referencedColumnName = "id", nullable = false)
    private Estoque estoque_id;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id", referencedColumnName = "id", nullable = false)
    private Laboratorio laboratorio_id;

    @ManyToMany
    @JoinTable(name = "produto_categoria", joinColumns = @JoinColumn(name="produto_id")
            ,inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categoria_id;

    @OneToMany(mappedBy = "produto_id")
    private Set<ProdutoVenda> venda_id;

}
