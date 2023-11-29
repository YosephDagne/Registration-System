//This registration system works for students who are undergraduate
import java.util.Scanner;

// Person super class
class Person {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected char sex;

    // Constructor
    public Person(String firstName, String lastName, int age, char sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        setSex(sex); // Validate and set sex
    }

    // Set sex method with validation
    protected void setSex(char sex) {
        if (sex == 'f' || sex == 'm') {
            this.sex = sex;
        } else {
            throw new IllegalArgumentException("Invalid sex input. Please enter 'f' for female or 'm' for male.");
        }
    }

    // Display method (overridden in derived classes)
    public void display() {
         System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Sex: " + sex);
    }
}

// Student subclass
class Student extends Person {
    final int id;
    final double cgpa;
    final int academicYear;
    static int semester;
    private String college;
    private String department;
    static int numCourses;
    private String[] courseNames;

    // Constructor
    public Student(String firstName, String lastName, int age, char sex, int id, double cgpa, int academicYear, String college, String department) {
        super(firstName, lastName, age, sex);
        this.id = id;
        this.cgpa = cgpa;
        this.academicYear = academicYear;
        this.semester = semester;
        this.college = college;
        this.department = department;
        setCourses();
    }

    // Overloaded constructor with additional parameter
    public Student(String firstName, String lastName, int age, char sex, int id, double cgpa, int academicYear, String college, String department, String[] courseNames) {
        this(firstName, lastName, age, sex, id, cgpa, academicYear, college, department);
        this.courseNames = courseNames;
        this.numCourses = courseNames.length;
    }

    // Overridden display method
    @Override
    public void display() {
        System.out.println();
        System.out.println("Successfully Registered!");

        System.out.println("Student Information:");
        super.display(); // Call display method of the base class
        System.out.println("ID: " + id);
        System.out.println("CGPA: " + cgpa);
        System.out.println("Academic Year: " + academicYear);
        System.out.println("current semester : "  + semester);
        System.out.println("College: " + college);
        System.out.println("Department: " + department);
        System.out.println("Enroll Courses: ");
        for (int i = 0; i < numCourses; i++) {
            System.out.println((i + 1) + ". " + courseNames[i]);
        }
    }

    // Set courses method
    private void setCourses() {
        Scanner scanner = new Scanner(System.in);

        // Validate and set the number of courses
        while (true) {
            try {
                System.out.print("Enter the number of enroll courses  : ");
                numCourses = Helper.getInRangeIntInput(scanner, "", 3, 15);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect  number of courses. Please enter correct number of enroll courses  ");
            }
        }

        // Initialize array for course names
        courseNames = new String[numCourses];

        // Set course names
        for (int i = 0; i < numCourses; i++) {
            System.out.print("Enter the name of course #" + (i + 1) + ": ");
            courseNames[i] = Helper.getStringInput(scanner, "");
        }
    }
}

  class RegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            // Get student information for registration
            System.out.println("Student Registration System");
            System.out.println("---------------------------");

            String firstName = Helper.getStringInput(scanner, "Enter First Name: ");
            String lastName = Helper.getStringInput(scanner, "Enter Last Name: ");
            int age = Helper.getInRangeIntInput(scanner, "Enter Age : ", 17, 70);
            char sex = Helper.getSexInput(scanner, "Enter Sex (f/m): ");
            int id = Helper.getExactLengthIntInput(scanner, "Enter ID (four digits): ", 4);
            double cgpa = Helper.getInRangeDoubleInput(scanner, "Enter CGPA (2.0-4.0): ", 2.0, 4.0);
            int academicYear = Helper.getInRangeIntInput(scanner, "Enter Academic Year (1-7): ", 1, 7);
            int semester = Helper.getInRangeIntInput(scanner, "Enter current semester ( '1' or '2'): ", 1, 2);
            String college = Helper.getStringInput(scanner, "Enter College: ");
            String department = Helper.getStringInput(scanner, "Enter Department: ");

            // Create Student object using overloaded constructor
            Student student = new Student(firstName, lastName, age, sex, id, cgpa, academicYear, college, department);

            // Display registration details using overridden display method
            student.display();

            // Ask if the user wants to register another student
            System.out.print("Do you want to register another student? (Enter 'yes' to continue): ");
        } while (scanner.nextLine().equalsIgnoreCase("yes"));
    }
}
// we use helper methods to obtain and validate the student information
class Helper {
    public static int getInRangeIntInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            int input = getIntInput(scanner);
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("Incorrect input. Please enter a input between " + min + " and " + max + ".");
            }
        }
    }

    public static int getExactLengthIntInput(Scanner scanner, String prompt, int length) {
        while (true) {
            System.out.print(prompt);
            int input = getIntInput(scanner);
            if (String.valueOf(input).length() == length) {
                return input;
            } else {
                System.out.println("Incorrect input. Please enter a " + length + "-digit number.");
            }
        }
    }

    public static double getInRangeDoubleInput(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            double input = getDoubleInput(scanner);
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("Incorrect input. Please enter a value between " + min + " and " + max + ".");
            }
        }
    }

    public static String getStringInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (!input.isEmpty() && input.matches("[a-zA-Z]+")) {
                return input;
            } else {
                System.out.println("Incorrect input. Please enter a valid data.");
            }
        }
    }

    public static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input. Please enter a valid data.");
            }
        }
    }

    public static double getDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input. Please enter a valid data.");
            }
        }
    }

    public static char getSexInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("f") || input.equals("m")) {
                return input.charAt(0);
            } else {
                System.out.println("Incorrect input. Please enter 'f' for female or 'm' for male.");
            }
        }
    }
}

/*
// This test focuses on the interaction between the RegistrationSystem and the user input
import org.junit.Test;
import static org.junit.Assert.*;

public class RegistrationSystemTest {

    @Test
    public void testStudentRegistrationAndDisplay() {
        // Mock user input for testing
        String[] mockInput = {"John", "Doe", "25", "m", "1234", "3.5", "2", "Engineering College", "Computer Science", "3", "Math", "Physics", "Java Programming"};

        // Redirect System.in to use mock input
        System.setIn(new MockInputStream(mockInput));

        // Execute main method
        RegistrationSystem.main(new String[]{});

        // Reset System.in to standard input
        System.setIn(System.in);

        // No assertion is performed here as it's difficult to test user interaction.
        // It's recommended to refactor the code to make it more testable (e.g., separate logic from input/output).
    }

    // Add more tests as needed

    // Helper class for mocking System.in
    private static class MockInputStream extends java.io.InputStream {
        private final String[] input;
        private int position = 0;

        public MockInputStream(String[] input) {
            this.input = input;
        }

        @Override
        public int read() {
            if (position == input.length) {
                return -1;
            }

            int result = input[position].charAt(0);
            position++;

            return result;
        }
    }
}
*/