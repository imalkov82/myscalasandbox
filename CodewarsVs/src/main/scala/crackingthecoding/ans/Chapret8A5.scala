package crackingthecoding.ans

import scala.collection.mutable.HashMap

/*
Since the problem doesn't describe much about the functionality, let's assume we want
to design a basic online reading system which provides the following functionality:
• User membership creation and extension.
• Searching the database of books.
• Reading a book.
• Only one active user at a time
• Only one active book by this user.


Core objects:
===============
- OnlineReaderSystem 
    responsible to generate User and asign a library to the User
- User
- Book
- Library
- UserManager
- Display

Relationships:
===============
- 

Design patterns:
===============

*/
case class User(id: Int, desc: String="")

case class Book(id: Int, desc: String="")

class UserManager(){
    private val users = HashMap[Int, User]()

    def addUser(id: Int): Option[User] =
        if (users.contains(id)) None
        else {users.addOne(id, User(id)); Some(users(id))}

    def remove(user: User): Unit = {users.remove(user.id); ()}
        
    def findUser(id: Int): Option[User] = users.get(id)
}

class Library(){
    private val books = HashMap[Int, Book]()

    def addBook(id: Int, desc: String): Option[Book] = 
        if(books.contains(id)) None 
        else {books.addOne(id, Book(id, desc)); Some(books(id))}
    
    def remove(book: Book): Unit = {books.remove(book.id); ()}

    def findBook(id: Int): Option[Book] = books.get(id)
}

class Display(){

}

object Chapret8A5 extends App