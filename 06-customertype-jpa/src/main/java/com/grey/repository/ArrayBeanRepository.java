package com.grey.repository;

import com.grey.entity.array.ArrayBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArrayBeanRepository extends JpaRepository<ArrayBean,String> {

    List<ArrayBean> findByAuthorName(String[] authorName);

    @Query(value = "SELECT * FROM public.array_bean x where CAST(array[:authorName] as text[]) && x.author_name",nativeQuery = true)
    List<ArrayBean> findByAuthorNameIn2(@Param("authorName") List<String> authorName);

    /**
     * @param authorName
     * @return
     */
    @Query(value = "SELECT * FROM public.array_bean x where CAST(string_to_array(?1,',') as text[]) && x.author_name",nativeQuery = true)
    List<ArrayBean> findByAuthorNameIn2(String authorName);
}