// create class 
class Student{
    // a private  name and age and  the number of student
    private String  name;
    private int age;
    private static int studentCount = 0;



    // create  a constructor methode 
    Student( String name, int age) {
        this.name = name;
        this.age = age;
        studentCount++;
    }


     // create the getters
    String  getName() {
      return  this.name;
    }
    int getAge() {
       return  this.age;
    }
    static int getCount(){
        return studentCount;
    }
}



class GraduateStudent  extends Student {
//  create  a constructor 
GraduateStudent( String name, int age) {
super(name, age);
}



}



// create the  main class
public  class Main{
    public static void main(String[] args) {

Student student1 = new Student("Olivier", 12);
Student student2 = new Student("Kepler", 23);
GraduateStudent grad1 = new GraduateStudent("Alice", 25);



System.out.println(student1.getName() + " age " + student1.getAge());
System.out.println(student2.getName() + " age " + student2.getAge());
// getName() and getAge() were never written in GraduateStudent.
// They are inherited from Student.
System.out.println(grad1.getName() + " age " + grad1.getAge());

System.out.println("Number of students: " + Student.getCount());

    }
}