package com.survey.mole.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AbstractEntity {

    @Column(name = "created" , updatable = false)
    private LocalDateTime created;

    @Column(name = "updated", insertable = false)
    private LocalDateTime updated;

    @PrePersist
    public void toCreate(){
        if (created == null){
            setCreated(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void toUpdate(){
        setUpdated(LocalDateTime.now());
    }
}
