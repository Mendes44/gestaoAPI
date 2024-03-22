package mendes44.github.com.cliente.rest;

import mendes44.github.com.cliente.entity.Cliente;
import mendes44.github.com.cliente.entity.ServicoPrestado;
import mendes44.github.com.cliente.repository.ClienteRepository;
import mendes44.github.com.cliente.rest.dto.ServicoPrestadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

    public final ClienteRepository clienteRepository;
    public final ClienteRepository repository;

    //Em vez de usar o construtor posso usar annotation @RequiredArgsConstructor
    public ServicoPrestadoController(ClienteRepository clienteRepository,
                                     ClienteRepository repository) {
        this.clienteRepository = clienteRepository;
        this.repository = repository;
    }


    @PostMapping
    public ServicoPrestado salvar (@RequestBody ServicoPrestadoDTO dto) {
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Long idCliente = dto.getIdCliente();

        //Verificação se Existe Cliente
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Cliente Inexistente!"));
        /*
            Tem como Verificar de varias maneiras como:
            <Optional> e uma interface criada no java 8 onde indica que pode ou não ter um
            objeto (nulo ou não).
            1 - Metodo: IF
                        if(cliente.Optional.isPresent()){
                        Caso existe cliente ele indiaca, caso contratio posso lançar um erro}

           2 - Metodo: Funcional do JAVA
           Optional<Cliente> clienteOptional = clienteRepository.findById (cliente);
           Cliente cliente = clienteOptional.get(); [se nao tiver pode acusar erro] ai e melhor fazer assim:
           Cliente cliente = clienteOptional.orElse(new Cliente());

           3 - Metodo: MAP
           Este metodo mapeia para um novo Optional
           Cliente cliente = clienteOptional.map (cliente -> cliente.getNome());

         */
        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData( data );
        servicoPrestado.setCliente( cliente );
        servicoPrestado.setValor(  );
    }

}
