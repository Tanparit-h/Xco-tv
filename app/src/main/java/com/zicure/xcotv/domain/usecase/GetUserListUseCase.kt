package com.zicure.xcotv.domain.usecase

import com.zicure.xcotv.utils.Profile
import javax.inject.Inject

class GetUserListUseCase @Inject constructor() {
    operator fun invoke() = listOf<Profile>(
        Profile("Alisa linosa", "https://kawaii-avatar.now.sh/api/avatar", "U001"),
        Profile("Tonnist", "https://kawaii-avatar.now.sh/api/avatar?username=moe", "U002"),
        Profile("Man", "https://kawaii-avatar.now.sh/api/avatar?username=super", "U003"),
        Profile("Nongfill", "https://kawaii-avatar.now.sh/api/avatar?username=superthegf", "U004"),
        Profile("Baimon", "https://kawaii-avatar.now.sh/api/avatar?username=nicehat", "U005")
    )
}