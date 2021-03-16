package crackingthecoding.sol

import scala.collection.mutable.PriorityQueue
import java.util.Dictionary

/*
Q:
Imagine you have a call center with three levels of employees: respondent,
manager, and director. An incoming telephone call must be first allocated to a
respondent who is free. If the respondent can't handle the call, he or she must
escalate the call to a manager. If the manager is not free or not able to handle it,
then the call should be escalated to a director. Design the classes and data struc-
tures for this problem. Implement a method dispatchCall() which assigns a
call to the first available employe

A: 
a. Employee trait, Mng, Dev, Respondent classes. 
b. Call center class - will hold a priority  queue (min heap) to retrieve the available responder and forward it to  data structure that represents a occupied calls .
(???) The Employee class can place himself into the queue or there will be a class that will contain this responsibility
*/

// CODE IMPLEMENTATION
/*
sealed trait Employee{
    var isAvailable = true

    def recieveCall(): Boolean = {
        isAvailable = false
        println(isAvailable)
        // Thread.sleep(1000)
        // isAvailable = true
        isAvailable
    }
}

case class Respondent(val id: Int) extends Employee
case class Manager(val id: Int) extends Employee
case class Director(val id: Int) extends Employee

class CallCentre(){
    // val minHeap = PriorityQueue[Employee]()(Ordering.by((_: Employee).priority))
    val respondents: List[Respondent] = (0 to 4).map((x: Int) => Respondent(x)).toList
    val managers: List[Manager] = List(Manager(0))
    val directors: List[Director] = List(Director(0))

    def dispatchCall(): Boolean = {
        respondents.find(_.isAvailable).headOption match {
            case None => managers.find(_.isAvailable).getOrElse(directors.find(_.isAvailable).headOption) match {
                case None => false
                case Some(value) => {println(s"$value"); value.as[Employee].recieveCall()}
            }
            case Some(e) => {
                println(s"$e")
                e.recieveCall() 
            }
        }
    }
}
*/
// companion object
object Chapter8Q2 extends App {
}