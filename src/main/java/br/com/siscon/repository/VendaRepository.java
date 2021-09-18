package br.com.siscon.repository;

import br.com.siscon.model.venda.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<VendaModel, Long> {
}
