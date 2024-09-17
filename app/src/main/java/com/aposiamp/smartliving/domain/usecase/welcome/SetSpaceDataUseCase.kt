package com.aposiamp.smartliving.domain.usecase.welcome

import com.aposiamp.smartliving.domain.mapper.SpaceDataMapper
import com.aposiamp.smartliving.domain.model.SpaceData
import com.aposiamp.smartliving.domain.repository.SpaceRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class SetSpaceDataUseCase(
    private val spaceRepository: SpaceRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(spaceData: SpaceData) {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")

        spaceRepository.setSpaceData(
            uid,
            SpaceDataMapper.toDto(spaceData)
        )
    }
}