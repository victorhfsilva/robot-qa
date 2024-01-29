import styled from "styled-components";

const QuestionInput = styled.textarea`
    margin-top: 4%;
    padding-top: 1.5%;
    padding-bottom: 1.5%; 
    width: 60%;
    color: ${props => props.theme.colors.primaryColor};
    background-color: ${props => props.theme.colors.secundaryBackground};
    font-family: ${props => props.theme.fonts.primaryFont};
    font-weight: ${props => props.theme.fontWeight.thin};
    font-size: ${props => props.theme.fontSize.xs};
`
export default QuestionInput;