package com.devnuts.ruflu.ui.onboarding.fragment.seven

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.onboarding.FancyUIModel

class OnboardingFancyViewModel : ViewModel() {

    private var _fancy = MutableLiveData<Int>()
    val fancy: LiveData<Int> get() = _fancy

    fun initFancy(position: Int): List<Model> {
        _fancy.value = position

        val fancyRange = mutableListOf<FancyUIModel>()
        for (i in fancyInfo.indices) {
            fancyRange.add(FancyUIModel(fancy = fancyInfo[i], isSelected = false),)
            if (position == i) fancyRange[position].isSelected = true
        }

        return fancyRange
    }

    companion object {
        private val fancyInfo = listOf<String>(
            "강아지상", "안경", "애교 많음", "청순함", "요리 잘함",
            "눈웃음", "다정함", "긴 생머리", "술 잘마심", "연하",
            "청바지 잘 어울림", "연상", "은은한 샴푸향", "시크함", "운동 좋아함",
            "쌍커풀", "챙겨주는 성격", "귀여움", "잘 웃음", "나만 바라봄",
            "게임 좋아함", "손 예쁨", "원피스 잘 어울림", "우유 빛깔", "개그코드 일치",
            "대형견 느낌", "안경", "귀여운 애교", "적극적인", "다정다감", "운동 좋아하는",
            "수트핏", "오피스핏", "태평양 어깨", "글래머", "연하", "연상", "중저음 보이스",
            "책을 좋아하는", "성난 등근육", "너드", "단발", "장발", "손이 예쁜", "키가 큰"
       )
    }
}