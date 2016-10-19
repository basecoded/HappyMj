package com.s4game.core.data.accessor.write;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.s4game.core.data.IEntity;

/**
 * @author zeusgooogle
 * @date 2015-05-21 下午07:59:25
 */
public class EntityOperate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private IEntity entity;

    private boolean insert;

    private boolean update;

    private boolean delete;

    public EntityOperate(String id) {
        this.id = id;
    }

    private void updateEntity(IEntity entity, boolean copy) {
        if (copy) {
            this.entity = entity.copy();
        } else {
            this.entity = entity;
        }
    }

    public void insert(IEntity entity, boolean copy) {
        if (this.delete) {
            throw new RuntimeException("illeagle [insert] after [delete]," + entity.getClass().getName());
        }
        this.insert = true;
        updateEntity(entity, copy);
    }

    public void update(IEntity entity, boolean copy) {
        this.update = true;
        updateEntity(entity, copy);
    }

    public boolean delete(IEntity entity, boolean copy) {
        if (this.insert) {
            return true;
        }
        this.delete = true;
        updateEntity(entity, copy);
        return false;
    }

    public String getId() {
        return this.id;
    }

    public IEntity getInsert() {
        if (this.insert) {
            return this.entity;
        }
        return null;
    }

    public IEntity getUpdate() {
        if (this.insert) {
            return null;
        }
        if (this.delete) {
            return null;
        }
        if (this.update) {
            return this.entity;
        }
        return null;
    }

    public IEntity getDelete() {
        if (this.delete) {
            return this.entity;
        }
        return null;
    }

    public String getEntityName() {
        if (null != this.entity) {
            return this.entity.getClass().getSimpleName();
        }
        throw new RuntimeException("error,entity is null");
    }

    public String getEntityInfo() {
        if (null != this.entity) {
            return JSONObject.toJSONString(this.entity);
        }
        return null;
    }

}
