package com.game.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

/**
 * CRUD 写日志
 */
@MappedSuperclass
public abstract class BaseSeriaObject implements Serializable {

    /**
     * Returns a multi-line String with key=value pairs.
     * 
     * @return a String representation of this class.
     */
    @Override
    public abstract String toString();

    /**
     * Compares object equality. When using Hibernate, the primary key should not be a part of this comparison.
     * 
     * @param o
     *            object to compare to
     * @return true/false based on equality tests
     */
    @Override
    public abstract boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are equals() and hashCode() importation" for more information: http://www.hibernate.org/109.html
     * http://www.91linux.com/html/article/program/java/20080530/11875.html
     * 
     * @return hashCode
     */
    @Override
    public abstract int hashCode();

    /**
     * 数据采集过程标记业务+orm唯一约束，非持久化
     */
    @Transient
    @XmlTransient
    protected Long sessCode;

    public Long getSessCode() {
        return sessCode;
    }

    public void setSessCode(Long sessCode) {
        this.sessCode = sessCode;
    }
}
