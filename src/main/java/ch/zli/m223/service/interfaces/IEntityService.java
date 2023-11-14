package ch.zli.m223.service.interfaces;

import java.util.List;
import java.util.Map;

public interface IEntityService<T, V> {
    public T createEntity(T entity);
    public T deleteEntity(long id);
    public T patchEntity(Map<String, V> partialEntityMap, long id);
    public List<T> findAll();
}
