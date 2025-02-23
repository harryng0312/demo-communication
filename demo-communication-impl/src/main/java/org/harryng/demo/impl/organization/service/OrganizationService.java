package org.harryng.demo.impl.organization.service;

import org.harryng.demo.impl.base.service.BaseSearchableValidatedService;
import org.harryng.demo.impl.organization.dto.OrganizationDto;
import org.harryng.demo.impl.organization.entity.OrganizationImpl;

public interface OrganizationService extends BaseSearchableValidatedService<OrganizationImpl, OrganizationDto, OrganizationDto, OrganizationDto, Long> {
}
