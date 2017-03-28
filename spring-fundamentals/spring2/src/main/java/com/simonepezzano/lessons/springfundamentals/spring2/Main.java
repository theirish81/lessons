package com.simonepezzano.lessons.springfundamentals.spring2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class
 */
public class Main {

    /**
     * Main class. Where everything starts
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Application application = (Application) context.getBean("application");
        application.run();
    }
}
