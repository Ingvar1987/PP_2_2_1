package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(" FROM User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserByCarModelAndSeries(String model, int series) {
        TypedQuery<User> query = sessionFactory.openStatelessSession().
                createQuery("FROM User as user inner join fetch user.car as car where car.model =:model and car.series = :series", User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        List<User> list = query.getResultList();
        System.out.println(list);
        return list;
    }
}