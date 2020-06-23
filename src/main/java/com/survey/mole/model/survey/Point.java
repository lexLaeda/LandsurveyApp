package com.survey.mole.model.survey;

import com.survey.mole.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "point")
@NoArgsConstructor
@AllArgsConstructor
public class Point extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "x")
    private Double x;

    @Column(name = "y")
    private Double y;

    @Column(name = "h")
    private Double h;

    @JoinColumn(name = "level_reference_id")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private LevelReference levelReference;

}
