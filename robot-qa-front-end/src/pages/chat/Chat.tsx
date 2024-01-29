import { useEffect, useState } from "react";
import MainContainer from "../../components/containers/MainContainer";
import { RobotModel } from "../../models/robotModel";
import { getRobots } from "../../service/getRobots.service";
import RobotsSelect from "./RobotsSelect";
import AnswerContainer from "./AnswerContainer";
import QuestionInput from "./QuestionInput";
import Button from "../../components/button/Button";

function Chat() {

    const [robots, setRobots] = useState<RobotModel[]>([]);
    
    useEffect(() => {
        // Fetch the data when the component mounts
        const fetchRobots = async () => {
          try {
            const data = await getRobots();
            setRobots(data);
          } catch (error) {
            alert('Error fetching robots:' + error);
          }
        };
    
        fetchRobots();
      }, []);

    return (
        <MainContainer>
            <h2>Chat</h2>
            <p>Choose one Robot:</p>
            <RobotsSelect>
                {robots.map(
                    (robot: RobotModel) => (
                        <option value={robot.name} key={robot.id}>{robot.name}</option>
                    )
                )}
            </RobotsSelect>
            <AnswerContainer>
                <h3>Answer</h3>
            </AnswerContainer>
            <p>What is your question?</p>
            <QuestionInput />
            <Button>Ask</Button>
        </MainContainer>
    )
}

export default Chat;