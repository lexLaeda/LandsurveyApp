package com.survey.mole.model.worktracker.employee;

import com.survey.mole.model.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "post")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Post extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "name")
    private String name;
}
