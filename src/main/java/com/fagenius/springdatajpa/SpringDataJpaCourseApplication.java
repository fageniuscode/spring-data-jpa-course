package com.fagenius.springdatajpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaCourseApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return args->{
			generateRandomStudent(studentRepository);
			Sort sort = Sort.by(Sort.Direction.ASC,"firstName");
			Sort sort0 = Sort.by("firstName").ascending()
					.and(Sort.by("age").descending());
			Sort sort1 = Sort.by(Sort.Direction.ASC,"firstName");
			studentRepository.findAll(sort0)
					.forEach(student -> {
						System.out.println(student.getFirstName() + " " + student.getAge());
					});
			/*Student khane = new Student("Ablaye", "diouf","diuof.faye@gmail.com",21);
			Student ibou = new Student("Ibou", "FAYE","iboulaye.faye@gmail.com",25);
			Student ibou2 = new Student("Ibou", "FAYE","ebuyamak.faye@gmail.com",34);
			Student ngoumbi = new Student("Fatou", "DIOP","fatou.piop@gmail.com",21);
			Student gnima = new Student("Marie Louise", "FAYE","louise.faye@gmail.com",3);
			studentRepository.saveAll(List.of(khane, gnima,ibou, ngoumbi, ibou2));
			System.out.println(studentRepository.count());
			studentRepository
					.findById(2L)
					.ifPresentOrElse(
							System.out::println,
							() -> System.out.println("Student with ID 2 is not found")
					);

			studentRepository
					.findById(8L)
					.ifPresentOrElse(
							System.out::println,
							() -> System.out.println("Student with 8L isnot found")
					);

			List<Student> students = studentRepository.findAll();
			students.forEach(System.out::println);

			studentRepository
					.findById(1L)
					.ifPresent(student -> studentRepository.deleteById(1L));

			System.out.println(studentRepository.count());

			studentRepository
					.findStudentByEmail("gnia.faye@gmail.com")
					.ifPresentOrElse(System.out::println,
					()-> System.out.println("Student with email : gnia.faye@gmail.com not found"));

			studentRepository
					.findStudentsByFirstNameEqualsAndAgeIsGreaterThan("Ibou",12)
					.forEach(System.out::println);

			studentRepository
					.findStudentByFirstNameEqualsAndLastNameEquals("Ibou","FAYE")
					.forEach(System.out::println);

			studentRepository
					.findStudentByFirstNameEqualsAndAgeIsGreaterThanEqual("Ibou",25)
					.forEach(System.out::println);

			studentRepository
					.findStudentsByFirstNameEqualsAndAgeIsLessThan("Ibou", 12)
					.forEach(System.out::println);

			studentRepository
					.findStudentByFirstNameEqualsAndAgeEquals("Ibrahima", 10)
					.forEach(System.out::println);

			studentRepository
					.selectStudentWhereFirstNameAndAgeGreaterOrEqualNative("Ibou",2)
					.forEach(System.out::println);
			System.out.println("-_-_-_-_-_-_ =>>> Delete Student");
			System.out.println(studentRepository.deleteStudentById(18L)); */
		};
	}

	private void generateRandomStudent(StudentRepository studentRepository){
		Faker faker = new Faker();
		for(int i =0; i<20; i++){
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@fagenius.edu",firstName,lastName);
			Student student = new Student(
					firstName,
					lastName,
					email,
					faker.number().numberBetween(15,75)
			);
			studentRepository.save(student);
		}
	}

}
