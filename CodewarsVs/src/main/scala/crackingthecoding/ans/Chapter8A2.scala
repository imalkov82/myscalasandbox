package crackingthecoding.ans

import scala.collection.mutable.ListBuffer

object Rank extends Enumeration{
    type Rank = Value
    val Responder, Manager, Director = Value
}

import Rank._

case class Caller()

sealed trait Employee{
    private val currentCall = null
    protected val rank: Rank

    def recieveCall(call: Call): Unit = ???
    def callCompleted(): Unit = ???
    def escalateAndReassing(): Unit = ???
    def assingNewCall(): Boolean = ???
    def isFree(): Boolean = ???
    def getRank(): Rank = rank
}

class Responder(override val rank: Rank = Rank.Responder) extends Employee
class Manager(override val rank: Rank = Rank.Manager) extends Employee
class Director(override val rank: Rank = Rank.Director) extends Employee

class Call(c: Caller){
    private var rank: Rank = Rank.Responder
    private val caller: Caller = c
    private var handler: Employee = ???

    def setHandler(e: Employee): Unit = handler = e
    def reply(message: String) = ???
    def getRank(): Rank = rank
    def setRank(r: Rank): Unit = rank = r
    def incrementRank(): Unit = ???
    def disconect(): Unit = ???

}
class CallHandler(){
    private val employeeLevels: List[List[Employee]] = ???
    private val callQueues: ListBuffer[ListBuffer[Call]] = ???

    def getHandlerForCall(c: Call): Option[Employee] = ???
    def dispatchCall(c: Call): Unit = {
        getHandlerForCall(c) match {
            case None => {
                c.reply("Please wait")
                callQueues(c.getRank().id).append(c)
            }
            case Some(e) => {
                e.recieveCall(c)
                c.setHandler(e)
            }
        }
    }
    def assingCall(e: Employee): Boolean = ???

}

object Chapter8A2 extends App {
}