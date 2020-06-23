package com.survey.mole.model.worktracker.employee;

import com.survey.mole.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Post extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

}
