package mendes44.github.com.cliente.rest;

import mendes44.github.com.cliente.entity.Cliente;
import mendes44.github.com.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

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
    public Cliente acharPorID ( @PathVariable Long id ){
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    //Deletar atraves do ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        repository
                .findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado){
        repository
                .findById(id)
                .map( cliente -> {
                   //Jeito de alterar somente o nome sem usar o updatable = false na entidade:
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    return repository.save( clienteAtualizado );
                })
                .orElseThrow(()  -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
