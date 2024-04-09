package org.jan.mapping.dto;

public record StudentDto(Long id,String name,String email,String semestre) {
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getSemestre() {
        return semestre;
    }
}

