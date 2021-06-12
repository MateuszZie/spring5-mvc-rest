package org.mateuszziebura.spring5mvcrest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Customer {
    private String firstName;
    private String lastName;
    @Id
    private String customerUrl;
}
