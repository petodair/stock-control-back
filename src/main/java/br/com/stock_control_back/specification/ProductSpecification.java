package br.com.stock_control_back.specification;

import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.enums.ProductType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    private ProductSpecification(){}

    public static Specification<Product> filter(String name, String code, ProductType productType){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(!ObjectUtils.isEmpty(name)){
                predicates.add(
                        cb.like(root.get("name"), "%" + name + "%")
                );
            }

            if(!ObjectUtils.isEmpty(code)){
                predicates.add(
                        cb.like(root.get("code"), "%" + code + "%")
                );
            }

            if(productType != null){
                predicates.add(
                        cb.equal(root.get("productType"), productType)
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
