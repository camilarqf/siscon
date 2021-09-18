package br.com.siscon.repository;

import br.com.siscon.model.estoque.EstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<EstoqueModel, Long> {
}
