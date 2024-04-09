package org.jan.service;

import org.jan.mapping.dto.StudentDto;

import java.util.List;

public interface StudentService<T>{
    List<StudentDto> listar();

}
