package ch.zli.m223.service;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Tag;
import ch.zli.m223.service.interfaces.IEntityService;

@ApplicationScoped
public class TagService implements IEntityService<Tag, String> {

    @Inject
    private EntityManager entityManager;

    @Override
    @Transactional
    public Tag createEntity(Tag tag) {
        entityManager.persist(tag);
        return tag;
    }

    @Override
    @Transactional
    public Tag deleteEntity(long tagId) {
        Tag tag = entityManager.find(Tag.class, tagId);
        entityManager.remove(tag);
        return tag;
    }

    @Override
    @Transactional
    public Tag patchEntity(Map<String, String> partialTagMap, long tagId) {
        Tag tag = entityManager.find(Tag.class, tagId);
        String title = partialTagMap.get("title");
        if (title != null) {
            tag.setTitle(title);
        }
        entityManager.flush();
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        var query = entityManager.createQuery("FROM Tag", Tag.class);
        return query.getResultList();
    }
}
