package j4.lesson01ex;

import java.util.Scanner;

interface Shape {
    /**
     * 面積を求める
     * @return 面積
     */
    public float area();

    /**
     * 図形の情報を出力する
     * @return 出力文字列
     */
    public String toString();
}

class Circle implements Shape {
    private float radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    public float area() {
        return (float) (Math.PI * radius * radius);
    }

    public String toString() {
        return "Circle: radius=" + radius + ", area=" + area();
    }
}

class Rectangle implements Shape {
    private float width;
    private float height;

    public Rectangle(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float area() {
        return width * height;
    }

    public String toString() {
        return "Rectangle: width=" + width + ", height=" + height + ", area=" + area();
    }
}

class Cuboid implements Shape {
    private float width;
    private float height;
    private float depth;

    public Cuboid(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public float area() {
        return 2 * (width * height + height * depth + width * depth);
    }

    public String toString() {
        return "Rectangle: width=" + width + ", height=" + height + ", depth=" + depth + ", area=" + area();
    }
}

public class ShapeTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Shape Creation\nCircle -- 1, Rectangle -- 2, Cuboid -- 3: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Create Circle:");
                System.out.println("Enter radius: ");
                float radius = scanner.nextFloat();
                Circle circle = new Circle(radius);
                System.out.println(circle);
                break;
            case 2:
                System.out.println("Create Rectangle:");
                System.out.println("Enter width: ");
                float width = scanner.nextFloat();
                System.out.println("Enter height: ");
                float height = scanner.nextFloat();
                Rectangle rectangle = new Rectangle(width, height);
                System.out.println(rectangle);
                break;
            case 3:
                System.out.println("Create Cuboid:");
                System.out.println("Enter width: ");
                width = scanner.nextFloat();
                System.out.println("Enter height: ");
                height = scanner.nextFloat();
                System.out.println("Enter depth: ");
                float depth = scanner.nextFloat();
                Cuboid cuboid = new Cuboid(width, height, depth);
                System.out.println(cuboid);
                break;
        }
    }
}
