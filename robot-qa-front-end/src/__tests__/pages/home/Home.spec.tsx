// Importar as bibliotecas necessárias e o próprio componente Home
import { render, fireEvent, screen } from '@testing-library/react';
import '@testing-library/jest-dom'
import Home from '../../../pages/home/Home';
import { ThemeProvider } from 'styled-components';
import defaultTheme from '../../../themes/default';

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => jest.fn(),
}));


test('clicking "More About Us" button navigates to the correct page', () => {
  const navigateMock = jest.fn(); 
  jest.spyOn(require('react-router-dom'), 'useNavigate').mockReturnValue(navigateMock);

  render(        
    <ThemeProvider theme={defaultTheme}>
        <Home />
    </ThemeProvider>
    );

  expect(screen.getByText("Robot QA")).toBeInTheDocument();

  fireEvent.click(screen.getByText("More About Us"));

  expect(navigateMock).toHaveBeenCalledWith('/about');
});