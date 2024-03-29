package mendes44.github.com.cliente.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mendes44.github.com.cliente.entity.Cliente;
import mendes44.github.com.cliente.entity.ServicoPrestado;
import mendes44.github.com.cliente.repository.ClienteRepository;
import mendes44.github.com.cliente.repository.ServicoPrestadoRepository;
import mendes44.github.com.cliente.rest.dto.ServicoPrestadoDTO;
import mendes44.github.com.cliente.util.BigDecimalConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

    public final ClienteRepository clienteRepository;
    public final ServicoPrestadoRepository repository;
    public final BigDecimalConverter bigDecimalConverter;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar (@Valid @RequestBody ServicoPrestadoDTO dto) {
        //Formatação da Data
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Long idCliente = dto.getIdCliente();

        //Verificação se Existe Cliente
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Cliente Inexistente!"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData( data );
        servicoPrestado.setCliente( cliente );
        //Criar um metodo para fazer a conversão de BigDecimal
        servicoPrestado.setValor( bigDecimalConverter.converte(dto.getPreco()) );

        return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ){
     return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }

}
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