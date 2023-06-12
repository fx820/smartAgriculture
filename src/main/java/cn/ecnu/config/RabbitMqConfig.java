package cn.ecnu.config;

import cn.ecnu.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 *
 * @author zachary
 **/
@Slf4j
@Configuration
public class RabbitMqConfig {

    /**
     * 消息转换器
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 邮件交换机
     */
    @Bean
    public TopicExchange emailExchange() {
        return new TopicExchange(MqConstant.EMAIL_EXCHANGE, true, false);
    }

    /**
     * 邮件Simple队列
     */
    @Bean
    public Queue emailSimpleQueue() {
        return new Queue(MqConstant.EMAIL_SIMPLE_QUEUE, true);
    }

    /**
     * 邮件Html队列
     */
    @Bean
    public Queue emailHtmlQueue() {
        return new Queue(MqConstant.EMAIL_HTML_QUEUE, true);
    }

    /**
     * 绑定邮件Simple队列
     */
    @Bean
    public Binding simpleQueueBinding() {
        return BindingBuilder.bind(emailSimpleQueue()).to(emailExchange()).with(MqConstant.EMAIL_SIMPLE_KEY);
    }

    /**
     * 绑定邮件Html队列
     */
    @Bean
    public Binding htmlQueueBinding() {
        return BindingBuilder.bind(emailHtmlQueue()).to(emailExchange()).with(MqConstant.EMAIL_HTML_KEY);
    }

    /**
     * 文章交换机
     */
    @Bean
    public TopicExchange articleExchange() {
        return new TopicExchange(MqConstant.ARTICLE_EXCHANGE, true, false);
    }

    /**
     * 文章队列
     */
    @Bean
    public Queue articleQueue() {
        return new Queue(MqConstant.ARTICLE_QUEUE, true);
    }

    /**
     * 绑定文章队列
     */
    @Bean
    public Binding articleQueueBinding() {
        return BindingBuilder.bind(articleQueue()).to(articleExchange()).with(MqConstant.ARTICLE_KEY);
    }
}