package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      Session session =sessionFactory.openSession();
      String hql = "FROM User";
      Query query = session.createQuery(hql);
      List<User> userList = query.getResultList();
      return userList;
   }

   public User getById(long id){
      Session session = sessionFactory.openSession();
      String hql = "FROM User user WHERE user.id =: id";
      Query query = session.createQuery(hql);
      query.setParameter("id", id);
      return (User) query.getSingleResult();
   }

   public User getByCar(Car car){
      Session session = sessionFactory.openSession();
      String hql = "FROM User user WHERE car.name =: carName and car.series =: carSeries";
      TypedQuery<User> query = session.createQuery(hql);
      query.setParameter("carName", car.getName());
      query.setParameter("carSeries", car.getSeries());
      return (User) query.getSingleResult();
   }
}
