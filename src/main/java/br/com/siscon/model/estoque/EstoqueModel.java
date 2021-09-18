package br.com.siscon.model.estoque;

import br.com.siscon.model.produto.ProdutoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estoque")
public class EstoqueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int estoque_min;

    private int estoque_max;

    @NotNull
    private int quantidade;

    @OneToOne(mappedBy = "estoque_id")
    private ProdutoModel produto_id;
}
