package crackingthecoding.sol

import java.time.LocalDateTime
import scala.util.Random

/*
Q: Design a parking lot using object-oriented principles
A: 
Clarifications: 
- who is going to use it? (customers? tracks of ordinary circle? Motorcycle?
- what kind of parking lot it is? Is it free of charge? Is it single floor or multi floor?
- Is there is a disabled parking space
- Is there is a restrictions on the amount of vehicle
- where the parking lot, with roof or not. 
- when the parking lot will be closed?
- how - it's operate
- why
Kind? 

Core objects:
==============
- Vehicle
- Parking lot
- Gate - opened or closed
- Checkpoint 
- Billing service
- Parking space (for only one vehicle) - will contain attributes: isAvailable,  vehicle, id
	- private car, private car for disabled, motorcycle, a track


Relationships:
==============
- Vehicle object - will hold id, type, entry time
- Parking lot - will hold the Vehicle objects and be queried for is full 
- Checkpoint - will hold the Gate and will make the pass safe
Design patterns:
===============
- Parking Lot will be a singleton

*/
class Gate(val barierDown:Boolean=true){
	def open(): Gate = if(barierDown) new Gate(false) else this
	def close(): Gate = if(!barierDown) new Gate(true) else this
}
case class Vehicle(startTime: LocalDateTime=LocalDateTime.now())

object Billing{
	def pay(startTime: LocalDateTime, endTime: LocalDateTime, ammount: Double): Double = ammount 
}

class Checkpoint(val gate: Gate){
	def passVehicle(id: Int): Checkpoint = {
		val gate2 = gate.open()
		println(s"pass Vehicle with id: $id")
		Thread.sleep(1000)
		new Checkpoint(gate.close())
	}
}

class ParkingLot private(parkingSpaces: Map[Int, Vehicle], enterGate: Checkpoint, exitGate: Checkpoint){
	private val maxCapacity = 10

	private def addVehicle(id: Int): Map[Int, Vehicle] = 
		if (parkingSpaces.size < maxCapacity) parkingSpaces ++ Map(id -> Vehicle())
		else parkingSpaces
	
	private def removeVehicle(id: Int): Map[Int, Vehicle] = parkingSpaces.removed(id)

	def enter(id: Int): ParkingLot =
		if (parkingSpaces.size < maxCapacity) new ParkingLot(addVehicle(id), enterGate.passVehicle(id), exitGate)
		else this

	def exit(id: Int, ammount: Double = 100): ParkingLot = 
		if(Billing.pay(parkingSpaces(id).startTime, LocalDateTime.now(), ammount) > 0)
			new ParkingLot(removeVehicle(id), enterGate, exitGate.passVehicle(id))
		else this

	def printMe(): ParkingLot = {
		println(s"parking space=${parkingSpaces.mkString(",")}, enterence=${enterGate.gate}, exit=${exitGate.gate}")
		this
	}
}

object ParkingLot{
	private lazy val instance: ParkingLot = 
		new ParkingLot(Map.empty[Int, Vehicle], new Checkpoint(new Gate()), new Checkpoint(new Gate()))
	
	def apply(): ParkingLot = instance
}

object Chapter8Q4 extends App{
	val getId = () => (Random.nextDouble() * 100000).toInt
	
	val parkingLot = ParkingLot()
	val id1 = getId()
	val id2 = getId()
	parkingLot
	.enter(id1)
	.printMe()
	.enter(id2)
	.printMe()
	.exit(id2)
	.printMe()
	.exit(id1)
	.printMe()
	
}
