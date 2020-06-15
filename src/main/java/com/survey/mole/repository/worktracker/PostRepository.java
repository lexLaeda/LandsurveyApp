package com.survey.mole.repository.worktracker;

import com.survey.mole.model.worktracker.employee.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
