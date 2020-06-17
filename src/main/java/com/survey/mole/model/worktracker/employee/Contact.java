package com.survey.mole.model.worktracker.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.survey.mole.model.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Contact extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @JsonIgnore
    @OneToOne(mappedBy = "contact", fetch = FetchType.LAZY)
    private Employee employee;
}
