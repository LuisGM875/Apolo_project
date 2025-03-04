package com.aidr.backend.Repositories;

import com.aidr.backend.Models.SolicitudesOfertaXDemanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolicitudesOfertaXDemandaRepository extends JpaRepository<SolicitudesOfertaXDemanda, Long> {
}
