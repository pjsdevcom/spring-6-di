package com.pjsdev.spring6di.services;

import com.pjsdev.spring6di.controllers.MyController;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class LifeCycleDemoBean implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, BeanPostProcessor {

    private String javaVer;

    public LifeCycleDemoBean() {
        System.out.println("==> In the LifeCycleDemoBean constructor...");
    }

    @Value("${java.specification.version}")
    public void setJavaVer(String javaVer) {
        this.javaVer = javaVer;
        System.out.println("==> 1. Properties set. Java version: " + this.javaVer);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("==> 2. BeanNameAware - bean name is: " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("==> 3. BeanFactoryAware - bean factory has been set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("==> 4. ApplicationContextAware - application context has been set");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("==> 5. PostConstruct - the @PostConstruct annotated method has been called");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("==> 6. AfterPropertiesSet populate properties - the LifeCycleBean has its properties set");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("==> 7. PreDestroy - the @PreDestroy annotated method has been called");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("==> 8. DisposableBean.destroy - the LifeCycleBean has been terminated");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("==> postProcessBeforeInitialization: " + beanName);

        if (bean instanceof MyController){
            MyController myController = (MyController) bean;
            System.out.println("Calling before init...");
            myController.beforeInit();
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("==> postProcessAfterInitialization: " + beanName);

        if (bean instanceof MyController){
            MyController myController = (MyController) bean;
            System.out.println("Calling after init...");
            myController.afterInit();
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

}
