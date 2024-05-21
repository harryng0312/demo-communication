package org.harryng.demo.impl.organization.service;

import org.harryng.demo.impl.organization.dto.OrganizationDto;
import org.harryng.demo.impl.base.service.BaseSearchableAuthenticatedService;
import org.harryng.demo.impl.organization.entity.OrganizationImpl;

public interface OrganizationService extends BaseSearchableAuthenticatedService<OrganizationDto, OrganizationImpl, Long> {
}
