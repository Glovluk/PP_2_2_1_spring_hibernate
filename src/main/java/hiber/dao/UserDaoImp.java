package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByCarModelAndSeries(String carModel, int carSeries) {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User where userCar.model = :carModel and " +
                        "userCar.series = :carSeries", User.class);
        query.setParameter("carModel", carModel);
        query.setParameter("carSeries", carSeries);

        return query.getSingleResult();
    }

}
