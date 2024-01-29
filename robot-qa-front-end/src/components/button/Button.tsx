import styled from "styled-components";

const Button = styled.button`
    margin-top: 4%;
    width: 60%;
    padding-top: 1.5%;
    padding-bottom: 1.5%;
    background-color: ${props => props.theme.colors.primaryBackground};  
    color: ${props => props.theme.colors.primaryColor};
    font-family: ${props => props.theme.fonts.primaryFont};
    font-weight: ${props => props.theme.fontWeight.thin};
    font-size: ${props => props.theme.fontSize.xs};
    border-radius: ${props => props.theme.borderRadius.sm};
`

export default Button;