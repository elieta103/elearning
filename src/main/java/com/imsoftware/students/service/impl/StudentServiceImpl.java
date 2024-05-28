package com.imsoftware.students.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.imsoftware.students.repository.StudentRepository;
import org.springframework.stereotype.Service;

import com.imsoftware.students.domain.StudentDTO;
import com.imsoftware.students.entity.Student;
import com.imsoftware.students.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	private final StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Collection<StudentDTO> findAll() {
		return studentRepository.findAll().stream().map(new Function<Student, StudentDTO>() {
			@Override
			public StudentDTO apply(Student student) {
				List<String> programmingLanguagesKnowAbout = student.getSubjects().stream()
						.map(pl -> new String(pl.getName())).collect(Collectors.toList());
				return new StudentDTO(student.getName(), programmingLanguagesKnowAbout);
			}

		}).collect(Collectors.toList());
		
	}

	@Override
	public Collection<StudentDTO> findAllAndShowIfHaveAPopularSubject() {
		// TODO Obtener la lista de todos los estudiantes e indicar la materia más concurrida existentes en la BD e
		// indicar si el estudiante cursa o no la materia más concurrida registrado en la BD.

		String materiaMasCursada = "";
		List<StudentDTO> studentDTOList = studentRepository.findAll()
				.stream().map(item ->{
					return new StudentDTO(item.getName(),
							item.getSubjects().stream().map(subject -> subject.getName()).collect(Collectors.toList()));
				}).collect(Collectors.toList());

		// Frecuencia de las materias
		Map<String,Long> frequency = studentDTOList
				.stream()
				.flatMap(student -> student.getCurrentSubject().stream())
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(frequency);
		Long max = Collections.max(frequency.values());

		// Obtiene la mas cursada
		for(Map.Entry<String, Long> entry: frequency.entrySet()) {
			if(Long.parseLong(entry.getValue().toString()) == max) {
				materiaMasCursada = entry.getKey();
				System.out.println("Materia mas cursada es : " + materiaMasCursada  + " con : " +max +" alumnos.");
				break;
			}
		}
		// El alumno la cursa si/no ?
		final String subject = materiaMasCursada;
		List<StudentDTO> finalListStudent = studentDTOList.stream()
				.map(student ->{
					if(student.getCurrentSubject().contains(subject)){
						student.setCurrentPopularSubject(true);
					}else {
						student.setCurrentPopularSubject(false);
					}
					return  student;
				}).collect(Collectors.toList());
		return finalListStudent;
	}

}
