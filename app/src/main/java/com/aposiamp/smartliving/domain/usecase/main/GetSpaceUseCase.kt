package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.mapper.SpaceDataMapper
import com.aposiamp.smartliving.domain.model.SpaceData
import com.aposiamp.smartliving.domain.repository.SpaceRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class GetSpaceUseCase(
    private val spaceRepository: SpaceRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(): SpaceData {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")

        val spaceDataDTO = spaceRepository.getSpaceData(uid)
        val spaceData = SpaceDataMapper.fromDto(spaceDataDTO)
        return spaceData
    }
}