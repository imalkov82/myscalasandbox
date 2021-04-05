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


class User(val name: String){
    var room: ChatRoom = null
    private val chatLog: ListBuffer[String]= ListBuffer[String]()
    private val friends: ListBuffer[String] = ListBuffer[String]()
    var status: String = ""
    var isActive = false

    def updateStatus(msg: String): Unit = status = msg

    def receive(sender: String, message: String): Unit = {
        println(s"from ${sender} to $name, $message")
    }

    def privateMsg(who: String, message: String) =
        if(room != null) room.message(name, who, message)
}

class UsersManager(){
    val users = ListBuffer[User]()

    def singinUser(name: String): Boolean = {
        users.find(_.name == name).getOrElse(false)
        //if(users.map(_.name).contains(name)) users.find(_.name == name).get.isActive = true
        true
    } 

    def registerUser(name: String) = 
        if(!users.map(_.name).contains(name)) users :+ new User(name)
    
    def isExist(name: String): Boolean = users.map(_.name).contains(name)
}

class ChatRoom(){
    val usersManager = new UsersManager()
    val persons = ListBuffer[User]()
    
    def join(user: User) = {
        // if ()
        broadcast("room", s"${user.name} joins the chat")
        persons :+ user
        user.room = this
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
    val usersManager = new UsersManager()

}