package com.anushka.repository;

import com.anushka.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by rxd2095 on 5/2/17.
 */
@Component
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
