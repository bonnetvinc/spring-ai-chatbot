"use client";

import React, { useEffect, useState } from "react";
import { openApiChatController, openApiTestController } from "@/repository/controller-wrapper";
import { ChatRequest } from "@generated/chat-server";
import { useMutation } from "@tanstack/react-query";
import { ChatList } from "./chat-list";

export interface ChatMessage {
  message: string;
  name: string;
}

export function ChatClient() {
  const [discussionId, setDiscussionId] = useState<string>();
  const [messagesState, setMessageState] = useState<ChatMessage[]>([
    { message: "", name: "bot" },
  ]);

  async function fetcher(chatRequest: ChatRequest) {
  
    return openApiChatController.chat(chatRequest);
  }

  useEffect(() => {
    const number = Math.floor(Math.random() * 1000);
    setDiscussionId(number.toString());
  }, []);

  const { mutate, data, isPending } = useMutation({
    mutationFn: fetcher,
  });

  useEffect(() => {
    if (!isPending && data) {
      const message = { message: data, name: "bot" };
      setMessageState((a) => [...a, message]);
    }
  }, [data, isPending]);

  const handleSetMessages = (newMessage: string) => {
    const chatRequest: ChatRequest = {
      messageDto: { message: newMessage, chatId: discussionId },
    };
    const message = { message: newMessage, name: "me" };
    setMessageState((a) => [...a, message]);
    mutate(chatRequest);
  };

  return (
    <div className="h-full w-full">
      <div className="flex flex-col justify-between w-full h-full">
        <ChatList
          messages={messagesState}
          sendMessage={handleSetMessages}
          isLoading={isPending}
        />
      </div>
    </div>
  );
}
