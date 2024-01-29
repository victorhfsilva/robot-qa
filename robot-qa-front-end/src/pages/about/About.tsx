import Button from "../../components/button/Button";
import MainContainer from "../../components/containers/MainContainer";

function About() {
    const handleButtonClick = () => {
        window.open('https://github.com/victorhfsilva', '_blank'); // Abre o link em uma nova aba
    };

    return (
        <MainContainer>
            <h2>About Us</h2>
            <p>This page was develop by Victor a really committed developer anxious to learn everyday more.</p>
            <Button onClick={handleButtonClick}>Github</Button>
        </MainContainer>
    )
}

export default About;