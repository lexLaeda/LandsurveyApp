package com.survey.mole.model.survey;

import com.survey.mole.model.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baseline")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Baseline extends AbstractEntity {

    @Column(name = "baseline_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Point> points = new ArrayList<>();

}
