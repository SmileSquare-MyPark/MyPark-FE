package com.smile.mypark.presentation.sign

import com.smile.mypark.domain.model.SignStartArgs

object SignupStash {
    var args: SignStartArgs? = null
    fun consume(): SignStartArgs? = args.also { args = null }
}