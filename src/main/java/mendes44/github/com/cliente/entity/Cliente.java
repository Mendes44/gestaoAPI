package mendes44.github.com.cliente.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String nome;
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(name = "data_cadastro" )
    private LocalDate dataCadastro;

    //PerPersist = Serve para quando for persistir o sistema cadastrar no banco de dados a data atual
    @PrePersist
    public void perPersist(){
        setDataCadastro(LocalDate.now());
    }
}
