package com.sanya1am.consecutivepractices.profile.presentation.state

import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity

data class ProfileState(
    val items: List<MovieShortEntity> = emptyList()
)
