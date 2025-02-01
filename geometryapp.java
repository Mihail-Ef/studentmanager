import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GeometryApp {

    public interface Shape {
        double calculateArea();
        double calculatePerimeter();
    }

    public static class Circle implements Shape {
        private double radius;

        public Circle(double radius) {
            if (radius <= 0) {
                throw new IllegalArgumentException("Радиус должен быть положительным.");
            }
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }

        @Override
        public double calculatePerimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        public String toString() {
            return "Круг с радиусом " + radius;
        }
    }

    public static class Rectangle implements Shape {
        private double length;
        private double width;

        public Rectangle(double length, double width) {
            if (length <= 0 || width <= 0) {
                throw new IllegalArgumentException("Длина и ширина должны быть положительными.");
            }
            this.length = length;
            this.width = width;
        }

        public double getLength() {
            return length;
        }

        public double getWidth() {
            return width;
        }

        @Override
        public double calculateArea() {
            return length * width;
        }

        @Override
        public double calculatePerimeter() {
            return 2 * (length + width);
        }

        @Override
        public String toString() {
            return "Прямоугольник с длиной " + length + " и шириной " + width;
        }
    }

    public static class Square extends Rectangle {
        public Square(double side) {
            super(side, side);
        }

        @Override
        public String toString() {
            return "Квадрат со стороной " + super.getWidth();
        }
    }

    public static class Triangle implements Shape {
        private double sideA;
        private double sideB;
        private double sideC;

        public Triangle(double sideA, double sideB, double sideC) {
            if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
                throw new IllegalArgumentException("Длины сторон должны быть положительными.");
            }
            if (sideA + sideB <= sideC || sideA + sideC <= sideB || sideB + sideC <= sideA) {
                throw new IllegalArgumentException("Сумма двух сторон должна быть больше третьей.");
            }
            this.sideA = sideA;
            this.sideB = sideB;
            this.sideC = sideC;
        }

        @Override
        public double calculateArea() {
            double s = (sideA + sideB + sideC) / 2;
            return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
        }

        @Override
        public double calculatePerimeter() {
            return sideA + sideB + sideC;
        }

        @Override
        public String toString() {
            return "Треугольник со сторонами " + sideA + ", " + sideB + ", " + sideC;
        }
    }

     public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        // Создаем объекты фигур напрямую
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(4, 6));
        shapes.add(new Square(7));
        shapes.add(new Triangle(3, 4, 5));

       try{
           shapes.add(new Rectangle(1,0));
       } catch (IllegalArgumentException e){
           System.err.println("Ошибка при создании прямоугольника " + e.getMessage());
       }
       try{
          shapes.add(new Triangle(1,2,10));
       } catch (IllegalArgumentException e){
           System.err.println("Ошибка при создании треугольника " + e.getMessage());
       }
      shapes.add(new Circle(9));


        for (Shape shape : shapes) {
            System.out.println(shape);
            System.out.println("Площадь: " + shape.calculateArea());
            System.out.println("Периметр: " + shape.calculatePerimeter());
            System.out.println("---");
        }
    }
}
