package com.kth.project_dollarstore.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import java.util.ArrayList;

@Entity
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ReceiptMetaData> receipts = new ArrayList<>();

    public Customer(Integer id, String name, Long dob, Integer age, String email, String address, String postalCode,
                    Integer phoneNumber) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.email = email;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
    }

    public Customer(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<ReceiptMetaData> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<ReceiptMetaData> receipts) {
        this.receipts = receipts;
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
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", dob=" + dob + ", age=" + age + ", email=" + email
                + ", address=" + address + ", postalCode=" + postalCode + ", phoneNumber=" + phoneNumber + "]";
    }
}
