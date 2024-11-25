package com.rocketseat.gestao_vagas.providers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
public class OpenAiProvider {

    @Value("${openAi.api.url}")
    private String OPENAI_API_URL;

    @Value("${openAi.api.secret_key}")
    private String API_KEY;


    public String analyzeCandidate(String descriptionJob, String curriculo) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + API_KEY);
        headers.add("Content-Type", "application/json");

        var prompt = "Dado o seguinte currículo: " + descriptionJob + " E a seguinte descrição de vaga: " + curriculo + " Classifique o candidato como 'Totalmente Apto', 'Parcialmente Apto' ou 'Não Apto'. Justifique brevemente sua decisão.";


        String requestBody = """
                {
                  "model": "gpt-3.5-turbo",
                  "messages": [
                    {"role": "user", "content": "%s"}
                  ]
                }
                """.formatted(prompt);


        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                OPENAI_API_URL, HttpMethod.POST, request, String.class);

        String responseBody = response.getBody();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(responseBody);
            return jsonResponse.get("choices").get(0).get("message").get("content").asText();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a resposta da API da OpenAI", e);
        }



    }

}







