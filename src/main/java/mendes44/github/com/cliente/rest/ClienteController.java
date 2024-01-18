package mendes44.github.com.cliente.rest;

import mendes44.github.com.cliente.entity.Cliente;
import mendes44.github.com.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public void salvar (Cliente cliente){
        repository.save(cliente);
    }

}
