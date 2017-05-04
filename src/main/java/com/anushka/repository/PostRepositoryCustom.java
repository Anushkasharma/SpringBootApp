package com.anushka.repository;

import com.anushka.entity.Post;

import java.util.List;

/**
 * Created by rxd2095 on 5/2/17.
 */
public interface PostRepositoryCustom {

    List<Post> getAllPostCommentsWithACertainId(Long id);

}
