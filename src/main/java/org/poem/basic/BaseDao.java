package org.poem.basic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author poem
 */
@NoRepositoryBean
public interface BaseDao<T,I extends String> extends JpaRepository<T,I> ,JpaSpecificationExecutor<T> {
}
