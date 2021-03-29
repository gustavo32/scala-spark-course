def isEven(num: Int): Boolean = {
  return num % 2 == 0
}

isEven(2)
isEven(3)

def existsEven(list: List[Int]): Boolean = {
  for (element <- list){
    if (isEven(element)){
      return true
    }
  }
  return false
}

existsEven(List(1, 3, 5, 6, 3, 4))
existsEven(List(1, 3, 5, 3, 5))

def luckyNumberSeven(list: List[Int]): Int = {
  return list.map(element => if (element == 7) element * 2 else element).sum
}

luckyNumberSeven(List(1, 2, 3))
luckyNumberSeven(List(1, 2, 3, 7))
luckyNumberSeven(List(7, 7))

def canBalance(list: List[Int]): Boolean = {
  val allSum: Int = list.sum
  var partialSum: Int = 0
  for (element <- list){
    partialSum += element
    if (partialSum == (allSum / 2)){
      return true
    }
  }
  return false
}

canBalance(List(1, 2, 3, 4))
canBalance(List(1, 2, 3))
canBalance(List(1, 5, 3, 3))


def isPalindrome(word: String): Boolean = {
  return word.reverse == word
}

isPalindrome("ana")
isPalindrome("wrongTest")
isPalindrome("subinoonibus")