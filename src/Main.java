//create a  class student
class Student {
    // create private elements
   private String name;
private int age;
    private static int studentCount = 0;


    // create a constructor methode 
    Student(String name, int age) {
        this.name = name;
        this.age  = age;
        studentCount++;
    }



    // create  getters
    String getName() {
        return this.name;
    }
     
     int getAge() {
        return this.age;
     }

 static  int getCount() {
        return studentCount;
     }

     void haveBirthday() {
  this.age++;
}
    
}

// create an enheritance class  of Student
class graduateStudent extends Student {
     
private String title;

//create  constructor of graduateStudent
 graduateStudent(String  name , int age, String title ){
    super(name, age);
    this.title  = title;

    

 }

String getTitle(){
    return  this.title;
}






}




// the main  class
public class Main{
    public static  void  main(String[] args) {

Student student1 = new Student("Olivier", 12);
graduateStudent student2 = new graduateStudent("kepler", 23, "Math");
student2.haveBirthday();

System.out.println("Name: " + student1.getName() + "  age: " + student1.getAge());
System.out.println("Name: " + student2.getName() + "  age: " + student2.getAge() + " title: " + student2.getTitle());
System.out.println("Number of student: " + student1.getCount());
System.out.println("New age: " + student2.getAge());


    }
}