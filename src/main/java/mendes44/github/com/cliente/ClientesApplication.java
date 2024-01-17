package mendes44.github.com.cliente;

import mendes44.github.com.cliente.entity.Cliente;
import mendes44.github.com.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientesApplication {

	public static void main(String[] args) {

		SpringApplication.run(ClientesApplication.class, args);
	}

	/*
			O Comando -> CommandLineRunner executa a linha de comando assim que iniciar a aplicação
			 e injetar o codigo atraves do @Beans, assim posso testar a entrada no banco de dados.

			 OBS: Lembrar de colocar essa anotação @Bean sempre apos o metodo run.
	*/
	//@Autowired ClienteRepository repository; > pode ser declarado assim tambem.
	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {
			Cliente cliente = Cliente.builder().cpf("12345678912").nome("Teste").build();
			repository.save(cliente);
		};
	}

}
