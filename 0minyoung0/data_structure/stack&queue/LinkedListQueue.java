// LinkedList로 Queue 구현하기
// https://youtu.be/PkTKe_wUfUI
// 개와 고양이만 분양하는 분양소가 있다.
// 분양받는 사람은 동물의 종류만 고를 수 있고,
// 분양소에서 가장 오래 있던 순서로 자동으로 분양될 동물이 정해지는 클래스를 구현하시오.
// 단, 자바에서 제공하는 LinkedList로 구현하시오

import java.util.LinkedList;

enum AnimalType{
	DOG, CAT
}

abstract class Animal{
	AnimalType type;
	String name;
	int order;
	Animal (AnimalType type, String name){
		this.type = type;
		this.name = name;
	}
	void setOrder(int order) {
		this.order = order;
	}
	int getOrder() {
		return order;
	}
	String info() {
		return order + ") type: " + type + ", name" + name;
	}
}
class Dog extends Animal{
	Dog(String name){
		super(AnimalType.DOG, name);
	}
}
class Cat extends Animal{
	Cat(String name){
		super(AnimalType.CAT, name);
	}
}

class AnimalShelter{
	LinkedList<Dog> dogs = new LinkedList<>();
	LinkedList<Cat> cats = new LinkedList<>();
	int order;
	AnimalShelter(){
		order = 1;
	}
	void enqueue(Animal animal) {
		animal.setOrder(order);
		order++;
		if (animal.type == AnimalType.DOG) {
			dogs.addLast((Dog) animal);
		}else if (animal.type == AnimalType.CAT) {
			cats.addLast((Cat) animal);
		}
	}
	Animal dequeueDog() {
		return dogs.poll();
	}
	Animal dequeueCat() {
		return cats.poll();
	}
	Animal dequeue() {
		if (dogs.size() == 0 && cats.size() == 0) {
			return null;
		} else if (dogs.size() == 0) {
			return cats.poll();
		} else if (cats.size() == 0) {
			return dogs.poll();
		}
		
		Animal dog = dogs.peek();
		Animal cat = cats.peek();
		if (cat.order < dog.order) {
			return cats.poll();
		} else {
			return dogs.poll();
		}
	}
}

public class LinkedListQueue {
	public static void main(String[] args) {
		Dog d1 = new Dog("puppy");
		Dog d2 = new Dog("chichi");
		Dog d3 = new Dog("choco");
		Cat c1 = new Cat("shasha");
		Cat c2 = new Cat("miumiu");
		Cat c3 = new Cat("gaga");
		
		AnimalShelter as = new AnimalShelter();
		as.enqueue(d1);
		as.enqueue(c1);
		as.enqueue(d2);
		as.enqueue(c2);
		as.enqueue(d3);
		as.enqueue(c3);
		
		System.out.println(as.dequeueCat().info()); // 2) type: CAT, nameshasha
		System.out.println(as.dequeueDog().info()); // 1) type: DOG, namepuppy
		System.out.println(as.dequeue().info());	// 3) type: DOG, namechichi
		System.out.println(as.dequeue().info());	// 4) type: CAT, namemiumiu
		System.out.println(as.dequeue().info());	// 5) type: DOG, namechoco
		System.out.println(as.dequeue().info());	// 6) type: CAT, namegaga
	}
}
