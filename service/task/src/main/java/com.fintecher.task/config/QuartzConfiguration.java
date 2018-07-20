package com.fintecher.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class QuartzConfiguration {

    @Autowired
    AutowiringSpringBeanJobFactory autowiringSpringBeanJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setConfigLocation(new ClassPathResource("/quartz.properties"));
        factory.setJobFactory(autowiringSpringBeanJobFactory);
        factory.afterPropertiesSet();
        return factory;
    }
}
