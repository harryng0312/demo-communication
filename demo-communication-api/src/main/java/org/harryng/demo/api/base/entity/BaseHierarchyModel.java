package org.harryng.demo.api.base.entity;

import java.io.Serializable;

public interface BaseHierarchyModel<Tid extends Serializable> {
    Tid getParentId();
    String getTreepath();
}
