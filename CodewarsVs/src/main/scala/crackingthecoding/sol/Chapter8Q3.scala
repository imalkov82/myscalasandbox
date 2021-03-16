package crackingthecoding.sol

/*
Q: 
+ Design a musical jukebox using object-oriented principles.

A:
System components: 
- CD 
- Song 
- Artist 
- Playlist 
- Jukebox
- Display (displays details on the screen)

Now, let's break this down further and think about the possible actions

*/

case class Song(name: String, artist: String){
    def play(): Unit = println(s"playing `$name` by `$artist`")
}

class JukeBox(val songs: Array[Song]){
    def showSongs(): Unit = 
        songs.zipWithIndex.foreach{
            case (Song(name, artist), n) => println(s"${n+1}. $name")
            case _ => println("---")
        }
    def playSong(n: Int): Unit = {
        if (n >= 0 && n < songs.length) songs(n).play()
        else println("invalid index")
    }
}

object JukeBox{
    def apply(): JukeBox = 
       new JukeBox(Array(Song("enter sandman", "metalica"), Song("schesm", "tool")))
}

object Chapter8Q3 extends App{
    val jb = JukeBox()
    jb.showSongs()
    jb.playSong(0)
}