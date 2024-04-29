package org.harryng.demo.api.organization.service;

import org.harryng.demo.api.organization.dto.OrganizationDto;
import org.harryng.demo.api.base.service.BaseSearchableAuthenticatedService;
import org.harryng.demo.api.organization.entity.OrganizationImpl;

public interface OrganizationService extends BaseSearchableAuthenticatedService<OrganizationDto, OrganizationImpl, Long> {
}
