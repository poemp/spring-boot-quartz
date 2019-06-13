package org.poem.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author poem
 */
@Service
public class ApplicationContext implements ApplicationContextAware,ApplicationListener {

    private static org.springframework.context.ApplicationContext applicationContext;

    /**
     * 获取bean
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 刷新和创建bean
     * @param cls
     * @param beanId id of bean
     * @param initMethod init method: Set the name of the initializer method. The default is {@code null}
     *                     in which case there is no initializer method.
     */
    public static void refreshBean(Class cls,String beanId,String initMethod) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(cls);
        builder.getBeanDefinition().setAttribute("id",beanId);
        builder.getBeanDefinition().setInitMethodName(initMethod);
        beanFactory.registerBeanDefinition(beanId,builder.getBeanDefinition());
        applicationContext.getBean(beanId);
    }


    /**
            * 刷新和创建bean,更新指定的参数
     * @param cls
     * @param beanId id of bean
     * @param initMethod init method: Set the name of the initializer method. The default is {@code null}
     *                     in which case there is no initializer method.
     */
    public static void refreshBean(Class cls,String beanId,String initMethod,String constructorArgumentValue) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(cls);
        builder.getBeanDefinition().setAttribute("id",beanId);
        builder.getBeanDefinition().setInitMethodName(initMethod);
        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        constructorArgumentValues.addIndexedArgumentValue(0,constructorArgumentValue);
        builder.getBeanDefinition().setConstructorArgumentValues(constructorArgumentValues);
        beanFactory.registerBeanDefinition(beanId,builder.getBeanDefinition());
        applicationContext.getBean(beanId);
    }

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }
}
