public class Student {
    int id;
    String name;
    int age;
    String courseName;

    Student(int id, String name, int age, String courseName){
        this.id = id;
        this.name = name;
        this.age = age;
        this.courseName = courseName;
    }

    Student(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean displayStudent(){
        System.out.println("ID: " + id + "Name: " + name + "Age: " + age + "Course: " + courseName );
        return false;
    }
}
