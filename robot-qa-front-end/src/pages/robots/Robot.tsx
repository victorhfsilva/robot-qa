import { RobotModel } from "../../models/robotModel";
import RobotContainer from "./RobotContainer";


function Robot({name, description, questions} : Readonly<RobotModel>) {

    return (
        <RobotContainer>
            <h3>{name}</h3>
            <p>{description}</p>
            <h4>Questions</h4>
            <ul>
                {questions.map(
                    (question, index) => (
                        <li key={index}>{question}</li>
                    )
                )}
            </ul>

        </RobotContainer>
    )
}

export default Robot