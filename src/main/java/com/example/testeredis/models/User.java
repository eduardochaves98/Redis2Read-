package com.example.testeredis.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.annotation.Transient;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.HashSet;
import java.util.Set;

@Data
@RedisHash
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties(value = {"password","passwordConfirm"}, allowSetters = true)
public class User {
    @Id
    @ToString.Include
    private String id;

    @NotNull
    @Size(min = 2, max = 48)
    @ToString.Include
    private String name;

    @NotNull
    @Email
    @EqualsAndHashCode.Include
    @ToString.Include
    @Indexed
    private String email;

    @NotNull
    private String password;

    @Transient
    private String passwordConfirm;

    @Reference
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    @Reference
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
    }

}
