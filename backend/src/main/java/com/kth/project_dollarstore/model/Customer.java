package com.kth.project_dollarstore.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Id
    @SequenceGenerator(
        name = "customer_id_sequence",
        sequenceName = "customer_id_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "customer_id_sequence"
    )
    private Integer id;
    private String name;
    private Long dob;
    private Integer age;
    private String email;
    private String address;
    private String postalCode;
    private Integer phoneNumber;
    private String password; 

    public Customer(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", dob=" + dob + ", age=" + age + ", email=" + email
                + ", address=" + address + ", postalCode=" + postalCode + ", phoneNumber=" + phoneNumber + "]";
    }
}
