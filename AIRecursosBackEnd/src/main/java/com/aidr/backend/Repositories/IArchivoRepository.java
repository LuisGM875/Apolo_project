package com.aidr.backend.Repositories;

import com.aidr.backend.Models.ArchivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IArchivoRepository extends JpaRepository<ArchivoEntity, Long> {
}
