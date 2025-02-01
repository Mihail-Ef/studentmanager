import java.io.*;
import java.util.ArrayList;

// Класс Student
class Student implements Serializable {
    private String name;
    private int age;
    private String gender;

    // Обычный конструктор
    public Student() {
        this.name = "";
        this.age = 0;
        this.gender = "";
    }

    // Конструктор с параметрами
    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Билдер для класса
    public static class Builder {
        private String name;
        private int age;
        private String gender;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Student build() {
            return new Student(name, age, gender);
        }
    }

    // Геттеры и сеттеры (при необходимости)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Студент{имя='" + name + '\'' + ", возраст=" + age + ", пол='" + gender + "'}";
    }
}

// Основной класс программы
public class StudentManager {
    public static void main(String[] args) {
        // Создаём список студентов
        ArrayList<Student> students = new ArrayList<>();

        // Добавляем студентов разными способами
        students.add(new Student("Светлана", 20, "Женщина")); // конструктор с параметрами
        students.add(new Student("Николай", 22, "Мужчина"));
        students.add(new Student.Builder() // через билдер
                .setName("Настя")
                .setAge(25)
                .setGender("Женщина")
                .build());
        students.add(new Student.Builder()
                .setName("Диана")
                .setAge(19)
                .setGender("Женщина")
                .build());

        // Имя файла для сохранения
        String fileName = "students.dat";

        // Сохраняем список студентов в файл
        saveStudentsToFile(students, fileName);

        // Читаем список студентов из файла
        ArrayList<Student> loadedStudents = loadStudentsFromFile(fileName);

        // Фильтрация и вывод студентов определённого пола (например, только "Женщина")
        System.out.println("Учащиеся по гендерному признаку 'Женщина':");
        for (Student student : loadedStudents) {
            if ("Женщина".equalsIgnoreCase(student.getGender())) {
                System.out.println(student);
            }
        }
    }

    // Метод для сохранения списка студентов в файл
    private static void saveStudentsToFile(ArrayList<Student> students, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
            System.out.println("Учащиеся успешно сохранены в файле.");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении учащихся в файл: " + e.getMessage());
        }
    }

    // Метод для загрузки списка студентов из файла
    private static ArrayList<Student> loadStudentsFromFile(String fileName) {
        ArrayList<Student> students = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (ArrayList<Student>) ois.readObject();
            System.out.println("Учащиеся успешно загрузились из файла.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке студентов из файла: " + e.getMessage());
        }
        return students;
    }
}
