import { useEffect, useState } from "react";
import Button from "../../components/button/Button";
import MainContainer from "../../components/containers/MainContainer";
import FormInput from "./FormInput";
import LoginForm from "./LoginForm";
import { LoginModel } from "../../models/loginModel";
import { RobotModel } from "../../models/robotModel";
import { getRobots } from "../../service/getRobots.service";
import RobotsSelect from "../../components/select/RobotsSelect";

function Login() {
    const [robots, setRobots] = useState<RobotModel[]>([])
    const [login, setLogin] = useState<LoginModel>({user:  '', password: '', robot: ''})

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const {name, value} = e.target;
        setLogin({
            ...login,
            [name]: value
        })
    }
    
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        console.log(`${login.user} ${login.password} ${login.robot}`)
    }

    useEffect(() => {
        // Fetch the data when the component mounts
        const fetchRobots = async () => {
          try {
            const data = await getRobots();
            setRobots(data);
            setLogin({
                ...login,
                'robot': data[0].name
            })
          } catch (error) {
            alert('Error fetching robots:' + error);
          }
        };
    
        fetchRobots();
      }, []);

    return (
        <MainContainer>
            <h2>Login</h2>
            <LoginForm>
                <label>
                    User:
                    <FormInput name="user" type="text" value={login.user} onChange={handleChange} />
                </label>
                
                <label>
                    Password:
                    <FormInput name="password" type="password" value={login.password} onChange={handleChange}/>
                </label>
                
                <label>
                    Robot:
                    <RobotsSelect name="robot" value={login.robot} onChange={handleChange}>
                        {robots.map(
                            (robot: RobotModel) => (
                                <option value={robot.name} key={robot.id}>{robot.name}</option>
                            )
                        )}
                    </RobotsSelect>
                </label>

                <Button type="submit" onClick={handleSubmit}>Login</Button>
            </LoginForm>
        </MainContainer>
    )
}

export default Login;