package com.anushka.repository;

import com.anushka.entity.Post;
import com.anushka.utility.AbstractAnushkaTestDataSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rxd2095 on 5/2/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PostRepositoryTest extends AbstractAnushkaTestDataSetup {

    @Test
    public void test() {
        List<Post> postsList = postRepository.getAllPostCommentsWithACertainId(1L);
        assertEquals(1, postsList.size());
    }

}