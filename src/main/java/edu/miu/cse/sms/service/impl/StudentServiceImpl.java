package edu.miu.cse.sms.service.impl;

import edu.miu.cse.sms.dto.request.StudentRequestDTO;
import edu.miu.cse.sms.dto.response.StudentResponseDTO;
import edu.miu.cse.sms.model.Student;
import edu.miu.cse.sms.repository.StudentRepository;
import edu.miu.cse.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        //Write your code here
        return studentRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getStudentByRegisterNumber(String registerNumber) {
        //Write your code here
        return studentRepository.findByRegisterNumber(registerNumber)
                .map(this::convertToResponseDTO)
                .orElse(null);
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        //Write your code here
        Student student = new Student(studentRequestDTO.registerNumber(), studentRequestDTO.name(), studentRequestDTO.email(), studentRequestDTO.phone());
        Student savedStudent = studentRepository.save(student);
        return convertToResponseDTO(savedStudent);
    }

    private StudentResponseDTO convertToResponseDTO(Student student) {
        //Write your code here
        return new StudentResponseDTO(student.getName(), student.getRegisterNumber(), student.getEmail(), student.getPhone());
    }
}
