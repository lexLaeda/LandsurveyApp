package com.survey.mole.model.worktracker.employee;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.AbstractEntity;
import com.survey.mole.model.worktracker.EmployeeDay;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Table(name = "employee_history")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class EmployeeHistory extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_history")
    private Long id;

    @OneToOne(mappedBy = "employeeHistory")
    @ToString.Exclude
    private Employee employee;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkPeriod> periods = new ArrayList<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EmployeeDay> attendances = new ArrayList<>();

    public void addNewPeriod(WorkPeriod period) {
        periods.add(period);
    }

    public WorkPeriod getLastPeriod(){
        return periods.stream().max(Comparator.comparing(WorkPeriod::getStart)).orElseThrow(ElementNotFoundException::new);
    }

    public void closeLastPeriod(LocalDate end) {
        WorkPeriod last = getLastPeriod();
        for(WorkPeriod period : periods){
            if (period.equals(last)){
                period.setEnd(end);
                break;
            }
        }
    }
}
