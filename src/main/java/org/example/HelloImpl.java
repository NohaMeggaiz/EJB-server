package org.example;


import jakarta.ejb.Stateless;

@Stateless
public class HelloImpl implements Hello{


    @Override
    public String sayHello(String msg) {
        return "this is hello world" + msg;
    }
}
