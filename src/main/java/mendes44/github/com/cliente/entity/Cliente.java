package mendes44.github.com.cliente.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    @NotEmpty //Anotação de validação: para não deixar o campo em branco
    private String nome;

    @Column(nullable = false, length = 11)
    @NotNull //Anotação para não deixar um valor null.
    @CPF //Bin ja pronta para validação de CPF.
    private String cpf;

    @Column(name = "data_cadastro", updatable = false )
    @JsonFormat(pattern = "dd/MM/yyyy") //Mascara para formatar a data.
    private LocalDate dataCadastro;

    //PerPersist = Serve para quando for persistir o sistema cadastrar no banco de dados a data atual
    @PrePersist
    public void perPersist(){
        setDataCadastro(LocalDate.now());
    }
}
