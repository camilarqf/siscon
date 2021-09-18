package br.com.siscon.repository;

import br.com.siscon.model.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<VendaModel, Long> {
}
