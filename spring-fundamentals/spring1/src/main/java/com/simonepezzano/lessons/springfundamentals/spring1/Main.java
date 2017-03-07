package com.simonepezzano.lessons.springfundamentals.spring1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The main class of our example.
 */
public class Main {

    public static void main(String[] args){
        /**
         * We create a context using 'beans.xml' as constructor's manual
         */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        /**
         * Obtaining a process object is simple. "process" is not defined as a bean in beans.xml but it's
         * annotated. Being a Runnable, we don't even know what class implements it.
         */
        Runnable process = (Runnable) context.getBean("process");
        process.run();
    }
}
