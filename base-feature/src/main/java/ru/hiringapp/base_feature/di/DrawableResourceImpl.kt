package ru.hiringapp.base_feature.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.hiringapp.base.resources.DrawableResource
import java.lang.ref.WeakReference
import ru.hiringapp.uikit.R
import javax.inject.Inject

class DrawableResourceImpl@Inject constructor(
    @ApplicationContext private val context: Context,
) : DrawableResource {

    override val searchButtonIcon: Int get() = R.drawable.ic_search_not_selected

    override val favouritesButtonIcon: Int get() = R.drawable.ic_search_not_selected

    override val feedbackButtonIcon: Int get() = R.drawable.ic_feedback

    override val profileButtonIcon: Int get() = R.drawable.ic_profile_selected

    override val messagesButtonIcon: Int get() = R.drawable.ic_messages_not_selected
}