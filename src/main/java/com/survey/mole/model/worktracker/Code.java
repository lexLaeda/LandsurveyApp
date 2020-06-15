package com.survey.mole.model.worktracker;


import com.survey.mole.model.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "code")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Code extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;
}
