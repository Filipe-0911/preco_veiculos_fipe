package br.com.application.fipe.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class ConverteDados<T> implements IConverteDados {

    private ObjectMapper objectMapper = new ObjectMapper();

    @SuppressWarnings("hiding")
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(json, classe);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        CollectionType list = objectMapper
        .getTypeFactory()
        .constructCollectionType(List.class, classe);

        try {
            return objectMapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
