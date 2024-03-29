package mendes44.github.com.cliente.util;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalConverter {

    public BigDecimal converte (String value){

        if (value == null){
            return null;
        }

        /*Aqui estou fazendo uma conversão como por exemplo:
        Recebe R$ 1.000,00 e ira converter para 1000.00
        */
        value = value.replace(".","").replace(",",".");
        return new BigDecimal(value);
    }
}
