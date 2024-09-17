fun main() {
}

data class Post(
    var id: Int, //идентификатор записи
    val ownerId: Int, // ид владельца стены
    val fromId: Int, // Идентификатор автора записи от чьего имени опубликована запись
    val date: Int, //   Время публикации записи в формате unixtime.
    val text: String,//    Текст записи.
    val comments: Comments = Comments(),//Информация о комментариях к записи, объект с полями
    val postType: String, //    Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest
    val canPin: Boolean,//    Информация о том, может ли текущий пользователь закрепить запись (1 — может, 0 — не может).
    val canDelete: Boolean, //Информация о том, может ли текущий пользователь удалить запись (1 — может, 0 — не может).
    val canEdit: Boolean //Информация о том, может ли текущий пользователь редактировать запись (1 — может, 0 — не может).
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
        post.id = posts.lastIndex
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
        var result: Boolean = true
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(
                    id = post.id,
                    ownerId = post.ownerId,
                    fromId = post.fromId,
                    date = post.date,
                    text = post.text,
                    comments = post.comments,
                    postType = post.postType,
                    canPin = post.canPin,
                    canDelete = post.canDelete,
                    canEdit = post.canEdit
                )
                result = true

            } else result = false
        }
        return result
    }
}
