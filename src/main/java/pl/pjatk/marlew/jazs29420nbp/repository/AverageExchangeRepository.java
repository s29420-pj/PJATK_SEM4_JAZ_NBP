package pl.pjatk.marlew.jazs29420nbp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.marlew.jazs29420nbp.model.AverageExchange;

@Repository
public interface AverageExchangeRepository extends JpaRepository<AverageExchange, Integer> {}
