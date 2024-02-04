import Button from "../../components/button/Button";
import MainContainer from "../../components/containers/MainContainer";
import FormInput from "./FormInput";
import LoginForm from "./LoginForm";
import { LoginModel } from "../../models/loginModel";
import { useState } from "react";
import { getToken } from "../../service/getToken.service";

function Login() {
    const [login, setLogin] = useState<LoginModel>({username:  '', password: ''})
    const [authenticated, setAuthenticated] = useState<boolean>(false)

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const {name, value} = e.target;
        setAuthenticated(false)
        setLogin({
            ...login,
            [name]: value
        })
    }
    
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const token: string = await getToken(login)
            localStorage.setItem('token', token)
            setAuthenticated(true)
        } catch {
            setAuthenticated(false)
        }
    }

    return (
        <MainContainer>
            <h2>Login</h2>
            <LoginForm>
                <label>
                    User:
                    <FormInput name="username" type="text" value={login.username} onChange={handleChange} />
                </label>
                
                <label>
                    Password:
                    <FormInput name="password" type="password" value={login.password} onChange={handleChange}/>
                </label>
                
                <Button type="submit" onClick={handleSubmit}>Login</Button>
                {authenticated && <p>{login.username} is authenticated</p>}
            </LoginForm>
        </MainContainer>
    )
}

export default Login;