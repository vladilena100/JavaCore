package config;

import model.Role;
import model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"services", "dao", "support"})
public class DBConfig {
    @Bean
    public TransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        final LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setAnnotatedClasses(User.class, Role.class);
        return sessionFactoryBean;
    }
}
