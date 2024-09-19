import org.junit.Assert.assertEquals
import org.junit.Test

class MainKtTest {

    @Test
    fun addTest() {
        val addedPost = WallService.add(Post())
        val addedPost2 = WallService.add(Post())
        val idTest = addedPost2.id
        assertEquals(2, idTest)
    }

    @Test
    fun updateTestTrue() {
        val addedPost = WallService.add(Post())
        val result = WallService.update(1, 2, 2, 4, "5", Comments(), "6", false, true, false)

        assertEquals(true, result)
    }

    @Test
    fun updateTestFalse() {
        val addedPost = WallService.add(Post())
        val result = WallService.update(6, 2, 3, 4, "5", Comments(), "6", false, true, false)

        assertEquals(false, result)
    }


}