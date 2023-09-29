package com.mest.mestroll.core.domain

import javax.inject.Inject

class EnterRoomUseCase @Inject constructor(
    private val mestrollRepository: MestrollRepository
) {
    suspend operator fun invoke(enterRoom: EnterRoom) = mestrollRepository.enterUser(enterRoom)
}
