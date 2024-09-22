package me.bossm0n5t3r.kotlinspringaiplayground.configurations

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.api.OpenAiApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AiConfiguration {
    @Bean
    fun chatClientAutoconfigured(chatClientBuilder: ChatClient.Builder): ChatClient = chatClientBuilder.build()

    @Bean
    fun chatClientProgrammatically(
        @Value("\${spring.ai.openai.api-key}") apiKey: String,
    ): ChatClient {
        val openAiChatModel = OpenAiChatModel(OpenAiApi(apiKey))
        return ChatClient.create(openAiChatModel)
    }
}
