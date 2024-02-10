import { useEffect, useState } from "react";
import MainContainer from "../../components/containers/MainContainer";
import { RobotModel } from "../../models/robotModel";
import { getRobots } from "../../service/getRobots.service";
import RobotsSelect from "../../components/select/RobotsSelect";
import AnswerContainer from "./AnswerContainer";
import QuestionInput from "./QuestionInput";
import Button from "../../components/button/Button";
import { useWebsocket } from "../../hooks/useWebsocket";
import { StompSubscription } from "@stomp/stompjs";

function Chat() {

    const [robots, setRobots] = useState<RobotModel[]>([])
    const [subscription, setSubscription] = useState<StompSubscription>()
    const [selectedRobot, setSelectedRobot] = useState<string>('')
    const [typedQuestion, setTypedQuestion] = useState<string>('')

    const {message, getClient, subscribe, publish} = useWebsocket()
   

    useEffect(() => {
        // Fetch the data when the component mounts
        const fetchRobots = async () => {
          try {
            const token: string | null = localStorage.getItem('token') 
            const data = await getRobots(token ?? '');
            
            setRobots(data);
            setSelectedRobot(data[0].name)
          } catch (error) {
            console.log('Error fetching robots.');
          }
        };
    
        fetchRobots();
      }, []);

       
    useEffect(() => {
        return () => {
          if (subscription) {
            subscription.unsubscribe();
          }
        };
      }, [subscription]);

     
    const handleAskButtonClick = async () => {
        try {
          const client = await getClient();
          const subscription = subscribe(client, selectedRobot);
          setSubscription(subscription);
          publish(client, selectedRobot, typedQuestion);
        } catch (error) {
          console.log(error);
        }
      };


    return (
        <MainContainer>
            <h2>Chat</h2>
            <p>Choose one Robot:</p>
            <RobotsSelect value={selectedRobot} onChange={(e) => setSelectedRobot(e.target.value)}>
                {robots.map(
                    (robot: RobotModel) => (
                        <option value={robot.name} key={robot.id}>{robot.name}</option>
                    )
                )}
            </RobotsSelect>
            <AnswerContainer>
                <h3>Answer</h3>
                <p>{message}</p>
            </AnswerContainer>
            <p>What is your question?</p>
            <QuestionInput value={typedQuestion} onChange={(e) => setTypedQuestion(e.target.value)} data-testid="question input"/>
            <Button onClick={handleAskButtonClick}>Ask</Button>
            
        </MainContainer>
    )
}

export default Chat;