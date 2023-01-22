package com.app.saloon.Model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;

    String clientName;
    String clientPhone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Functionary functionary;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Saloon saloon;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active = true;

    private Boolean done;

}
