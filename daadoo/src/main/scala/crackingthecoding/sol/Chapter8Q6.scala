package crackingthecoding.sol
/*
What specific actions does it need to support?
• Signing online and offline.
• Add requests (sending, accepting, and rejecting).
• Updating a status message.
• Creating private and group chats.
• Adding new messages to private and group chats.


Core Objects:
==============
- User
- ChatRoom
- UsersManager
- 


Relationships:
==============

Design Patterns:
================
*/

import scala.collection.mutable.ListBuffer


class User(val name: String, val room: ChatRoom, val manager: UsersManager){
    private val chatLog: ListBuffer[String]= ListBuffer[String]()
    private val friends: ListBuffer[String] = ListBuffer[String]()
    var status: String = ""


    def updateStatus(msg: String): Unit = status = msg

    def request() = ???

    def receive(sender: String, message: String): Unit = {
        println(s"from ${sender} to $name, $message")
    }

    def privateMsg(who: String, message: String) =
        room.message(name, who, message)
}

class UsersManager(val room: ChatRoom){
    val users = ListBuffer[User]()

    def singinUser(name: String): Boolean = users.map(_.name).contains(name) 

    def registerUser(name: String) = 
        if(!users.map(_.name).contains(name)) users :+ new User(name, room, this)
}

class ChatRoom(){
    val persons = ListBuffer[User]()
    
    def join(User: User) = {
        broadcast("room", s"${User.name} joins the chat")
        persons :+ User
    }

    def broadcast(source: String, message: String) = {
        for(
            p <- persons
            if p.name != source
        ) yield p.receive(source, message) 
    }
    
    def message(source: String, destination: String, message: String) = {
      for(
          p <- persons
          if p.name == destination
      )  yield p.receive(source, message)
    }
}

object Chapter8Q6 extends App{
    
}