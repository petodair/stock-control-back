package br.com.stock_control_back.dataloader;

import br.com.stock_control_back.dto.product.ProductRequestDTO;
import br.com.stock_control_back.enums.ProductType;
import br.com.stock_control_back.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Order(value = 1)
//@Profile("test")
public class ProductDataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<ProductRequestDTO> listProduct = Arrays.asList(
                new ProductRequestDTO("Miolo da Alcatra", "2592", BigDecimal.valueOf(42.90), ProductType.VACUO),
                new ProductRequestDTO("Alcatra com Maminha", "8679", BigDecimal.valueOf(42.90), ProductType.VACUO),
                new ProductRequestDTO("Picanha", "56809", BigDecimal.valueOf(69.90), ProductType.VACUO),
                new ProductRequestDTO("Fraldinha", "31714", BigDecimal.valueOf(39.97), ProductType.VACUO),
                new ProductRequestDTO("Bife Ancho", "56458", BigDecimal.valueOf(39.97), ProductType.VACUO),
                new ProductRequestDTO("Frango Maringá", "14717", BigDecimal.valueOf(9.97), ProductType.AVE),
                new ProductRequestDTO("Meio da Asa Congelado", "94191", BigDecimal.valueOf(15.98), ProductType.CONGELADO),
                new ProductRequestDTO("Coxa c/ Sobrecoxa Dorso", "25317", BigDecimal.valueOf(7,98), ProductType.CONGELADO),
                new ProductRequestDTO("Fígado Bovino", "60264", BigDecimal.valueOf(9.97), ProductType.BOVINO),
                new ProductRequestDTO("Pernil Suíno c/ osso", "80897", BigDecimal.valueOf(15.97), ProductType.SUINO),
                new ProductRequestDTO("Paleta Suína c/ osso", "80903", BigDecimal.valueOf(15.97), ProductType.SUINO),
                new ProductRequestDTO("Bife de Alcatra Bovino", "4176", BigDecimal.valueOf(45.97), ProductType.BOVINO),
                new ProductRequestDTO("Filé de Peito Maringá Refriado", "57578", BigDecimal.valueOf(24.97), ProductType.AVE),
                new ProductRequestDTO("Filé Mignon Bovino", "43038", BigDecimal.valueOf(69.98), ProductType.VACUO)
        );

        if (productRepository.count() == 0) {
            productRepository.saveAll(
                    listProduct.stream().map(ProductRequestDTO::toEntity).toList()
            );
        }
    }

}
