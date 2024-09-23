package me.bossm0n5t3r.kotlinspringaiplayground.controllers

import me.bossm0n5t3r.kotlinspringaiplayground.dtos.Answer
import me.bossm0n5t3r.kotlinspringaiplayground.dtos.MathematicianPublications
import me.bossm0n5t3r.kotlinspringaiplayground.dtos.Question
import me.bossm0n5t3r.kotlinspringaiplayground.dtos.toAnswer
import me.bossm0n5t3r.kotlinspringaiplayground.services.BasicAiService
import me.bossm0n5t3r.kotlinspringaiplayground.services.ChatClientResponsesService
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AiController(
    private val basicAiService: BasicAiService,
    private val chatClientResponsesService: ChatClientResponsesService,
) {
    @PostMapping("/generation/autoconfigured")
    fun generationWithAutoconfigured(
        @RequestBody question: Question,
    ): Answer = basicAiService.generationWithAutoconfigured(question.text).toAnswer()

    @PostMapping("/generation/programmatically")
    fun generationWithProgrammatically(
        @RequestBody question: Question,
    ): Answer = basicAiService.generationWithProgrammatically(question.text).toAnswer()

    @PostMapping("/chat-client-responses/chat-response")
    fun returningAChatResponse(
        @RequestBody question: Question,
    ): ChatResponse = chatClientResponsesService.returningAChatResponse(question.text)

    @GetMapping("/chat-client-responses/entity")
    fun returningAnEntity(): MathematicianPublications = chatClientResponsesService.returningAnEntity()

    @GetMapping("/chat-client-responses/entity-with-parameterized-type-reference")
    fun returningAnEntityWithParameterizedTypeReference(): List<MathematicianPublications> =
        chatClientResponsesService.returningAnEntityWithParameterizedTypeReference()
}
