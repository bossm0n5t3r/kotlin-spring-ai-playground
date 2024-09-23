package me.bossm0n5t3r.kotlinspringaiplayground.services

import me.bossm0n5t3r.kotlinspringaiplayground.dtos.MathematicianPublications
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service

@Service
class ChatClientResponsesService(
    private val chatClientAutoconfigured: ChatClient,
) {
    fun returningAChatResponse(userInput: String): ChatResponse =
        chatClientAutoconfigured
            .prompt()
            .user(userInput)
            .call()
            .chatResponse()

    fun returningAnEntity(): MathematicianPublications =
        chatClientAutoconfigured
            .prompt()
            .user("Generate the publications for a random mathematician.")
            .call()
            .entity(MathematicianPublications::class.java)

    fun returningAnEntityWithParameterizedTypeReference(): List<MathematicianPublications> =
        chatClientAutoconfigured
            .prompt()
            .user("Generate 5 publications for René Descartes and Carl Friedrich Gauss.")
            .call()
            .entity(object : ParameterizedTypeReference<List<MathematicianPublications>>() {})
}
