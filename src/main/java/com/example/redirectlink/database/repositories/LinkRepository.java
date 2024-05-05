package com.example.redirectlink.database.repositories;

import com.example.redirectlink.database.enities.LinkEnity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LinkRepository extends CassandraRepository<LinkEnity, Long> {
    List<LinkEnity> findByKeyLinkId(final UUID linkId);
}
