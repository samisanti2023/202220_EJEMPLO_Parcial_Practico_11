package co.edu.uniandes.dse.parcialejemplo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;

public interface EspecialidadRepository extends JpaRepository<EspecialidadEntity, Long>{
    
}

