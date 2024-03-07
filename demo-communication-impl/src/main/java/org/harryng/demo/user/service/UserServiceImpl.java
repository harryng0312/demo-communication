package org.harryng.demo.user.service;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.harryng.demo.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.base.service.AbstractSearchableService;
import org.harryng.demo.user.persistence.UserPersistence;
import org.harryng.demo.user.pojo.data.entity.UserImpl;
import org.harryng.demo.util.PageInfo;

@Transactional
public class UserServiceImpl extends AbstractSearchableService<Long, UserImpl> implements UserService {
    @Resource
    private UserPersistence userPersistence;
    @Override
    public BaseSearchablePersistence<Long, UserImpl> getPersistence() {
        return userPersistence;
    }

    @Override
    public UserImpl getById(Long id) throws Exception {
        return super.getById(id);
    }
    @Override
    public UserImpl getByUsername(String username) throws RuntimeException, Exception {
        var pageInfo = new PageInfo();
        var criteriaBuilder = getPersistence().getStatelessSession().getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(UserImpl.class);
        var root = criteriaQuery.from(UserImpl.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username));
        var pageResult = findByConditions(pageInfo, criteriaQuery);
        if (!pageResult.getResults().isEmpty()) {
            return pageResult.getResults().getFirst();
        }
        return null;
    }
}
