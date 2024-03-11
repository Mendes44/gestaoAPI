package mendes44.github.com.cliente.rest;

import jakarta.validation.Valid;
import mendes44.github.com.cliente.entity.Cliente;
import mendes44.github.com.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController( ClienteRepository repository ){

        this.repository = repository;
    }

    //Metodo que indica que busca atrasves do REST e Salva no banco de dados com status 201
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Cliente salvar ( @Valid @RequestBody Cliente cliente ){
        Cliente clienteSalvo = repository.save(cliente);
        return clienteSalvo;
    }

    @GetMapping("{id}")
    public Cliente acharPorID ( @PathVariable Integer id ){
        return repository
                .findById(Long.valueOf(id))
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encotrado!") );
    }

    //Deletar atraves do ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Long id ) {
        repository
                .findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encotrado!"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Long id, @RequestBody @Valid Cliente clienteAtualizado ){
        repository
                .findById(id)
                .map( cliente -> {
                   //Jeito de alterar somente o nome sem usar o updatable = false na entidade:
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    return repository.save( clienteAtualizado );
                })
                .orElseThrow(()  -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encotrado!"));
    }
}
