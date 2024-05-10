package org.harryng.demo.impl.organization.service;

import jakarta.annotation.Resource;
import org.harryng.demo.api.organization.dto.OrganizationDto;
import org.harryng.demo.api.organization.entity.OrganizationImpl;
import org.harryng.demo.api.organization.persistence.OrganizationPersistence;
import org.harryng.demo.api.organization.service.OrganizationService;
import org.harryng.demo.impl.base.service.AbstractSearchableService;
import org.harryng.demo.impl.organization.mapper.OrganizationMapper;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl extends AbstractSearchableService<OrganizationDto, OrganizationImpl, Long> implements OrganizationService {
    @Resource
    private OrganizationPersistence organizationPersistence;
    @Resource
    private OrganizationMapper organizationMapper;

    @Override
    public OrganizationPersistence getPersistence() {
        return organizationPersistence;
    }

    @Override
    public OrganizationMapper getMapper() {
        return organizationMapper;
    }
}
