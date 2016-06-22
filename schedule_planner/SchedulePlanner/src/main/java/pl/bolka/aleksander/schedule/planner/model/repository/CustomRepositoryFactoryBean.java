package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by Aleksander Bołka on 2016-06-21.
*/
public class CustomRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends
        JpaRepositoryFactoryBean<R, T, I> {

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {

        return new CustomRepositoryFactory(entityManager);
    }

    private static class CustomRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        public CustomRepositoryFactory(EntityManager entityManager) {
            super(entityManager);

            this.entityManager = entityManager;
        }

        @Override
        protected Object getTargetRepository(RepositoryMetadata metadata) {

            return new CommonCustomRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

            // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
            // to check for QueryDslJpaRepository's which is out of scope.
            return CommonCustomRepositoryImpl.class;
        }
    }
}