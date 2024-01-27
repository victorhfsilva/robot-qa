import { ThemeProvider } from "styled-components"
import defaultTheme from "./themes/default"
import PageRouter from "./router/PageRouter"


function App() {

  return (
    <ThemeProvider theme={defaultTheme}>
      <PageRouter />
    </ThemeProvider>
  )
}

export default App
