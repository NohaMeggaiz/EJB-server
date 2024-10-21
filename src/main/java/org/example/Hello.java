package org.example;


import jakarta.ejb.Remote;

@Remote
public interface Hello {
    String sayHello(String msg);

}
