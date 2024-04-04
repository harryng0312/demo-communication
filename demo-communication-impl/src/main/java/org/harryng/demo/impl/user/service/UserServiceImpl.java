package org.harryng.demo.impl.user.service;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.harryng.demo.api.user.entity.UserImpl;
import org.harryng.demo.api.user.persistence.UserPersistence;
import org.harryng.demo.api.user.service.UserService;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.impl.base.service.AbstractSearchableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl extends AbstractSearchableService<UserImpl, Long> implements UserService {

    @Resource
    private UserPersistence userPersistence;

    @Override
    public UserPersistence getPersistence() {
        return userPersistence;
    }

    @Override
    public UserImpl getByUsername(String username, Map<String, Object> extra) throws Exception {
        UserImpl result = null;
        final PageInfo pageInfo = new PageInfo();
        final CriteriaBuilder criteriaBuilder = getPersistence().getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<UserImpl> criteriaQuery = criteriaBuilder.createQuery(UserImpl.class);
        final Root<UserImpl> root = criteriaQuery.from(UserImpl.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username));
//        final PageResult<UserImpl> pageResult = findByConditions(SESSION_HOLDER_EMPTY, pageInfo, criteriaQuery, extra);
//        final PageResult<UserImpl> pageResult = getPersistence().selectByConditions(pageInfo, criteriaQuery);
        final Pageable pageable = PageRequest.of(0,1);
        final Page<UserImpl> page = getPersistence().searchByUsername(username, pageable);
        if(page != null && page.getTotalElements() > 0) {
            result = page.getContent().getFirst();
        }
//        if (!pageResult.getResults().isEmpty()) {
//            result = pageResult.getResults().getFirst();
//        }
        return result;
    }
}
