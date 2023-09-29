package com.mest.mestroll.core.domain

import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val mestrollRepository: MestrollRepository
) {
    suspend operator fun invoke(lat: String, lng: String) = mestrollRepository.getLocation(lat, lng)
}
