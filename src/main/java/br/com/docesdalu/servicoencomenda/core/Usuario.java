package br.com.docesdalu.servicoencomenda.core;

import java.time.LocalDate;
import java.util.List;

public class Usuario {
    private Long id;
    private String nome;
    private String telefone;
    private String senha;
    private List<Roles> roles;
    private LocalDate created;
}