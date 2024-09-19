fun main() {
    val newPost = Post(ownerId = 5)
    val addedPost = WallService.add(newPost)
    val newPost2 = Post(ownerId = 6)
    val addedPost2 = WallService.add(newPost2)
    println(newPost)
    println(newPost2)

}


data class Post(
    var id: Int = 0, //идентификатор записи
    val ownerId: Int = 0, // ид владельца стены
    val fromId: Int = 0, // Идентификатор автора записи от чьего имени опубликована запись
    val date: Int = 0, //   Время публикации записи в формате unixtime.
    val text: String = "0",//    Текст записи.
    val comments: Comments = Comments(),//Информация о комментариях к записи, объект с полями
    val postType: String = "0", //    Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest
    val canPin: Boolean = false,//    Информация о том, может ли текущий пользователь закрепить запись (1 — может, 0 — не может).
    val canDelete: Boolean = false, //Информация о том, может ли текущий пользователь удалить запись (1 — может, 0 — не может).
    val canEdit: Boolean = false//Информация о том, может ли текущий пользователь редактировать запись (1 — может, 0 — не может).
)

class Comments(
    val count: Int = 0, // — количество комментариев;
    val canPost: Boolean = false, //— информация о том, может ли текущий пользователь комментировать запись (1 — может, 0 — не может);
    val groupsCanPost: Boolean = false, //— информация о том, могут ли сообщества комментировать запись;
    val canClose: Boolean = false, //— может ли текущий пользователь закрыть комментарии к записи;
    val canOpen: Boolean = false  //— может ли текущий пользователь открыть комментарии к записи.
)

object WallService {
    private var posts = emptyArray<Post>()
    fun add(post: Post): Post {
        posts += post
        post.id = if (posts.isEmpty()) 1 else posts.maxOf { it.id } + 1
        return posts.last()
    }

    fun update(
        id: Int,
        ownerId: Int,
        fromId: Int,
        date: Int,
        text: String,
        comments: Comments = Comments(),
        postType: String,
        canPin: Boolean,
        canDelete: Boolean,
        canEdit: Boolean
    ): Boolean {
//        var result: Boolean
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(
                    id = id,
                    ownerId = ownerId,
                    fromId = fromId,
                    date = date,
                    text = text,
                    comments = comments,
                    postType = postType,
                    canPin = canPin,
                    canDelete = canDelete,
                    canEdit = canEdit

                )
                return true


            }

        }
        return false
    }
}