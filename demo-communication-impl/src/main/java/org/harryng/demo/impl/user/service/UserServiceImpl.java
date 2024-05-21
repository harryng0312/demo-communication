package org.harryng.demo.impl.user.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.auth.persistence.ResourceActionPersistence;
import org.harryng.demo.impl.auth.persistence.ResourcePermissionPersistence;
import org.harryng.demo.impl.auth.persistence.RolePersistence;
import org.harryng.demo.impl.base.service.AbstractSearchableService;
import org.harryng.demo.impl.user.dto.UserDto;
import org.harryng.demo.impl.user.entity.UserImpl;
import org.harryng.demo.impl.user.mapper.UserMapper;
import org.harryng.demo.impl.user.persistence.UserGroupPersistence;
import org.harryng.demo.impl.user.persistence.UserPersistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractSearchableService<UserDto, UserImpl, Long> implements UserService {

    private final UserPersistence userPersistence;
    private final UserGroupPersistence userGroupPersistence;
    private final RolePersistence rolePersistence;
    private final ResourcePermissionPersistence resourcePermissionPersistence;
    private final ResourceActionPersistence resourceActionPersistence;
    private final UserMapper userMapper;

    @Override
    public UserPersistence getPersistence() {
        return userPersistence;
    }

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }

    @Override
    public Optional<UserDto> getByUsername(String username, Map<String, Object> extra) throws Exception {
        Optional<UserDto> result = Optional.empty();
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
            result = Optional.of(getMapper().toDto(page.getContent().getFirst()));
        }
        return result;
    }
    @Override
    public Optional<UserDto> getByMyId(SessionHolder sessionHolder, Map<String, Object> extra) throws Exception {
        final Optional<UserImpl> optUser = getPersistence().findById(sessionHolder.getUserId());
        if(optUser.isPresent()){
            final UserDto userDto = getMapper().toDto(optUser.get());
            return Optional.of(userDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> getById(
            SessionHolder sessionHolder, Long id, boolean loadUsergroup, boolean loadRole,
            boolean loadPermission, Map<String, Object> extra) throws Exception {
        final Optional<UserImpl> optUserImpl = getPersistence().findById(id);
        final Optional<UserDto> optUserDto = optUserImpl.map(user -> getMapper().toDto(user));
        if (optUserDto.isPresent()) {
            final var user = optUserDto.get();
            if (loadUsergroup) {
                final var usergroups = userGroupPersistence.findAllByUserId(user.getId());
//                user.getUserGroups().addAll(usergroups);
//                if (loadRole) {
//                    user.getUserGroups().forEach(userGroup -> {
//                        final List<RoleImpl> roles = rolePersistence.getIdsByUsergroupId(userGroup.getId());
//                        userGroup.getRoles().addAll(roles);
//                        if (loadPermission && !roles.isEmpty()) {
//                            final List<Long> roleIds = roles.stream().map(AbstractModel::getId).toList();
//                            final var permissionsScope = resourcePermissionPersistence.getByRoleIds(roleIds);
//                            userGroup.getPermissions().addAll(permissionsScope);
//                        }
//                    });
//                }
            }
        }
        return optUserDto;
    }
}
