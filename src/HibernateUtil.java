import entity.Book;
import entity.Laptop;
import entity.Program;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory SESSION_FACTORY;

    static {
        try {
            SESSION_FACTORY = buildSessionFactory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static SessionFactory buildSessionFactory() throws IOException {
        Properties properties = new Properties();
        properties.load(Appinitializer.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .applySettings(properties)
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Laptop.class)
                .addAnnotatedClass(Program.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder().build();
    }
    public static Session getSession(){
        return SESSION_FACTORY.openSession();
    }
}
