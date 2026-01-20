package br.com.stock_control_back.repository;

import br.com.stock_control_back.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
