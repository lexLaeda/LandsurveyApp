package com.survey.mole.model.survey;

import com.survey.mole.model.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "point")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "baseline_point",
            joinColumns = @JoinColumn(name = "baseline_id"),
            inverseJoinColumns = @JoinColumn(name = "point_id"))
    private Baseline baseline;

}
