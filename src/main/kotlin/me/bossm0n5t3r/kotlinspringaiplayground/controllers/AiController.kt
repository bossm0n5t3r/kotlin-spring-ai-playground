package me.bossm0n5t3r.kotlinspringaiplayground.controllers

import me.bossm0n5t3r.kotlinspringaiplayground.dtos.Answer
import me.bossm0n5t3r.kotlinspringaiplayground.dtos.Question
import me.bossm0n5t3r.kotlinspringaiplayground.dtos.toAnswer
import me.bossm0n5t3r.kotlinspringaiplayground.services.BasicAiService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AiController(
    private val basicAiService: BasicAiService,
) {
    @PostMapping("/generation/autoconfigured")
    fun generationWithAutoconfigured(
        @RequestBody question: Question,
    ): Answer = basicAiService.generationWithAutoconfigured(question.text).toAnswer()

    @PostMapping("/generation/programmatically")
    fun generationWithProgrammatically(
        @RequestBody question: Question,
    ): Answer = basicAiService.generationWithProgrammatically(question.text).toAnswer()
}
