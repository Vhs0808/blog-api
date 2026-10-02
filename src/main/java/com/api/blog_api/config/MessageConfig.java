package com.api.blog_api.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {

        // Cria o objeto responsável por localizar
        // e carregar as mensagens da aplicação.
        var source = new ReloadableResourceBundleMessageSource();

        // Informa o nome-base e a localização dos arquivos
        // de mensagens dentro do projeto.
        //
        // O Spring irá procurar arquivos como:
        // messages.properties
        // messages_en.properties
        // messages_es.properties
        source.setBasename("classpath:messages");

        // Define UTF-8 como codificação dos arquivos.
        //
        // Isso permite utilizar corretamente caracteres
        // especiais da língua portuguesa, como:
        // á, é, í, ó, ú, ç, ã, õ
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());

        // Impede que o Spring utilize automaticamente
        // o idioma configurado no sistema operacional
        // como alternativa.
        //
        // Dessa forma, trabalhamos com os Locales
        // definidos pela nossa aplicação.
        source.setFallbackToSystemLocale(false);

        // Retorna o MessageSource configurado para que
        // o Spring possa utilizá-lo na aplicação.
        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {

        // Cria o componente responsável por identificar
        // qual idioma/localidade deve ser utilizado pela aplicação.
        //
        // O AcceptHeaderLocaleResolver utiliza o cabeçalho
        // "Accept-Language" enviado pelo cliente na requisição.
        var resolver = new AcceptHeaderLocaleResolver();

        // Define o idioma/localidade padrão da aplicação.
        //
        // Se o cliente não informar um idioma compatível,
        // será utilizado português do Brasil (pt-BR).
        resolver.setDefaultLocale(new Locale("pt", "BR"));

        // Define as localidades que a aplicação aceita.
        //
        // pt-BR = Português do Brasil
        // en-US = Inglês dos Estados Unidos
        // es-ES = Espanhol da Espanha
        resolver.setSupportedLocales(List.of(
                new Locale("pt", "BR"),
                new Locale("ja", "JP"),
                new Locale("en", "US"),
                new Locale("es", "ES")
        ));

        // Retorna o LocaleResolver configurado
        // para que o Spring possa utilizá-lo nas requisições.
        return resolver;
    }

}