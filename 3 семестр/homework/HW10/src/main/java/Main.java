import DataBase.HibernateSessionFactory;
import DataBase.TrainServicingEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Main  {

    static Session session;

    public static void main(String[] args) {
        session = connect();
        JavaFXMain.main(args);
        session.close();
    }

    static Session connect() {
        System.out.println("Запуск сессии...");
        Session local_session = HibernateSessionFactory.getSessionFactory().openSession();
        System.out.println("Успешно");
        return local_session;
    }

    public static TrainServicingEntity getById(int id) {
        Criteria userCriteria = session.createCriteria(TrainServicingEntity.class);
        userCriteria.add(Restrictions.eq("id", id));
        TrainServicingEntity serv1 = (TrainServicingEntity) userCriteria.uniqueResult();
        System.out.println(serv1);

        return serv1;
    }

    public static void insert(int id, String date, String service_type, int train_n, Double cost) {
        TrainServicingEntity servicingEntity = new TrainServicingEntity();

        Session local_session = connect();
        local_session.beginTransaction();

        servicingEntity.setId(id);
        servicingEntity.setDt(date);
        servicingEntity.setServiceType(service_type);
        servicingEntity.setTrainN(train_n);
        servicingEntity.setCost(cost);

        local_session.save(servicingEntity);
        local_session.getTransaction().commit();
        local_session.close();
        System.out.println("Добавление объекта успешно");
    }
    public static List<TrainServicingEntity> getServicingList() {
        Criteria userCriteria = session.createCriteria(TrainServicingEntity.class);
        userCriteria.addOrder(Order.asc("id"));
        return userCriteria.list();
    }

    public static List<TrainServicingEntity> getServicingList(String propName, String order) {
        Criteria userCriteria = session.createCriteria(TrainServicingEntity.class);

        if (order.equals("ASCENDING")) {
            userCriteria.addOrder(Order.asc(propName));
        } else if (order.equals("DESCENDING")) {
            userCriteria.addOrder(Order.desc(propName));
        }

        return userCriteria.list();
    }

    public static void update(int id, String date, String service_type, int train_n, Double cost) {

        TrainServicingEntity servicingEntity =  getById(id);

        Session local_session = connect();
        local_session.beginTransaction();

        servicingEntity.setId(id);
        servicingEntity.setDt(date);
        servicingEntity.setServiceType(service_type);
        servicingEntity.setTrainN(train_n);
        servicingEntity.setCost(cost);

        local_session.update(servicingEntity);
        local_session.getTransaction().commit();
        local_session.close();
        System.out.println("Обновление успешно");
    }

    public static void delete(String id) {
        Session local_session = connect();
        local_session.beginTransaction();

        local_session.delete(getById(Integer.parseInt(id)));
        local_session.getTransaction().commit();
        local_session.close();
        System.out.println("Удаление успешно");
    }
}
