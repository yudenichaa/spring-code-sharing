package dev.nightzen.codesharing.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dev.nightzen.codesharing.business.entity.Code;

import java.util.Optional;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {
    Optional<Code> findByUuid(String uuid);

    Iterable<Code> findTop10ByTimeRestrictionFalseAndViewsRestrictionFalseOrderByIdDesc();

}
