package com.survey.mole.model.survey;

import com.survey.mole.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "level_reference")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
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
