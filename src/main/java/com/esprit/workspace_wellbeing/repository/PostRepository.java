package com.esprit.workspace_wellbeing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esprit.workspace_wellbeing.entity.Post;
import com.esprit.workspace_wellbeing.entity.Role;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
