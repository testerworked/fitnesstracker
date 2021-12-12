package com.myfitness.fitnesscompose

import dagger.Component
import javax.inject.Inject

@Component
interface Body {
    fun addTraining(app: MainActivity)
}




