package com.survey.mole.model.survey;

import com.survey.mole.model.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "level_reference")
@EqualsAndHashCode(callSuper = false)
public class LevelReference extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_reference_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "elevation")
    private Double elevation;

}
