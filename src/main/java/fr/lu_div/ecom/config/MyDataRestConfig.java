package fr.lu_div.ecom.config;

import fr.lu_div.ecom.entity.Country;
import fr.lu_div.ecom.entity.Product;
import fr.lu_div.ecom.entity.ProductCategory;
import fr.lu_div.ecom.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};

        // disable HTTP methods for Product: POST, PUt and DELETE
        disableHttpMethods(Product.class, config, theUnsupportedActions);

        // disable HTTP methods for ProductCategory: POST, PUt and DELETE
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);

        // disable HTTP methods for Country: POST, PUt and DELETE
        disableHttpMethods(Country.class, config, theUnsupportedActions);

        // disable HTTP methods for State: POST, PUt and DELETE
        disableHttpMethods(State.class, config, theUnsupportedActions);


        // call an internal helper method
        exposeIds(config);


    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // expose entity ids
        //

        // -get a list of all entiy classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // -Create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // -get the entity types for the entities
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // -expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

    }
}
