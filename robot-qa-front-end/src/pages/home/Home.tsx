import { useNavigate } from "react-router-dom";
import Button from "../../components/button/Button";
import MainContainer from "../../components/containers/MainContainer";

function Home() {

    const navigate = useNavigate();

    const handleButtonClick = () => {
        navigate('/about');
    };

    return (
        <MainContainer>
            <h2>Robot QA</h2>
            <p> Robot QA is a project implemented to exercise QA concepts. By irony of the destiny its name is not an acronym for Quality Assurance, 
                but for Questions and Answers. The main goal of this project is that you ask predefined questions to a Robot.</p>
            <Button onClick={handleButtonClick}>More About Us</Button>
        </MainContainer>
        
    )
}

export default Home;