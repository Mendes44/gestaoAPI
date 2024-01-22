package mendes44.github.com.cliente.rest;

import mendes44.github.com.cliente.rest.exception.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErros ( MethodArgumentNotValidException ex ){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> messages = bindingResult.getAllErrors()
                .stream()
                .map( objectError -> objectError.getDefaultMessage() )
                .collect( Collectors.toList() );
        return new ApiErrors(messages);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException (ResponseStatusException ex ){
        String mensagemErro = ex.getMessage(); // Aqui coleto a mensagem da Classe ClienteController - "Cliente não encotrado!"
        HttpStatus codigoStatus = (HttpStatus) ex.getStatusCode(); // Aqui coleto o codigo de Status que foi colocado.
        ApiErrors apiErrors = new ApiErrors(mensagemErro); // Aqui ira mandar transformar em uma Array
        return new ResponseEntity(apiErrors,codigoStatus); // Aqui estou retornando dentro do ResponseEntity o codigo de status e a mensagem
    }

}


/*

    > Esta Classe = ApplicationControllerAdvice, vai interceptar toda vez que tiver erro de validação
    atraves do metodo ExceptioHandler vai capturar essa Exception, logo apos o usando o BindingResult onde tem todas
    as mensagens de erros e vamos retornar como um objeto padronizado.

    - O metodo map transforma o OBJETO EM STRING -

    > No metodo handleResponseStatusException - faz a retornos de erros dinamicamente.
    >
 */