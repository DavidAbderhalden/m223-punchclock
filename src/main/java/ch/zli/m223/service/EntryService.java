package ch.zli.m223.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Entry;
import ch.zli.m223.service.interfaces.IEntityService;

@ApplicationScoped
public class EntryService implements IEntityService<Entry, LocalDateTime> {
    
    @Inject
    private EntityManager entityManager;

    @Override
    @Transactional
    public Entry createEntity(Entry entry) {
        // entityManager.refresh(entry);
        return entityManager.merge(entry);
    }

    @Override
    @Transactional
    public Entry deleteEntity(long entryId) {
        Entry entry = entityManager.find(Entry.class, entryId);
        entityManager.remove(entry);
        return entry;
    }

    @Override
    @Transactional
    public Entry patchEntity(Map<String, LocalDateTime> partialEntryMap, long entryId) {
        Entry entry = entityManager.find(Entry.class, entryId);
        LocalDateTime checkIn = partialEntryMap.get("checkIn");
        LocalDateTime checkOut = partialEntryMap.get("checkOut");
        if (checkIn != null) {
            entry.setCheckIn(checkIn);
        }
        if (checkOut != null) {
            entry.setCheckIn(checkOut);
        }
        entityManager.flush();
        return entry;
    }

    @Override
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry", Entry.class);
        return query.getResultList();
    }
}
