package com.study.basicapp.repository.mapper

import com.study.basicapp.database.UserEntity
import com.study.basicapp.remote.UsersDto
import com.study.basicapp.ui.remotelist.UserItem


// domain -> data
fun mapperToUserEntity(items: List<UserItem>): List<UserEntity> {
    return items.toList().map {
        UserEntity(
            0,
            it.name,
            it.number
        )
    }
}
fun UserItem.map() = UserEntity(
    0,
    name,
    number
)

// data -> domain
fun UsersDto.toDomainModel(): UserItem {
    return UserItem(
        name = this.name,
        number = this.phone
    )
}



//// Mapper function
//fun SearchPhotoDto.toDomainModel(): SearchPhotoItem {
//    val photoitems = mutableListOf<PhotoItem>()
//    this.total
//    this.total_pages
//    this.results.forEach {
//        photoitems.add(PhotoItem(it.id,
//            it.alternative_slugs.ko,
//            it.urls.small,
//            it.description,
//            it.created_at,
//            it.updated_at,
//            it.width,
//            it.height))
//    }
//
//    return SearchPhotoItem(this.total, this.total_pages, photoitems)
//}
