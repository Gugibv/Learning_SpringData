package com.grey.entity.array;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
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
        @TypeDef(name = "string-array",typeClass = StringArrayType.class)
})
public class ArrayBean {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Type(type = "string-array")
    @Column(columnDefinition = "text[]")
    private String[] authorName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String[] authorName) {
        this.authorName = authorName;
    }
}
