# WhatsApp Chatbot Backend Simulation (Spring Boot)

This is a simple backend simulation of a WhatsApp chatbot using Java + Spring Boot.

## Requirements (what this project does)

- Exposes a REST endpoint: `POST /webhook`
- Accepts JSON input simulating WhatsApp inbound messages
- Returns predefined replies:
  - `Hi` → `Hello`
  - `Bye` → `Goodbye`
- Logs all incoming messages and outgoing replies

## Run locally (Windows PowerShell)

From the project folder:

```powershell
cd c:\WhatsAppChatbot\whatsapp-chatbot
.\gradlew bootRun
```

The server starts at `http://localhost:8080`.

## Test the webhook

### Hi → Hello

```powershell
curl -Method Post http://localhost:8080/webhook `
  -ContentType "application/json" `
  -Body '{"from":"233000000000","text":"Hi"}'
```

Expected response:

```json
{"reply":"Hello"}
```

### Bye → Goodbye

```powershell
curl -Method Post http://localhost:8080/webhook `
  -ContentType "application/json" `
  -Body '{"from":"233000000000","text":"Bye"}'
```

Expected response:

```json
{"reply":"Goodbye"}
```

## Payload format

The endpoint expects this minimal JSON:

```json
{
  "from": "233000000000",
  "text": "Hi"
}
```

## Run tests

```powershell
cd c:\WhatsAppChatbot\whatsapp-chatbot
.\gradlew test
```

## Bonus: Deploy on Render (free)

- Push this project to GitHub.
- In Render, create a **New Web Service** from your GitHub repo.
- **Build Command**: `./gradlew clean build`
- **Start Command**: `java -jar build/libs/whatsapp-chatbot-0.0.1-SNAPSHOT.jar`
- After deploy, test:
  - `POST https://<your-render-service>.onrender.com/webhook`

