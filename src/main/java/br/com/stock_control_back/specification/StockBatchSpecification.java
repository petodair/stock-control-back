package br.com.stock_control_back.specification;

import br.com.stock_control_back.entity.StockBatch;
import br.com.stock_control_back.enums.StockLocation;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockBatchSpecification {

    private StockBatchSpecification(){}

    public static Specification<StockBatch> filter(
            StockLocation location,
            Boolean expired
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (location != null) {
                predicates.add(
                        cb.equal(root.get("location"), location)
                );
            }

            if (expired != null) {
                LocalDate today = LocalDate.now();

                if (expired) {
                    predicates.add(
                            cb.lessThan(root.get("validity"), today)
                    );
                } else {
                    predicates.add(
                            cb.greaterThanOrEqualTo(root.get("validity"), today)
                    );
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
