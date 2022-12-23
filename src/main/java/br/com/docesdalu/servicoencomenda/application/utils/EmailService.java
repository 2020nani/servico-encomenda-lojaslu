package br.com.docesdalu.servicoencomenda.application.utils;

import org.springframework.stereotype.Component;

@Component
public class EmailService {

    public String senderEmail(){
        System.out.println("enviou");
        return "ok";
    }

    public String senderEmailOk(){
        System.out.println("enviou");
        return "ok";
    }

    public void senderEmailFraude(){
        System.out.println("fraude");
    }
}
