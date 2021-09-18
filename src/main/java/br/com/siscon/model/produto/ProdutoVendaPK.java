package br.com.siscon.model.produto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ProdutoVendaPK implements Serializable {
    private Long produto_id;
    private Long venda_id;
}
