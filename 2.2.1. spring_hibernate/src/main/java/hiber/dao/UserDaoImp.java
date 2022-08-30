package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;
@Transactional
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getUserByCar(String model, int series) {
      String hql = "from User s where s.car.model = ?1 and s.car.series = ?2";
      TypedQuery<User> query= sessionFactory.getCurrentSession().createQuery(hql)
              .setString(1, model)
              .setParameter(2, series);

      return query.getResultList();
   }

   @Override
   public void cleanTables() {
      Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM User ");
      query.executeUpdate();
      query = sessionFactory.getCurrentSession().createQuery("DELETE FROM Car ");
      query.executeUpdate();


   }
}
