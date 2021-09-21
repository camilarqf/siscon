package br.com.siscon.model.produto;

import br.com.siscon.model.venda.Venda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "produto_venda")
@IdClass(ProdutoVendaPK.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVenda {

    @Id
    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
    private Produto produto_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "venda_id", referencedColumnName = "id", nullable = false)
    private Venda venda_id;

    @NotNull
    private int quantidade;

    @NotNull
    private float preco_total;

    @NotNull
    private Date data;

    private float preco_desconto;

}
