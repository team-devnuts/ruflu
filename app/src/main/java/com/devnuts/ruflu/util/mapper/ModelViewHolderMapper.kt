package com.devnuts.ruflu.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.devnuts.ruflu.databinding.*
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.adapter.viewholder.common.DetailViewHolder
import com.devnuts.ruflu.ui.adapter.viewholder.home.CardImageViewHolder
import com.devnuts.ruflu.ui.adapter.viewholder.home.CardViewHolder
import com.devnuts.ruflu.ui.adapter.viewholder.like.MatchViewHolder
import com.devnuts.ruflu.ui.adapter.viewholder.like.SomeViewHolder
import com.devnuts.ruflu.ui.adapter.viewholder.onboarding.*
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.util.listener.ModelAdapterListener

object ModelViewHolderMapper {
    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        adapterListener: ModelAdapterListener? = null
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.USER_CARD_CEL -> CardViewHolder(
                ItemUserCardBinding.inflate(inflater, parent, false)
            )

            CellType.IMAGE_CEL -> CardImageViewHolder(
                ItemUserCardImagesBinding.inflate(inflater, parent, false)
            )

            CellType.SOME_LIKE_CEL -> SomeViewHolder(
                ItemSomeUserBinding.inflate(inflater, parent, false)
            )

            // onboarding
            CellType.GENDER_CEL -> GenderViewHolder(
                ItemOnboardingGenderBinding.inflate(inflater, parent, false)
            )

            CellType.AGE_CEL -> AgeViewHolder(
                ItemOnboardingAgeBinding.inflate(inflater, parent, false)
            )

            CellType.HEIGHT_CEL -> HeightViewHolder(
                ItemOnboardingHeightBinding.inflate(inflater, parent, false)
            )

            CellType.ACADEMY_CEL -> AcademyViewHolder(
                ItemOnboardingAcademyBinding.inflate(inflater, parent, false)
            )

            CellType.FANCY_CEL -> FancyViewHolder(
                ItemOnboardingFancyBinding.inflate(inflater, parent, false)
            )

            CellType.SOME_MATCH_CEL -> MatchViewHolder(
                ItemMatchUserBinding.inflate(inflater, parent, false)
            )

            CellType.DETAIL_CEL -> DetailViewHolder(
                ItemUserDetailBinding.inflate(inflater, parent, false)
            )

            else -> throw java.lang.IllegalArgumentException("옳은 분기가 없습니다.")
        }
        return viewHolder as ModelViewHolder<M>
    }
}