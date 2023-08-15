package part1recap

import scala.concurrent.Future

object AdvancedRecap extends App {
  // partial functions
  val partialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 65
    case 5 => 999
  }
  
  val pf = (x: Int) => x match {
    case 1 => 42
    case 2 => 65
    case 5 => 999
  }
  
  val function: (Int => Int) = partialFunction
  println(partialFunction.isDefinedAt(3)) // false
  println(partialFunction.isDefinedAt(5)) // true
  
  val divide2: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }
  
  println(divide2(4))
  
  val modifiedList = List(1, 2, 3).map {
    case 1 => 42
    case _ => 0
  }
  println(modifiedList)
  
  // lifting
  val lifted = partialFunction.lift // total function Int => Option[Int]
  println(lifted(2))
  println(lifted(5000))
  
  // orElse
  val pfChain = partialFunction.orElse[Int, Int] {
    case 60 => 9000
  }.orElse[Int, Int] {
    case 457 => 9
  }
  
  pfChain(5) // 999 per partialFunction
  pfChain(60) // 9000
  pfChain(457) // would throw a MatchError if no case were defined
  
  // type aliases
  type ReceiveFunction = PartialFunction[Any, Unit]
  
  def receive: ReceiveFunction = {
    case 1 => println("Hello")
    case _ => println("Hello")
  }
  
  // implicits
  implicit val timeout: Int = 3000
  def setTimeout(f: () => Unit)(implicit timeout: Int) = f()
  
  setTimeout(() => println("timeout")) // extra parameter list omitted
  
  // implicit conversions
  // 1) Implicit defs
  case class Person(name: String) {
    def greet = s"Hi, my name is $name"
  }
  
  implicit def fromStringToPerson(string: String): Person = Person(string)
  "Peter".greet
  // fromStringToPerson("Peter").greet - automatically by the compiler
  
  // 2) Implicit classes
  implicit class Dog(name: String) {
    def bark = println("bark!")
  }
  "Lassie".bark
  // new Dog("Lassie").bark - automatically done by the compiler
  
  // organize
  // local scope
  implicit val inverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  println(List(1, 2, 3).sorted) // List(3, 2, 1)
  
  // imported scope
  
  import scala.concurrent.ExecutionContext.Implicits.global
  
  val future = Future {
    println("Hello, future")
  }
  
  // companion objects of the types included in the call
  object Person {
    implicit val personOrdering: Ordering[Person] = Ordering.fromLessThan(
      (a, b) => a.name.compareTo(b.name) < 0
    )
  }
  
  println(List(Person("Bob"), Person("Alice")).sorted) // List(Person("Alice"), Person("Bob"))
}
