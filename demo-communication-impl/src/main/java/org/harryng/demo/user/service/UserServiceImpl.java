package org.harryng.demo.user.service;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.harryng.demo.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.base.service.AbstractSearchableService;
import org.harryng.demo.user.persistence.UserPersistence;
import org.harryng.demo.user.pojo.entity.UserImpl;
import org.harryng.demo.util.PageInfo;
import org.harryng.demo.util.PageResult;

public class UserServiceImpl extends AbstractSearchableService<Long, UserImpl> implements UserService {

    @Resource
    private UserPersistence userPersistence;

    @Override
    public BaseSearchablePersistence<Long, UserImpl> getPersistence() {
        return userPersistence;
    }

    @Override
    public UserImpl getByUsername(String username) throws RuntimeException, Exception {
        UserImpl result = null;
        PageInfo pageInfo = new PageInfo();
        CriteriaBuilder criteriaBuilder = getPersistence().getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserImpl> criteriaQuery = criteriaBuilder.createQuery(UserImpl.class);
        Root<UserImpl> root = criteriaQuery.from(UserImpl.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username));
        PageResult<UserImpl> pageResult = findByConditions(pageInfo, criteriaQuery);
        if (!pageResult.getResults().isEmpty()) {
            result = pageResult.getResults().getFirst();
        }
        return result;
    }
}
