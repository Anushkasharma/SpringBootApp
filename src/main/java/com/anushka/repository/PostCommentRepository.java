package com.anushka.repository;

import com.anushka.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by rxd2095 on 5/2/17.
 */
@Component
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
