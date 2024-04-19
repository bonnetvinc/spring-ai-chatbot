import "./App.css";
import { ChatClient } from "@/components/organisms/chat/chat-client";
import TanstackQueryClientProvider from "@/providers/QueryClientProvider";

function App() {
  return (
    <TanstackQueryClientProvider>
      <div className="px-20">
        <p>Welcome to chat-bot</p>
        <div className="z-10 border rounded-lg max-w-5xl w-full h-full text-sm lg:flex">
          <ChatClient />
        </div>
      </div>
    </TanstackQueryClientProvider>
  );
}

export default App;
