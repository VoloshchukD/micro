package by.valashchuk.microsender;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableRabbit
@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbit.mq.queue.name}")
    private String queueName;


    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }


    @Bean
    DirectExchange exchange() {
        return new DirectExchange(queueName);
    }

    //
    @Bean
    Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(queueName);
    }

}
