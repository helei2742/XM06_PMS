package org.xm06.pms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xm06.pms.service.MailService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTemplateMail() {
        //向Thymeleaf模板传值，并解析成字符串
        Context context = new Context();
        context.setVariable("id", 1);
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("914577981@qq.com", "这是一个模板文件", emailContent);
    }

}