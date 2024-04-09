package org.jan.mapping.mapper;

import org.jan.mapping.dto.StudentDto;
import org.jan.model.Student;

public class StudentMapper {

    public static StudentDto mapFrom(Student student){
        return new StudentDto(student.getId(),student.getName(), student.getEmail(), student.getSemestre());
    }
    public static Student mapFromDto(StudentDto student){
        return Student.builder()
                .id(student.id())
                .name(student.name())
                .email(student.email())
                .semestre(student.semestre())
                .build();
    }
}
