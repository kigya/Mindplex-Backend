package dev.kigya.mindplex.application

import dev.kigya.mindplex.adapters.outbound.FileFactProvider
import dev.kigya.mindplex.adapters.outbound.FileQuestionProvider
import dev.kigya.mindplex.domain.service.FactService
import dev.kigya.mindplex.domain.service.QuestionService

object MindplexApplication {
    private val fileQuestionProvider = FileQuestionProvider()
    val questionService = QuestionService(fileQuestionProvider)

    private val fileFactProvider = FileFactProvider()
    val factService = FactService(fileFactProvider)
}
