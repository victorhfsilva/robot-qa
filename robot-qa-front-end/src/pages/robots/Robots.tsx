import { useEffect, useState } from "react";
import MainContainer from "../../components/containers/MainContainer";
import { RobotModel } from "../../models/robotModel";
import { getRobots } from "../../service/getRobots.service";
import Robot from "./Robot";

function Robots() {

    const [robots, setRobots] = useState<RobotModel[]>([]);
    
    useEffect(() => {
        // Fetch the data when the component mounts
        const fetchRobots = async () => {
          try {
            const token: string | null = localStorage.getItem('token') 
            const data = await getRobots(token ?? '');
            setRobots(data);
          } catch (error) {
            console.log('Error fetching robots.');
          }
        };
    
        fetchRobots();
      }, []);

    return (
        <MainContainer>
            <h2>Robots</h2>
            {robots.map(
                (robot) => (
                    <Robot key={robot.id} name={robot.name} description={robot.description} questions={robot.questions} id={robot.id} />
                )
            )}
        </MainContainer>
    )
}

export default Robots;