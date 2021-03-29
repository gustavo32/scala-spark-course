val execList = List(1,2,3,4,5)
execList.contains(3)

val addList = scala.collection.mutable.ListBuffer[Int]()
addList += 1
addList += 2
addList += 3
addList += 4
addList

val evenArray = Array.range(1, 17, 2)
evenArray

val nonUniqueList = List(2,3,1,4,5,6,6,1,2)
nonUniqueList.toSet

val people = scala.collection.mutable.Map(("Sammy", 3), ("Frankie", 7), ("John", 45))
people.keys
people += ("Mike" -> 27)