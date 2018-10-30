package br.com.senaijandira.alunosnode.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    public static AlunosService create(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(AlunosService.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();
        // baseUrl => é a url que irá usar para conectar na api
        // addConverterFactory(GsonConverterFactory.create()) => converte um objeto Json em Java
        // build => constroi


        return retrofit.create(AlunosService.class);
    }

}
