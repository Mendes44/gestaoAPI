package mendes44.github.com.cliente.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //Criar sem parametro nenhum

//Criação das Variaveis
public class ServicoPrestadoDTO {
    private String descricao;
    private String preco;
    private String data;
    private Long idCliente;
}
