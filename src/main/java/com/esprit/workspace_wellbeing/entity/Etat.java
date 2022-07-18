package com.esprit.workspace_wellbeing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.esprit.workspace_wellbeing.enums.EtatName;

@Entity
@Table(name = "etats")
public class Etat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private EtatName name;

    public Etat() {}

    public Etat(EtatName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtatName getName() {
        return name;
    }

    public void setName(EtatName name) {
        this.name = name;
    }
}
