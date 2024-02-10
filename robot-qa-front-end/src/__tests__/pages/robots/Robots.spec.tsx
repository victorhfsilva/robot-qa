import {render, screen, waitFor} from "@testing-library/react"
import '@testing-library/jest-dom'
import { ThemeProvider } from "styled-components";
import defaultTheme from "../../../themes/default";
import Robots from "../../../pages/robots/Robots";

jest.mock('../../../service/getRobots.service', () => ({
    getRobots: jest.fn().mockResolvedValue([
      { id: 1, name: 'Robot 1', description: 'Description 1', questions: ["Question 1", "Question 2"] },
      { id: 2, name: 'Robot 2', description: 'Description 2', questions: ["Question 3", "Question 4"] },
    ]),
  }));
  
 
  it('should render robots correctly', async () => {
    render(
        <ThemeProvider theme={defaultTheme}>
            <Robots />
        </ThemeProvider>
    );
    
   
    await waitFor(() => expect(screen.getByText('Robot 1')).toBeInTheDocument());
    await waitFor(() => expect(screen.getByText('Description 1')).toBeInTheDocument());
    await waitFor(() => expect(screen.getByText('Question 1')).toBeInTheDocument());
    await waitFor(() => expect(screen.getByText('Question 2')).toBeInTheDocument());
    await waitFor(() => expect(screen.getByText('Robot 2')).toBeInTheDocument());
    await waitFor(() => expect(screen.getByText('Description 2')).toBeInTheDocument());
    await waitFor(() => expect(screen.getByText('Question 3')).toBeInTheDocument());
    await waitFor(() => expect(screen.getByText('Question 4')).toBeInTheDocument());

  });