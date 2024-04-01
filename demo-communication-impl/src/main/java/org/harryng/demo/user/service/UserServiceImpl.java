package org.harryng.demo.user.service;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.harryng.demo.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.base.pojo.dto.SessionHolder;
import org.harryng.demo.base.service.AbstractSearchableService;
import org.harryng.demo.user.persistence.UserPersistence;
import org.harryng.demo.user.pojo.data.entity.UserImpl;
import org.harryng.demo.util.PageInfo;
import org.harryng.demo.util.PageResult;

import java.util.Map;

public class UserServiceImpl extends AbstractSearchableService<Long, UserImpl> implements UserService {

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
        final PageResult<UserImpl> pageResult = getPersistence().selectByConditions(pageInfo, criteriaQuery);
        if (!pageResult.getResults().isEmpty()) {
            result = pageResult.getResults().getFirst();
        }
        return result;
    }
}
