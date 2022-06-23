/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 7:12 PM
 */
package lk.ijse.hms.util;

import lk.ijse.hms.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        //configuring properties file
        Properties p = new Properties();
        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        configuration.setProperties(p);

        //configuring entities
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(Reserve.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(RoomSetting.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() throws IOException {
        return factoryConfiguration==null? factoryConfiguration = new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
