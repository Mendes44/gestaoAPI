package mendes44.github.com.cliente.rest.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    /*
        Estou criando uma lista e um array para capturar os erros.
        No asList = Estou transformando um objeto em lista (String)
     */

    @Getter
    private List<String> errors;

    //Construtor Padrão
    public ApiErrors(List<String> errors){
        this.errors = errors;
    }

    public ApiErrors(String message){
        this.errors = Arrays.asList(message);
    }

}
