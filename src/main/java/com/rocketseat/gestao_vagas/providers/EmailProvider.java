package com.rocketseat.gestao_vagas.providers;

import com.rocketseat.gestao_vagas.modules.inscription.enums.StatusEnum;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
public class EmailProvider {

    public void sendEmailStatus(StatusEnum statusEnum) {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setTemplateMode("html");
        resolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        Context context = new Context();
        context.setVariable("nome", "João");
        context.setVariable("pedidoId", 12345);

        String output = templateEngine.process("template", context);
        System.out.println(output);

    }
}
