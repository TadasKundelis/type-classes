import FormatterInstances._
import FormattingSyntax._

object Main extends App {
  val cat = Cat("Fluffy", 5)
  val car = Car("BMW", "red", 2010)
  println(cat.format) // Cat's name is Fluffy, it's age is 5
  println(car.format) // That's a red BMW, made in year 2010
}

// Type class definition
trait Formatter[A] {
  def format(value: A): String
}

// Type class instances
object FormatterInstances {
  implicit val catPrinter: Formatter[Cat] = new Formatter[Cat] {
    def format(cat: Cat): String = {
      s"Cat's name is ${cat.name}, it's age is ${cat.age}"
    }
  }

  implicit val carPrinter: Formatter[Car] = new Formatter[Car] {
    def format(car: Car): String = {
      s"That's a ${car.color} ${car.brand}, made in year ${car.year}"
    }
  }
}

object FormattingSyntax {
  implicit class FormattingOps[A](value: A) {
    def format(implicit formatter: Formatter[A]): String = {
      formatter.format(value)
    }
  }
}

case class Cat(name: String, age: Int)
case class Car(brand: String, color: String, year: Int)
