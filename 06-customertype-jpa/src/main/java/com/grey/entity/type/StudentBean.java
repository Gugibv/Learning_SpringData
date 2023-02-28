package com.grey.entity.type;

import com.grey.config.type.CourseUserType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@TypeDefs({
        @TypeDef(name = "course",typeClass = CourseUserType.class),
})
public class StudentBean {

    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Type(type = "course")
    @org.hibernate.annotations.Columns(columns = {
            @Column(name = "course"),
            @Column(name = "grade")
    })
    private CourseBean courseBean;
}
