package br.com.siscon.model.venda;

import br.com.siscon.model.produto.ProdutoVendaModel;
import br.com.siscon.model.usuario.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "venda")
public class VendaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String forma_pagamento;

    @NotNull
    private Date data;

    private float desconto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioModel usuario_id;

    @OneToMany(mappedBy = "venda_id")
    private Set<ProdutoVendaModel> produto_id;

    @NotNull
    private int quantidade;

    @NotNull
    private float preco_total;

    private float preco_desconto;

    @NotNull
    private Date data_venda;
}
