package br.com.arrys.medicalconsult.consulta.domain;

import br.com.arrys.medicalconsult.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Entity
@Table(name = "CONSULTAS")
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONSULTA")
    private Long idConsulta;
    @Column(name = "DATA_CONSULTA")
    private Date dataConsulta;
    @Column(name = "ESPECIALIDADE")
    private String especialidade;
    @Column(name = "PROFISSIONAL")
    private String profissional;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Consulta(long idConsulta, Date dataConsulta, String especialidade, String profissional, Usuario usuario) {
        this.idConsulta = idConsulta;
        this.dataConsulta = dataConsulta;
        this.especialidade = especialidade;
        this.profissional = profissional;
        this.usuario = usuario;
    }
}