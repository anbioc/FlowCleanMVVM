package com.multithread.cocoon.di.module

import androidx.annotation.NonNull
import com.multithread.cocoon.base.TopStoriesParam
import com.multithread.cocoon.base.domain.GeneralUseCase
import com.multithread.cocoon.base.repository.StrategyFlowRepository
import com.multithread.cocoon.di.scope.PerApplication
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.domain.usecase.TopStoriesUseCase
import com.multithread.cocoon.error.ErrorContainer
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    @PerApplication
    fun provideTopStoryUseCAse(
        @NonNull repository: StrategyFlowRepository<TopStoryDomainEntity, TopStoriesParam>,
        @NonNull errorContainer: ErrorContainer
    ): GeneralUseCase<TopStoriesParam, TopStoryDomainEntity> =
        TopStoriesUseCase(repository, errorContainer)
}