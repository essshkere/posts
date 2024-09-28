import org.junit.Assert.assertEquals
import org.junit.Test

class MainKtTest {

    @Test
    fun createCommentTrue() {
        val newPost = Post()
        val addedPost = WallService.add(newPost)
        val newPost2 = Post()
        val addedPost2 = WallService.add(newPost2)
val addedComment :Comment = WallService.createComment(1, Comment())
        assertEquals(0, addedComment.count)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentThrow() {
        val addedComment :Comment = WallService.createComment(100, Comment())
    }


    @Test
    fun addTest() {
        val addedPost = WallService.add(Post())
        val addedPost2 = WallService.add(Post())
        val idTest = addedPost2.id
        assertEquals(4, idTest)
    }

    @Test
    fun updateTestTrue() {
        val addedPost = WallService.add(Post())
        val result = WallService.update(4, 2, 2, 4, "5", Comment(), "6", false, true, false)

        assertEquals(true, result)
    }

    @Test
    fun updateTestFalse() {
        val addedPost = WallService.add(Post())
        val result = WallService.update(10, 2, 3, 4, "5", Comment(), "6", false, true, false)

        assertEquals(false, result)
    }


}