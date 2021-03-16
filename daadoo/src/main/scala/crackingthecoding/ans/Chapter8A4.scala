package crackingthecoding.ans

import scala.collection.mutable.ArrayBuffer

object VehicleSize extends Enumeration{
    type VehicleSize = Value
    val Motorcycle, Compact, Large = Value

}

import VehicleSize._

trait Vehicle {
    protected val parkingSpots: ArrayBuffer[ParkingSpot] = ArrayBuffer.empty[ParkingSpot]
    protected val licensePlate: String
    protected val spotsNeeded: Int
    protected val size: VehicleSize

    def parkInSpot(s: ParkingSpot): Unit = parkingSpots += s
    def clearSpot()
    def catFitInSpot(s: ParkingSpot): Boolean
}

class Bus() extends Vehicle{

  override protected val licensePlate: String = ???

  override protected val spotsNeeded: Int = 5

  override protected val size: VehicleSize.VehicleSize = VehicleSize.Large

  override def clearSpot(): Unit = ???

  override def catFitInSpot(s: ParkingSpot): Boolean = ???


}

class Motorcycle() extends Vehicle{

  override protected val licensePlate: String = ???

  override protected val spotsNeeded: Int = 1

  override protected val size: VehicleSize.VehicleSize = VehicleSize.Motorcycle

  override def clearSpot(): Unit = ???

  override def catFitInSpot(s: ParkingSpot): Boolean = ???

}

class ParkingLot(){
    private val levels: List[Level] = List.empty[Level]
    private final val NUM_LEVELS = 5
    def parkVehicle(v: Vehicle): Boolean = ???
}

class Level(flr: Int, numberSpots: Int){
    private val floor: Int = flr
    private val spots: List[ParkingSpot] = List.empty[ParkingSpot]
    private final val SPOT_PER_ROW = 10
    var availableSpots: Int = 0
    
    def parkVehicle(v: Vehicle): Boolean = ???

    private def parkStartingAtSpot(num: Int, vehicle: Vehicle): Boolean = ???

    private def findAvailableStops(vehicle: Vehicle): Int = ???

    def spotFreed(): Unit = availableSpots -= 1
    
}

class ParkingSpot(lvl: Level, val rpw: Int, val spotNumber: Int, s: VehicleSize){
    private var vehicle: Vehicle = null

    def isAvailable(): Boolean = vehicle == null

    def canFitVehicle(vehicle: Vehicle): Boolean = ???

    def park(vehicle: Vehicle): Boolean = ???

    def removeVehicle(): Unit = ???



}
object Chapter8A4 extends App