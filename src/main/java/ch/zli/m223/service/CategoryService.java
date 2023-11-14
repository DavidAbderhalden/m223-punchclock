package ch.zli.m223.service;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Category;
import ch.zli.m223.service.interfaces.IEntityService;

@ApplicationScoped
public class CategoryService implements IEntityService<Category, String> {

    @Inject
    private EntityManager entityManager;

    @Override
    @Transactional
    public Category createEntity(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Override
    @Transactional
    public Category deleteEntity(long categoryId) {
        Category category = entityManager.find(Category.class, categoryId);
        entityManager.remove(category);
        return category;
    }

    @Override
    @Transactional
    public Category patchEntity(Map<String, String> partialCategoryMap, long categoryId) {
        Category category = entityManager.find(Category.class, categoryId);
        String title = partialCategoryMap.get("title");
        if (title != null) {
            category.setTitle(title);
        }
        entityManager.flush();
        return category;
    }

    @Override
    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category", Category.class);
        return query.getResultList();
    }
}
