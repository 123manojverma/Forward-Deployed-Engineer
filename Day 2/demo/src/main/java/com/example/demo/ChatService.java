package com.example.demo;

import com.example.demo.aitools.CalculatorTool;
import com.example.demo.aitools.CurrencyExchangeTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    private final ChatClient chatClient;
    private CalculatorTool calculatorTool;
    private CurrencyExchangeTool currencyExchangeTool;

    private List<Message> history=new ArrayList<>();

    public ChatService(ChatClient.Builder builder,CalculatorTool calculatorTool,CurrencyExchangeTool currencyExchangeTool) {
        this.chatClient = builder.build();
        this.calculatorTool=calculatorTool;
        this.currencyExchangeTool=currencyExchangeTool;
    }

    private final String SYSTEM_PROMPT=
            """
            You are a helpful AI assistant with access to external tools.

            Follow these rules:
            1. For arithmetic calculations, ALWAYS use the calculator tool.
            2. Always use calculator tool for even trivial calculation.
            3. For currency conversion or exchange rates, ALWAYS use the convertCurrencyTool.
            4. You may call multiple tools when solving a mult-step request.
            5. After receiving tool results, explain the answer naturally.
            6. Never invent current weather or exchange-rate information.
            """;

    public String chat(String message) {
//        USER role
        history.add(new UserMessage(message));

//        SYSTEM + Conversation History
        String response = chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .messages(history)
                .tools(calculatorTool,currencyExchangeTool)
                .call()
                .content();

//        ASSISTANT role
        history.add(new AssistantMessage(response));

        return response;
    }
}
