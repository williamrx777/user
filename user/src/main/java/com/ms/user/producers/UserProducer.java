package com.ms.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.user.dtos.EmailDTO;
import com.ms.user.models.User;

@Component
public class UserProducer {
    
    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user){
        var emailDTO = new EmailDTO();
        emailDTO.setId(user.getId());
        emailDTO.setEmailTo(user.getEmail());
        emailDTO.setSubject("Cadastro realizado com sucesso");
        emailDTO.setText(user.getName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!");
        
        rabbitTemplate.convertAndSend("", routingKey,emailDTO);

    }

}
