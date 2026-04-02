import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDto {

    static String url = "jdbc:mysql://localhost:3306/college";
    static String user = "root";
    static String password = "vishalini@89";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "select * from sms";

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ResultSet data = ps.executeQuery();

            while (data.next()) {
                Student std = new Student(
                        data.getInt("rollNumber"),
                        data.getString("name"),
                        data.getInt("age"),
                        data.getString("courseName")
                );
                students.add(std);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public Student findByRollNumber(int rollNumber) {
        String sql = "select * from sms where rollNumber=?";
        Student std = null;

        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, rollNumber);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                std = new Student(
                        resultSet.getInt("rollNumber"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("courseName")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return std;
    }

    public void addStudent(Student student) {
        String sql = "insert into sms (rollNumber, name, age, courseName) values (?,?,?,?)";

        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getCourseName());

            System.out.println(ps.executeUpdate());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateStudent(Student student) throws Exception {
        Student old = findByRollNumber(student.getId());

        if (old == null) {
            System.out.println("Student not found");
            return false;
        }

        String sql = "update sms set name=?, age=?, courseName=? where rollNumber=?";

        String name = (student.getName() != null && !student.getName().isEmpty())
                ? student.getName()
                : old.getName();

        int age = (student.getAge() != 0)
                ? student.getAge()
                : old.getAge();

        String course = (student.getCourseName() != null && !student.getCourseName().isEmpty())
                ? student.getCourseName()
                : old.getCourseName();

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setInt(4, old.getId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteStudent(int id) throws Exception {

        Student student = findByRollNumber(id);

        if (student == null) {
            return false;
        }

        String sql = "delete from sms where rollNumber=?";

        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}









//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//public class StudentDto {
//    public static void main(String[] args) throws Exception {
//
//        String url = "jdbc:mysql://localhost:3306/college";
//        String user = "root";
//        String password = "vishalini@89";
//
//        Connection con = DriverManager.getConnection(url, user, password);
//
//        String sql = "CREATE TABLE IF NOT EXISTS sms (" +
//                "rollNumber INT PRIMARY KEY, " +
//                "name VARCHAR(50) NOT NULL, " +
//                "age INT, " +
//                "courseName VARCHAR(100))";
//
//        Statement stmt = con.createStatement();
//        stmt.execute(sql);
//
//        System.out.println("Table created successfully");
//
//        stmt.close();
//        con.close();
//    }
//}