package br.com.siscon.repository;

import br.com.siscon.model.produto.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel,Long> {

}
