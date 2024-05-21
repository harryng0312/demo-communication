package org.harryng.demo.impl.organization.service;

import lombok.RequiredArgsConstructor;
import org.harryng.demo.impl.base.service.AbstractSearchableService;
import org.harryng.demo.impl.organization.dto.OrganizationDto;
import org.harryng.demo.impl.organization.entity.OrganizationImpl;
import org.harryng.demo.impl.organization.mapper.OrganizationMapper;
import org.harryng.demo.impl.organization.persistence.OrganizationPersistence;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl extends AbstractSearchableService<OrganizationDto, OrganizationImpl, Long> implements OrganizationService {
    private final OrganizationPersistence organizationPersistence;
    private final OrganizationMapper organizationMapper;

    @Override
    public OrganizationPersistence getPersistence() {
        return organizationPersistence;
    }

    @Override
    public OrganizationMapper getMapper() {
        return organizationMapper;
    }
}
