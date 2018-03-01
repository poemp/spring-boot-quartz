package org.poem.basic;

import org.hibernate.annotations.GenericGenerator;
import org.poem.common.utils.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 基类
 */
@MappedSuperclass
public class IdEntity  implements Serializable{


    private String id;

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "CHAR(36)")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IdEntity that = (IdEntity) o;

        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(that.id) ){
            return false;
        }
        return StringUtils.equals(id,that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
