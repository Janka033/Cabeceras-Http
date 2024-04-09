package org.jan.service.impl;

import org.jan.mapping.dto.StudentDto;
import org.jan.mapping.mapper.StudentMapper;
import org.jan.model.Student;
import org.jan.service.StudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private static final List<Student> students = Arrays.asList(
            new Student(1L, "Juan", "juan@gmail.com", "Primero"),
            new Student(2L, "María", "maria@gmail.com", "Segundo"),
            new Student(3L, "Pedro", "pedro@gmail.com", "Tercero")
    );

    @Override
    public List<StudentDto> listar() {
        return students.stream()
                .map(StudentMapper::mapFrom)
                .collect(Collectors.toList());
    }
}
