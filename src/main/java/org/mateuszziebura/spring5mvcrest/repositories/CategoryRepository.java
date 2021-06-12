package org.mateuszziebura.spring5mvcrest.repositories;


import org.mateuszziebura.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}

