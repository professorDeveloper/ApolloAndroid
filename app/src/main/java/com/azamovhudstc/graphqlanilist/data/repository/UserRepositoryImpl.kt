package com.azamovhudstc.graphqlanilist.data.repository

import com.azamovhudstc.graphqlanilist.data.network.service.AniListGraphQlClient
import com.azamovhudstc.graphqlanilist.domain.repository.PersistenceRepository
import com.azamovhudstc.graphqlanilist.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val storage: PersistenceRepository,
    private val aniListGraphQlClient: AniListGraphQlClient,
    private val ioDispatcher: CoroutineDispatcher
) : UserRepository {

    override val bearerToken: String?
        get() = storage.bearerToken

    override val refreshToken: String?
        get() = storage.refreshToken

    override val guestToken: String?
        get() = storage.guestToken

    override val isAuthenticated: Boolean
        get() = storage.bearerToken != null

    override val isGuest: Boolean
        get() = storage.guestToken != null

    override val userId: String?
        get() = storage.aniListUserId

    override val expiration: Int?
        get() = storage.expiration

    override fun setBearerToken(authToken: String?) {
        storage.bearerToken = authToken
    }

    override fun setRefreshToken(refreshToken: String?) {
        storage.refreshToken = refreshToken
    }

    override fun setAniListUserId(sync: String?) {
        storage.aniListUserId = sync
    }

    override fun setExpirationTime(expiration: Int) {
        storage.expiration = expiration
    }

    override fun setProvider(provider: String) {
    }

    override fun clearStorage(triggered: () -> Unit) {
        storage.clearStorage(triggered)
    }


}