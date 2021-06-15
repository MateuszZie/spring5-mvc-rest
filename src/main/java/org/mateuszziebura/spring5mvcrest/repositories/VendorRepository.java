package org.mateuszziebura.spring5mvcrest.repositories;

import org.mateuszziebura.spring5mvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
