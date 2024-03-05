package co.edu.uniandes.dse.parcialejemplo.services;
import java.util.List;
import java.util.Optional;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    /**
     * Asocia un Book existente a un Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param bookId   Identificador de la instancia de Book
     * @return Instancia de BookEntity que fue asociada a Author
     */

    @Transactional
    public EspecialidadEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
        log.info("Inicia proceso de asociarle un libro al autor con id = {0}", medicoId);
        Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);
        Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);

        if (medicoEntity.isEmpty())
            throw new EntityNotFoundException("ErrorMessage.Medico_NOT_FOUND");

        if (especialidadEntity.isEmpty())
            throw new EntityNotFoundException("ErrorMessage.Especialidad_NOT_FOUND");

        especialidadEntity.get().getMedicos().add(medicoEntity.get());
        medicoEntity.get().getEspecialidades().add(especialidadEntity.get());
        log.info("Termina proceso de asociarle un libro al autor con id = {0}", medicoId);
        return especialidadEntity.get();
    }
        
}







