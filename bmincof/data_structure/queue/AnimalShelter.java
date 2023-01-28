import java.util.LinkedList;

/*
자바에서 제공하는 LinkedList로 개와 고양이를 분양하는 동물 분양소를 만들 것
조건
1. 사용자는 동물의 종류만 선택할 수 있다.
2. 선택한 동물 중에서 오래 있었던 동물 순서로 분양해야한다.(Queue의 형태)
 */
public class AnimalShelter {
    public static void main(String[] args) {
        Dog d1 = new Dog("개1");
        Dog d2 = new Dog("개2");
        Dog d3 = new Dog("개3");

        Cat c1 = new Cat("고양이1");
        Cat c2 = new Cat("고양이2");
        Cat c3 = new Cat("고양이3");

        Shelter s = new Shelter();
        s.add(d1);
        s.add(c1);
        s.add(d2);
        s.add(c2);
        s.add(c3);
        s.add(d3);
        // (순서) 개1 -> 고양이1 -> 개2 -> 고양이2 -> 고양이3 -> 개3 (마지막)

        System.out.println(s.getCat());
        // 고양이1
        System.out.println(s.getDog());
        // 개1
        System.out.println(s.getAnimal());
        // 개2
        System.out.println(s.getAnimal());
        // 고양이2
        System.out.println(s.getAnimal());
        // 고양이3
        System.out.println(s.getAnimal());
        // 개3
    }

}

enum AnimalType {
    DOG, CAT
}

abstract class Animal {
    AnimalType type;
    String name;
    int order;

    Animal (AnimalType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return order + ") type : " + type + ", name : " + name;
    }

}

class Dog extends Animal {
    Dog(String name) {
        super(AnimalType.DOG, name);
    }
}

class Cat extends Animal {
    Cat(String name) {
        super(AnimalType.CAT, name);
    }
}

class Shelter {
    LinkedList<Dog> dogs = new LinkedList<>();
    LinkedList<Cat> cats = new LinkedList<>();
    private int order;
    Shelter() {
        order = 1;
    }

    // animal을 동물 종류에 맞게 저장
    void add(Animal animal) {
        animal.order = order++;
        if(animal.type == AnimalType.DOG) {
            dogs.add((Dog) animal);
        } else if(animal.type == AnimalType.CAT) {
            cats.add((Cat) animal);
        }
    }

    // 전체 순서에 상관없이 일찍온 개부터 반환
    Animal getDog() {
        return dogs.poll();
    }

    // 전체 순서에 상관없이 일찍온 고양이부터 반환
    Animal getCat() {
        return cats.poll();
    }

    // 동물 종류에 상관없이 일찍온 동물부터 반환
    Animal getAnimal() {
        if(dogs.isEmpty() && cats.isEmpty()) return null;
        else if(dogs.isEmpty()) return cats.poll();
        else if(cats.isEmpty()) return dogs.poll();
        else {
            if(dogs.peek().order < cats.peek().order) return dogs.poll();
            else return cats.poll();
        }
    }

}

