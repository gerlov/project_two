package com.kth.project_dollarstore.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;



@Entity
public class Customer implements Serializable{

    @Id
    @SequenceGenerator(
        name = "customer_id_sequence",
        sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
        strategy =  GenerationType.SEQUENCE,
        generator = "customer_id_sequence"
    )
    private Integer id;
    private String name;
    private Long dob;
    private Integer age;
    private String email;
    private String adress;
    private String postal_code;
    private Integer phone_number;
    public Customer(Integer id, String name, Long dob, Integer age, String email, String adress, String postal_code,
            Integer phone_number) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.email = email;
        this.adress = adress;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
    }
    public Customer() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getDob() {
        return dob;
    }
    public void setDob(Long dob) {
        this.dob = dob;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public String getPostal_code() {
        return postal_code;
    }
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
    public Integer getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((dob == null) ? 0 : dob.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((adress == null) ? 0 : adress.hashCode());
        result = prime * result + ((postal_code == null) ? 0 : postal_code.hashCode());
        result = prime * result + ((phone_number == null) ? 0 : phone_number.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (dob == null) {
            if (other.dob != null)
                return false;
        } else if (!dob.equals(other.dob))
            return false;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (adress == null) {
            if (other.adress != null)
                return false;
        } else if (!adress.equals(other.adress))
            return false;
        if (postal_code == null) {
            if (other.postal_code != null)
                return false;
        } else if (!postal_code.equals(other.postal_code))
            return false;
        if (phone_number == null) {
            if (other.phone_number != null)
                return false;
        } else if (!phone_number.equals(other.phone_number))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", dob=" + dob + ", age=" + age + ", email=" + email
                + ", adress=" + adress + ", postal_code=" + postal_code + ", phone_number=" + phone_number + "]";
    }

    
    

}
