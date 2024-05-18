package com.br.domain.model;

import lombok.*;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_mobil")
@Entity
public class Mobil implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "mobil_id")
	private Long mobilId;

    @Column(name = "sigla_mobil")
    private String siglaMobil;

    @CreationTimestamp
    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "marca_mobil_adicionada",
            joinColumns = @JoinColumn(name = "mobil_id"),
            inverseJoinColumns = @JoinColumn(name = "mark_id"))
    private List<Mark> marcas = new ArrayList<>();

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "mobil", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Movement> movimentacoes = new ArrayList<>();

    @Column(name = "ult_movimentacao_id")
    private Long ultimaMovimentacaoId;

    @OneToOne
    private Document documento;

}
