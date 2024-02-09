import {render, screen, fireEvent} from "@testing-library/react"
import About from "../../../pages/about/About";
import '@testing-library/jest-dom'
import { ThemeProvider } from "styled-components";
import defaultTheme from "../../../themes/default";

it('renders page', () => {
  render(
    <ThemeProvider theme={defaultTheme}>
      <About />
    </ThemeProvider>
  );
  expect(screen.getByText("About Us")).toBeInTheDocument();
  expect(screen.getByText("This page was develop by Victor a really committed developer anxious to learn everyday more.")).toBeInTheDocument();
});
  
it('clicking button opens Github page in new tab', () => {
  render(
    <ThemeProvider theme={defaultTheme}>
      <About />
    </ThemeProvider>
  );

  const originalOpen = window.open;
  window.open = jest.fn();

  fireEvent.click(screen.getByText("Github"));

  expect(window.open).toHaveBeenCalledWith('https://github.com/victorhfsilva', '_blank');

  window.open = originalOpen;
});