package com.survey.mole.model.survey;

import com.survey.mole.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "level_reference")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class LevelReference extends AbstractEntity {

    public LevelReference(String name, Double elevation) {
        this.name = name;
        this.elevation = elevation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_reference_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "elevation")
    private Double elevation;

    @OneToOne(mappedBy = "levelReference",fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Point point;
}
