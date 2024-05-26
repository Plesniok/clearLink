package com.example.redirectlink.database.repositories;

import com.example.redirectlink.database.enities.LinkEnity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface LinkRepository extends CassandraRepository<LinkEnity, Long> {
    List<LinkEnity> findByLinkId(final UUID linkId);

    @Query("SELECT * FROM links2 WHERE initdate < ?0 ALLOW FILTERING")
    List<LinkEnity> findByInitDateBefore(LocalDate date);
    @Query("DELETE FROM links2 WHERE link_id = ?0")
    void deleteByLinkId(UUID linkId);

//    List<LinkEnity> deleteByInitDateBefore2(LocalDate date);

    void deleteByInitDateBefore(LocalDate date);


}