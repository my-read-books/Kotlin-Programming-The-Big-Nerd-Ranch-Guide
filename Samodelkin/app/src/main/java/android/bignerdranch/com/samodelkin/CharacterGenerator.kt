package android.bignerdranch.com.samodelkin

import java.io.Serializable

private fun Int.roll() = (0 until this)
    .map { (1..6).toList().random() }.sum().toString()

private val firstName = listOf("Eli", "Alex", "Sophie")
private val lastName = listOf("Lightweaver", "Greatfoot", "Oakenfeld")

object CharacterGenerator {
    data class CharacterData(val name: String,
                             val race: String,
                             val dex: String,
                             val wis: String,
                             val str: String) : Serializable
    private fun name() = "${firstName.random()} ${lastName.random()}"
    private fun race() = listOf("dwarf", "elf", "human", "halfling").random()
    private fun dex() = 4.roll()
    private fun wis() = 3.roll()
    private fun str() = 5.roll()
    fun generate() = CharacterData(name = name(),
        race = race(),
        dex = dex(),
        wis = wis(),
        str = str())
}