import {fireEvent, render, screen, waitFor} from "@testing-library/react"
import '@testing-library/jest-dom'
import { ThemeProvider } from "styled-components";
import defaultTheme from "../../../themes/default";
import Login from "../../../pages/login/Login";
import { getToken } from "../../../service/getToken.service";

jest.mock('../../../service/getToken.service', () => ({
    getToken: jest.fn().mockResolvedValue("token")
}))

it('should get token correctly', async () => {    
    render(
        <ThemeProvider theme={defaultTheme}>
            <Login/>
        </ThemeProvider>
    )

    const userInput = screen.getByLabelText("User:")
    const passwordInput = screen.getByLabelText("Password:")
    const loginButton = screen.getByText("Login")

    fireEvent.change(userInput, { target: { value: 'User' } })
    fireEvent.change(passwordInput, { target: { value: 'Password' } })
    
    fireEvent.click(loginButton)

    await waitFor( () => expect(getToken).toHaveBeenCalledWith({username: "User", password: "Password"}))
    await waitFor( () => expect(localStorage.getItem("token")).toBe("token"))
    await waitFor( () => expect(screen.getByText("User is authenticated")).toBeInTheDocument())
})

