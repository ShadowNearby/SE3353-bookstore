package com.mainservice.repository;

import com.mainservice.entity.BookType;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookTypeRepository extends Neo4jRepository<BookType, Long> {
    @Query("MATCH (a:BookType)-[:relateBooks]->(b) " +
            "WHERE a.typeName = $name " +
            "RETURN b "
    )
    List<BookType> findNodeRelatedBookTypesDistance1(@Param("name") String name);

    @Query("MATCH (a:BookType)-[:relateBooks]->(b)-[:relateBooks]->(c) " +
            "WHERE a.typeName = $name " +
            "RETURN c "
    )
    List<BookType> findNodeRelatedBookTypesDistance2(@Param("name") String name);

    Optional<BookType> findBookTypesByTypeName(String name);
}
