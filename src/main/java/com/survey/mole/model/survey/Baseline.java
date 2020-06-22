package com.survey.mole.model.survey;

import com.survey.mole.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baseline")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Baseline extends AbstractEntity {

    @Column(name = "baseline_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "baseline_point",
            joinColumns = @JoinColumn(name = "baseline_id"),
            inverseJoinColumns = @JoinColumn(name = "point_id"))
    private List<Point> points = new ArrayList<>();
}
