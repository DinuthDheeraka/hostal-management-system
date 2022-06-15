package lk.ijse.hms.dao;

import lk.ijse.hms.entity.SuperEntity;

import java.util.List;

public interface CrudDAO<Entity extends SuperEntity,ID> extends SuperDAO {

    boolean add(Entity entity) throws Exception;

    boolean update(Entity entity) throws Exception;

    boolean delete(ID id) throws Exception;

    Entity find(ID id) throws Exception;

    List<Entity> findAll() throws Exception;

    ID findLastId() throws Exception;
}
