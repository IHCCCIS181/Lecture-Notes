# LU05 L1 Hello Thymeleaf

This is an introduction to Thymeleaf, a modern server-side Java template engine for web and standalone environments.
Follow this guide: https://spring.io/guides/gs/serving-web-content

## Explaining Server-Side Rendering

![Diagram](./diagram/server-side-rendering.svg)

```mermaid
graph TD
    %% Common Precondition: Browser Knowledge
    BrowserKnowledge("💻 Browser Knowledge:\nOnly Understands: HTML, CSS, JavaScript.\n🚫 No Java, Python, Ruby, etc.")
    style BrowserKnowledge fill:#f9f,stroke:#333,stroke-width:2px,rx:10,ry:10
    
    linkStyle default interpolate basis
    
    %% Server-Side Rendering (SSR) Flow
    subgraph SSR ["Server-Side Rendering"]
        direction TB
        SSR_Request[1. User Request for Page] --> SSR_Server
        SSR_Server("2. Server (e.g., Node.js, Next.js, Django):\nRuns application logic,\npre-renders FULL HTML page with content.") --> SSR_Response
        SSR_Response("3. Sends Fully Rendered HTML Page\n(with all final HTML markup and content).") --> SSR_Browser_Initial
        SSR_Browser_Initial("4. Browser immediately displays content\nfrom the received HTML.") --> SSR_Browser_Hydration
        SSR_Browser_Hydration("5. Downloads & executes JavaScript\nfor interactivity (Hydration).") --> SSR_Browser_Interact
        SSR_Browser_Interact["6. User Interacts (e.g., navigates)"] --> SSR_FullPageReload
        SSR_FullPageReload("🔄 Navigation usually triggers FULL PAGE RELOAD:\nBrowser requests new page,\nServer generates full new HTML,\nOld page is completely replaced.")
        style SSR_FullPageReload fill:#ff9999,stroke:#333,stroke-width:2px
    end
    
    %% Client-Side Rendering (CSR) Flow
    subgraph CSR ["Client-Side Rendering (e.g., React)"]
        direction TB
        CSR_Request[1. User Request for Page] --> CSR_Server
        CSR_Server("2. Server:\nServes minimal HTML (often nearly empty)\n+ Big JavaScript Bundle.") --> CSR_Response
        CSR_Response("3. Sends Minimal HTML & JS Bundle.") --> CSR_Browser_Initial
        CSR_Browser_Initial("4. Browser receives minimal HTML.\n(Initial blank content or loader).") --> CSR_Browser_ExecuteJS
        CSR_Browser_ExecuteJS("5. Browser Executes JavaScript Bundle.") --> CSR_Browser_CSRBuild
        CSR_Browser_CSRBuild("6. JavaScript builds the DOM,\nfetches data from APIs (client-side),\nand dynamically displays the page.") --> CSR_Browser_Interact
        CSR_Browser_Interact["7. User Interacts (e.g., navigates)"] --> CSR_DynamicUpdate
        CSR_DynamicUpdate("✨ User interaction updates elements dynamically\nWITHOUT whole page reload:\nJavaScript modifies only specific DOM elements.\nNo browser page refresh.")
        style CSR_DynamicUpdate fill:#d4f1f4,stroke:#333,stroke-width:2px
    end

```

## Run the Application

```bash
./mvnw spring-boot:run
```

Then open your browser and go to: [http://localhost:8080](http://localhost:8080)
