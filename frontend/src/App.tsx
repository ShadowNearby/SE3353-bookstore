import React from "react";
import { Router } from "./routes/Router";
import { UserProvider } from "./context/UserProvider";

function App() {
  return (
    <UserProvider>
      <Router />
    </UserProvider>
  );
}

export default App;
