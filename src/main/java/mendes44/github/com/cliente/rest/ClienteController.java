package mendes44.github.com.cliente.rest;

import mendes44.github.com.cliente.entity.Cliente;
import mendes44.github.com.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    //Utilizo o construtor padrão por bons costumes pois poderia fazer assim:
    //@Autowired
    //private ClienteRepositoru repository;
    private final ClienteRepository repository;

    @Autowired
    public ClienteController( ClienteRepository repository ){
        this.repository = repository;
    }

    //Metodo que indica que busca atrasves do REST e Salva no banco de dados com status 201
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar ( @RequestBody Cliente cliente){
        repository.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente acharPorID ( @PathVariable Long id   ){
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    //Deletar atraves do ID
    @DeleteMapping
    public void deletar( @PathVariable Long id ){

        //Poderia apagar somente com uma linha de comando
        //repository.deleteAllById(id); porem nao teria tratamento de execeções

        repository.findById(id)
                .map( cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE; //Mesmo nao voltando nada tenho que retornar o vazio
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND ));
    }

}
