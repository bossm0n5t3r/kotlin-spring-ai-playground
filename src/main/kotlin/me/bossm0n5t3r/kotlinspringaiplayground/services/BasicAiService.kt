package me.bossm0n5t3r.kotlinspringaiplayground.services

import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service

@Service
class BasicAiService(
    private val chatClientAutoconfigured: ChatClient,
    private val chatClientProgrammatically: ChatClient,
) {
    fun generationWithAutoconfigured(userInput: String): String =
        chatClientAutoconfigured
            .prompt()
            .user(userInput)
            .call()
            .content()

    fun generationWithProgrammatically(userInput: String): String =
        chatClientProgrammatically
            .prompt()
            .user(userInput)
            .call()
            .content()
}
