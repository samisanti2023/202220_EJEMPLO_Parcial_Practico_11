package co.edu.uniandes.dse.parcialejemplo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {
    @Autowired
    MedicoRepository medicoRepository;
    /**
     * Guardar un nuevo libro
     *
     * @param bookEntity La entidad de tipo libro del nuevo libro a persistir.
     * @return La entidad luego de persistirla
     * @throws IllegalOperationException Si el ISBN es inválido o ya existe en la
     *                                   persistencia o si la editorial es inválida
     */
    @Transactional
    public MedicoEntity createMedico(MedicoEntity medicoEntity) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de creación del libro");
        
        String sub=medicoEntity.getRegistro().substring(0,2);
        if (!sub.equals("RM"))
            throw new IllegalOperationException("Registro is not valid");

    
        log.info("Termina proceso de creación del libro");
        return medicoRepository.save(medicoEntity);
    }

}

