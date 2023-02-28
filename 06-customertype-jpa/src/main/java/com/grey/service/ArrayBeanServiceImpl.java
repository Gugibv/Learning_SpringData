package com.grey.service;

import com.grey.entity.array.ArrayBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArrayBeanServiceImpl {

    @Autowired
    private EntityManager em;

    public List findArrayBeanByAuthorNameIn(List<String> authorName) {
        StringBuffer sql = new StringBuffer("SELECT * FROM public.array_bean x");
        String ids = authorName.stream().map(item -> "'" + item + "'").collect(Collectors.joining(","));
        sql.append(" where CAST(array[").append(ids).append("] as text[]) && x.author_name");
        Query query = em.createNativeQuery(sql.toString(), ArrayBean.class);
        List<ArrayBean> resultList = query.getResultList();
        ArrayBean arrayBean = resultList.get(0);
        System.out.println(arrayBean.getAuthorName());
        return resultList;
    }
}