package com.anushka.repository;

import com.anushka.entity.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by rxd2095 on 5/2/17.
 */
public class PostRepositoryImpl implements PostRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Post> getAllPostCommentsWithACertainId(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Post> from = criteriaQuery.from(Post.class);

        CriteriaQuery<Object> select = criteriaQuery.select(from);
        select.orderBy(criteriaBuilder.asc(from.get("id")));
        TypedQuery<Object> typedQuery1 = em.createQuery(select);
        List<Object> resultList = typedQuery1.getResultList();

        return (List<Post>)(Object) resultList;
    }

}
