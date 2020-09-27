fun main() {
//mark class nullable by using ?    Int?    Double?

    // readLine return nullable value
//    var beverage = readLine().capitalize()  this not compile, can't invoke method for nullable value

//    BUt can use safe call
//    var beverage = readLine()?.capitalize()

//    or if want to do more than one function - use let
//    var beverage = readLine()?.let {
//        if (it.isNotBlank()){
//            it.capitalize()
//        } else {
//            "Buttered ale"
//        }
//    }

//    Also is operator !!   - Ignore that this var is nullable, but if value null - invoked NullPointException
//    var beverage = readLine()!!.capitalize()

//    3 way: equal if var is null
    var beverage = readLine()
//    beverage = null
    if (beverage != null) {
        beverage = beverage.capitalize()
    } else {
        println("I can't do that without crashing - beverage was null!")
    }

//    null coalescing operator ?:   if left operand == null, do right operation
    val beverageServed: String = beverage ?: "Buttered Ale"
    println(beverageServed)

//    combine
    beverage = readLine()
    beverage?.let {
        beverage = it.capitalize()
    } ?: println("I can't do that without crashing - beverage is null")

//beverage = null
    println(beverage)
}