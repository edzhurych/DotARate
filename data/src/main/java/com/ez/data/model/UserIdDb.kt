package com.ez.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ez.domain.model.UserId

/**
* Аннотацией Entity нам необходимо пометить объект, который мы хотим хранить в базе данных
* Этот класс будет использован для создания таблицы в базе
* В качестве имени таблицы будет использовано имя класса
* А поля таблицы будут созданы в соответствии с полями класса
*/
@Entity
data class UserIdDb internal constructor(
    @field:PrimaryKey
    val id: Long
) {

    fun toUserId() = UserId(
        id = this.id
    )
}

fun UserId.toUserIdDb() = UserIdDb(
    id = this.id
)