package co.edu.uniandes.dse.parcialejemplo.services;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de Books
 *
 * @author ISIS2603
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoEspecialidadService.class)
public class MedicoEspecialidadServiceTest {
    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;

    @Autowired
    private MedicoEspecialidadService especialidadService;
    

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<MedicoEntity> medicoList = new ArrayList<>();
    

    /**
     * Configuración inicial de la prueba.
     */
    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from MedicoEntity");
        
        entityManager.getEntityManager().createQuery("delete from EspecialidadEntity");
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        
        

        for (int i = 0; i < 3; i++) {
            MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
            
            entityManager.persist(medicoEntity);
            medicoList.add(medicoEntity);
        }

        
    }

    /**
     * Prueba para crear un Book
     */
    @Test
    void testAddEspecialidad() throws EntityNotFoundException, IllegalOperationException {
        EspecialidadEntity newEspecialidad = factory.manufacturePojo(EspecialidadEntity.class);
        entityManager.persist(newEspecialidad);
        
        MedicoEntity medico = factory.manufacturePojo(MedicoEntity.class);
        entityManager.persist(medico);
        
        medicoEspecialidadService.addEspecialidad(medico.getId(), newEspecialidad.getId());
        
        assertTrue(medico.getEspecialidades().contains(newEspecialidad));
    }
    
    /**
     * Prueba para asociar un autor que no existe a un libro.
     *
     */
    @Test
    void testAddInvalidEspecialidad() {
        assertThrows(EntityNotFoundException.class, ()->{
            EspecialidadEntity newEspecialidad = factory.manufacturePojo(EspecialidadEntity.class);
            
            entityManager.persist(newEspecialidad);
            medicoEspecialidadService.addEspecialidad(newEspecialidad.getId(), 0L);
        });
    }
    @Test
    void testAddMEdicoInvalidEspecialidad() throws EntityNotFoundException, IllegalOperationException {
        assertThrows(EntityNotFoundException.class, ()->{
            MedicoEntity medico = factory.manufacturePojo(MedicoEntity.class);
            entityManager.persist(medico);
            medicoEspecialidadService.addEspecialidad(0L, medico.getId());
        });
    }}
    




    

