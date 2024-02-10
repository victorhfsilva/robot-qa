import '@testing-library/jest-dom'
import {render, screen, waitFor, fireEvent} from "@testing-library/react"
import { ThemeProvider } from "styled-components"
import defaultTheme from "../../../themes/default"
import Chat from '../../../pages/chat/Chat'

jest.mock('../../../service/getRobots.service', () => ({
    getRobots: jest.fn().mockResolvedValue([
        { id: 1, name: 'Robot 1', description: 'Description 1', questions: ["Question 1", "Question 2"] },
        { id: 2, name: 'Robot 2', description: 'Description 2', questions: ["Question 3", "Question 4"] },
    ])
}))

it('should render Select accordingly', async () => {
    render (
        <ThemeProvider theme={defaultTheme}>
            <Chat />
        </ThemeProvider>
    )

    const robotsSelect = await waitFor(() => screen.getByDisplayValue("Robot 1"))

    fireEvent.change(robotsSelect, { target: { value: 'Robot 2' } })

    await waitFor(() => expect(screen.getByDisplayValue("Robot 2")).toBeInTheDocument())
})

it('should render question input accordingly', async () => {
    render (
        <ThemeProvider theme={defaultTheme}>
            <Chat />
        </ThemeProvider>
    )

    const questionInput = await waitFor(() => screen.getByTestId("question input"))
    expect(questionInput).toBeInTheDocument()
})