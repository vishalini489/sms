import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        StudentDto dao = new StudentDto();

        while (true) {

            System.out.println("\n---------------SMS---------------");
            System.out.println("1. Add Student ");
            System.out.println("2. View Student by ID ");
            System.out.println("3. View All Students ");
            System.out.println("4. Update Student By ID ");
            System.out.println("5. Delete Student By ID ");
            System.out.println("6. Exit ");

            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input");
                sc.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.println("Enter Student ID: ");
                    int id;

                    try {
                        id = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Enter valid ID");
                        sc.nextLine();
                        continue;
                    }

                    System.out.println("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.println("Enter Student Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Student Course: ");
                    String course = sc.nextLine();

                    dao.addStudent(new Student(id, name, age, course));
                    System.out.println("Student Added Successfully");
                    break;

                case 2:
                    System.out.println("Enter Student ID: ");
                    int idFromConsole = sc.nextInt();
                    sc.nextLine();

                    Student stds = dao.findByRollNumber(idFromConsole);

                    if (stds != null) {
                        System.out.println(stds.displayStudent());
                        System.out.println("No student found with ID " + idFromConsole);
                    }
                    break;

                case 3:
                    List<Student> allStudents = dao.getStudents();

                    if (allStudents.isEmpty()) {
                        System.out.println("No Students Found");
                    } else {
                        for (Student student : allStudents) {
                            System.out.println(student.displayStudent());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Enter Student ID to Update: ");
                    int idToUpdate = sc.nextInt();
                    sc.nextLine();

                    Student std = dao.findByRollNumber(idToUpdate);

                    if (std != null) {

                        System.out.println("Enter new name (Press Enter to skip): ");
                        String updateName = sc.nextLine();

                        System.out.println("Enter new age (0 to skip): ");
                        int updateAge = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Enter new course (Press Enter to skip): ");
                        String updateCourse = sc.nextLine();

                        Student updateStudent = new Student(idToUpdate, updateName, updateAge, updateCourse);

                        if (dao.updateStudent(updateStudent)) {
                            System.out.println("Student Updated Successfully");
                        } else {
                            System.out.println("Update Failed");
                        }
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                case 5:
                    System.out.println("Enter Student ID to Delete: ");
                    int idToDelete = sc.nextInt();
                    sc.nextLine();
                    if (dao.deleteStudent(idToDelete)) {
                        System.out.println("Student Deleted Successfully");
                    } else {
                        System.out.println("Student Not Found");
                    }
                    break;
                case 6:
                    System.out.println("Bye Bye...");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}