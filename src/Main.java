// Student is the parent class (superclass).
class Student {

    // static field: ONE copy shared by every Student object.
    private static int studentCount = 0;

    // private: only code inside Student can touch it.
    private String name;

    // protected: Student AND its subclasses can access it directly.
    protected int age;

    // Constructor: runs when a Student (or subclass) is created.
    Student(String name, int age) {
        this.name = name;
        this.age = age;
        studentCount++;   // count every student that gets built
    }

    // Getter for name.
    String getName() {
        return this.name;
    }

    // Getter for age.
    int getAge() {
        return this.age;
    }

    // Static method: belongs to the class, so no object is needed.
    static int getStudentCount() {
        return studentCount;
    }
}

// GraduateStudent "is a" Student, so it inherits everything public or protected.
class GraduateStudent extends Student {

    // An extra field only graduate students have.
    private String thesisTitle;

    // Constructors are not inherited, so we write our own.
    GraduateStudent(String name, int age, String thesisTitle) {
        super(name, age);              // call the parent constructor first
        this.thesisTitle = thesisTitle;
    }

    String getThesisTitle() {
        return this.thesisTitle;
    }

    // Allowed because age is protected. It would NOT compile if age were private.
    void haveBirthday() {
        this.age++;
    }
}

// The class that holds main, where the program starts.
public class Main {
    public static void main(String[] args) {

        GraduateStudent graduateStudent1 = new GraduateStudent("Alice", 25, "Machine Learning");

        // getName() and getAge() are inherited from Student.
        System.out.println(graduateStudent1.getName() + ", "
                + graduateStudent1.getAge() + ", "
                + graduateStudent1.getThesisTitle());

        graduateStudent1.haveBirthday();
        System.out.println(graduateStudent1.getAge());

        System.out.println("Total students: " + Student.getStudentCount());
    }
}