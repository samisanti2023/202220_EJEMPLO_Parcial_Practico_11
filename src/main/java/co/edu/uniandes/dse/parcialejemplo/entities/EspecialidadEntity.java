package co.edu.uniandes.dse.parcialejemplo.entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Data
@Entity
public class EspecialidadEntity extends BaseEntity{
    private String name;
    private String descripcion;

    @PodamExclude
    @ManyToMany
    private List<MedicoEntity> medicos = new ArrayList<>();
}


