package ch.zli.m223.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @Transactional
    public Entry deleteEntry(long entryId) {
        Entry entry = entityManager.find(Entry.class, entryId);
        entityManager.remove(entry);
        return entry;
    }

    @Transactional
    public Entry patchEntry(Map<String, LocalDateTime> partialEntry, long entryId) {
        Entry entry = entityManager.find(Entry.class, entryId);
        LocalDateTime checkIn = partialEntry.get("checkIn");
        LocalDateTime checkOut = partialEntry.get("checkOut");
        if (checkIn != null) {
            entry.setCheckIn(checkIn);
        }
        if (checkOut != null) {
            entry.setCheckIn(checkOut);
        }
        entityManager.flush();
        return entry;
    }

    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry", Entry.class);
        return query.getResultList();
    }
}
