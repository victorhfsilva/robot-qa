import { api } from "../lib/api"
import { LoginModel } from "../models/loginModel"

export const getToken = async (login: LoginModel) => {
    const response = await api.post(
        "/api/v1/robots/token", 
        login
    )
    return response.data
} 