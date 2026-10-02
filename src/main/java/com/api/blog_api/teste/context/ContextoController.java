package com.api.blog_api.teste.context;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;

@RestController
@RequestMapping("/api/contexto")
public class ContextoController {

    @GetMapping
    public ContextoResponseDto contexto(Locale locale) {

        ZoneId zoneId = timezonePara(locale);
        LocalDateTime agora = LocalDateTime.now(zoneId);

        DateTimeFormatter formatoData = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(locale);

        NumberFormat numero = NumberFormat.getNumberInstance(locale);
        numero.setMinimumFractionDigits(2);
        numero.setMaximumFractionDigits(2);

        NumberFormat moeda = NumberFormat.getCurrencyInstance(locale);
        moeda.setCurrency(Currency.getInstance(moedaPara(locale)));

        return new ContextoResponseDto(
                locale.toLanguageTag(),
                zoneId.getId(),
                agora.format(formatoData),
                moeda.format(1250.90),
                numero.format(1250.90)
        );
    }

    private String moedaPara(Locale locale) {
        return switch (locale.getCountry()) {
            case "US" -> "USD";
            case "ES" -> "EUR";
            case "JP" -> "JPY";
            default -> "BRL";
        };
    }

    private ZoneId timezonePara(Locale locale) {
        return switch (locale.getCountry()) {
            case "US" -> ZoneId.of("America/New_York");
            case "ES" -> ZoneId.of("Europe/Madrid");
            case "JP" -> ZoneId.of("Asia/Tokyo");
            default -> ZoneId.of("America/Sao_Paulo");
        };
    }
}