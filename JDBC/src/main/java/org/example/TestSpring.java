package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        testBean testBean = context.getBean("testBean", testBean.class);

        System.out.println(testBean.getName());

        context.close();
    }
}
