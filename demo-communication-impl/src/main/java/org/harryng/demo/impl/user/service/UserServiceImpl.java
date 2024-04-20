package org.harryng.demo.impl.user.service;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.harryng.demo.api.auth.entity.RoleImpl;
import org.harryng.demo.api.auth.persistence.ResourceActionPersistence;
import org.harryng.demo.api.auth.persistence.ResourcePermissionPersistence;
import org.harryng.demo.api.auth.persistence.RolePersistence;
import org.harryng.demo.api.base.dto.ResponseWrapper;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.entity.AbstractModel;
import org.harryng.demo.api.user.entity.UserImpl;
import org.harryng.demo.api.user.persistence.UserGroupPersistence;
import org.harryng.demo.api.user.persistence.UserPersistence;
import org.harryng.demo.api.user.service.UserService;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.impl.base.service.AbstractSearchableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractSearchableService<UserImpl, Long> implements UserService {

    @Resource
    private UserPersistence userPersistence;
    @Resource
    private UserGroupPersistence userGroupPersistence;
    @Resource
    private RolePersistence rolePersistence;
    @Resource
    private ResourcePermissionPersistence resourcePermissionPersistence;
    @Autowired
    private ResourceActionPersistence resourceActionPersistence;

    @Override
    public UserPersistence getPersistence() {
        return userPersistence;
    }

    @Override
    public Optional<UserImpl> getByUsername(String username, Map<String, Object> extra) throws Exception {
        Optional<UserImpl> result = Optional.empty();
//        final PageInfo pageInfo = new PageInfo();
        final CriteriaBuilder criteriaBuilder = getPersistence().getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<UserImpl> criteriaQuery = criteriaBuilder.createQuery(UserImpl.class);
        final Root<UserImpl> root = criteriaQuery.from(UserImpl.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username));
//        final PageResult<UserImpl> pageResult = findByConditions(SESSION_HOLDER_EMPTY, pageInfo, criteriaQuery, extra);
//        final PageResult<UserImpl> pageResult = getPersistence().selectByConditions(pageInfo, criteriaQuery);
        final Pageable pageable = PageRequest.of(0, 1);
        final Page<UserImpl> page = getPersistence().searchByUsername(username, pageable);
        if (!page.isEmpty()) {
            result = Optional.of(page.getContent().getFirst());
        }
        return result;
    }

    @Override
    public Optional<UserImpl> getById(
            SessionHolder sessionHolder, Long id, boolean loadUsergroup, boolean loadRole,
            boolean loadPermission, Map<String, Object> extra) throws Exception {
        final Optional<UserImpl> userOpt = getPersistence().findById(id);
        if (userOpt.isPresent()) {
            final var user = userOpt.get();
            if (loadUsergroup) {
                final var usergroups = userGroupPersistence.findAllByUserId(user.getId());
                user.getUserGroups().addAll(usergroups);
                if (loadRole) {
                    user.getUserGroups().forEach(userGroup -> {
                        final List<RoleImpl> roles = rolePersistence.getIdsByUsergroupId(userGroup.getId());
                        userGroup.getRoles().addAll(roles);
                        if (loadPermission && !roles.isEmpty()) {
                            final List<Long> roleIds = roles.stream().map(AbstractModel::getId).toList();
                            final var permissionsScope = resourcePermissionPersistence.getByRoleIds(roleIds);
                            userGroup.getPermissions().addAll(permissionsScope);
                        }
                    });
                }
            }
        }
        return userOpt;
    }
}
