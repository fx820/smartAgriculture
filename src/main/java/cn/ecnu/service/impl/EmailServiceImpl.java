package cn.ecnu.service.impl;

import cn.ecnu.model.dto.MailDTO;
import cn.ecnu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件服务接口实现类
 *
 * @author zachary
 **/
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * 邮箱号
     */
    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendSimpleMail(MailDTO mailDTO) {
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom(email);
        simpleMail.setTo(mailDTO.getToEmail());
        simpleMail.setSubject(mailDTO.getSubject());
        simpleMail.setText(mailDTO.getContent());
        javaMailSender.send(simpleMail);
    }

    @Override
    public void sendHtmlMail(MailDTO mailDTO) {
        try {
            //创建一个MimeMessage对象
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            //创建一个MimeMessageHelper对象，将MimeMessage对象传入
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            //创建一个Thymeleaf的上下文Context对象
            Context context = new Context();
            //设置Thymeleaf上下文中的变量，通过mailDTO.getContentMap()获取变量值
            context.setVariables(mailDTO.getContentMap());
            //使用Thymeleaf模板引擎渲染Template，mailDTO.getTemplate()是获取到当前邮件使用的模板
            String process = templateEngine.process(mailDTO.getTemplate(), context);
            //设置邮件发送方的的电子邮件地址，email指的是发送邮件的人的电子邮件地址
            mimeMessageHelper.setFrom(email);
            //设置邮件收件人的电子邮件地址，mailDTO.getToEmail()是获取到当前邮件的收件人
            mimeMessageHelper.setTo(mailDTO.getToEmail());
            //mimeMessageHelper.setSubject(mailDTO.getSubject()) 设置邮件主题 mailDTO.getSubject()是获取到当前邮件的主题
            mimeMessageHelper.setSubject(mailDTO.getSubject());
            //设置邮件内容，process是使用Thymeleaf渲染后的邮件内容，true表示邮件内容采用HTML格式
            mimeMessageHelper.setText(process, true);
            //发送邮件，使用JavaMailSender发送MimeMessage对象
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}