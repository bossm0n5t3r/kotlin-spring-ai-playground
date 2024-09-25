package me.bossm0n5t3r.kotlinspringaiplayground.services

import me.bossm0n5t3r.kotlinspringaiplayground.dtos.MathematicianPublications
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

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

    fun streamingResponses(userInput: String): Flux<String> =
        this.chatClientAutoconfigured
            .prompt()
            .user(userInput)
            .stream()
            .content()

    fun streamingResponsesWithConverter(): List<MathematicianPublications> {
        val converter = BeanOutputConverter(object : ParameterizedTypeReference<List<MathematicianPublications>>() {})
        val prompt =
            """
            Generate the publications for a random mathematician.
            {format}
            """.trimIndent()

        val flux =
            this.chatClientAutoconfigured
                .prompt()
                .user { it.text(prompt).param("format", converter.getFormat()) }
                .stream()
                .content()

        val content = flux.collectList().block()?.joinToString("") ?: ""
        return converter.convert(content) ?: emptyList()
    }
}
