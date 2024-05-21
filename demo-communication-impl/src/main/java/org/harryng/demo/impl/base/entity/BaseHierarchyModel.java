package org.harryng.demo.impl.base.entity;

import java.io.Serializable;

public interface BaseHierarchyModel<Tid extends Serializable> {
    Tid getParentId();
    String getTreepath();
}
