package br.com.siscon.repository;

import br.com.siscon.model.laboratorio.LaboratorioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioRepository extends JpaRepository<LaboratorioModel, Long> {

}
